package me.dorie.blog.search.trend.infra.db;

import me.dorie.blog.search.trend.domain.Trend;
import me.dorie.blog.search.trend.domain.TrendCreateCommand;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
class TrendDBServiceTest {

    @Autowired
    TrendDBService trendService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @AfterEach
    void tearDown() {
        clearRedisData();
    }

    private void clearRedisData() {
        final Set<String> keys = Objects.requireNonNull(stringRedisTemplate.keys("*"));
        stringRedisTemplate.delete(keys);
    }

    @Test
    void 인기_검색어는_많이_등록된_순으로_조회된다() {
        // given
        final String keyword1st = "1순위 인기 검색어";
        final String keyword2st = "2순위 인기 검색어";
        final String keyword3st = "3순위 인기 검색어";

        IntStream.range(0, 10).forEach(it -> trendService.createTrend(TrendCreateCommand.from(keyword1st)));
        IntStream.range(0, 5).forEach(it -> trendService.createTrend(TrendCreateCommand.from(keyword2st)));
        IntStream.range(0, 1).forEach(it -> trendService.createTrend(TrendCreateCommand.from(keyword3st)));

        // when
        final List<Trend> result = trendService.getTrends();

        // then
        assertThat(result)
                .extracting("keyword")
                .containsExactlyElementsOf(List.of(keyword1st, keyword2st, keyword3st));
    }

    @Test
    void 인기_검색어는_최대_10개까지_조회된다() {
        // given
        IntStream.range(0, 30)
                .forEach(index -> trendService.createTrend(TrendCreateCommand.from(index + "번쨰검색어")));

        // when
        final List<Trend> result = trendService.getTrends();

        // then
        assertThat(result).hasSize(10);
    }

}
