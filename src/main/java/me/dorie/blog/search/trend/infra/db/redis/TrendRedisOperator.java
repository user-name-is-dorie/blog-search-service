package me.dorie.blog.search.trend.infra.db.redis;

import lombok.RequiredArgsConstructor;
import me.dorie.blog.search.trend.domain.Trend;
import me.dorie.blog.search.trend.domain.TrendLog;
import me.dorie.blog.search.trend.domain.TrendReader;
import me.dorie.blog.search.trend.domain.TrendWriter;
import me.dorie.blog.search.trend.infra.db.TrendMapper;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class TrendRedisOperator implements TrendReader, TrendWriter {
    private static final String KEY = "trend";

    private final StringRedisTemplate redisTemplate;
    private final TrendMapper mapper;

    @Override
    public List<Trend> getTrendsByLimit(int limit) {
        final ZSetOperations<String, String> sortedSet = redisTemplate.opsForZSet();
        final Set<ZSetOperations.TypedTuple<String>> typedTuples = sortedSet.reverseRangeWithScores(KEY, 0, limit - 1L);
        if (CollectionUtils.isEmpty(typedTuples)) {
            return Collections.emptyList();
        }

        return typedTuples.stream()
                .map(tuple -> mapper.toTrend(tuple.getValue(), tuple.getScore()))
                .toList();
    }


    @Override
    public void write(TrendLog trend) {
        redisTemplate.opsForZSet().incrementScore(KEY, trend.getKeyword(), 1);
    }
}
