package me.dorie.blog.search.trend.infra.db;

import me.dorie.blog.search.trend.infra.db.jpa.TrendLogJpaOperator;
import me.dorie.blog.search.trend.infra.db.redis.TrendRedisOperator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.HttpServerErrorException;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
class TrendDBServiceFailCaseTest {

    @Autowired
    TrendDBService trendDBService;

    @MockBean
    TrendRedisOperator trendRedisOperator;

    @MockBean
    TrendLogJpaOperator trendLogJpaOperator;

    @Test
    void 인기검색어_조회시_레디스_조회가_실패할_경우_로그_바탕으로_조회한다() {
        when(trendRedisOperator.getTrendsByLimit(anyInt())).thenThrow(HttpServerErrorException.class);

        // when
        trendDBService.getTrends();

        // then
        verify(trendLogJpaOperator, times(1)).getTrendsByLimit(anyInt());
    }
}
