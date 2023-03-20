package me.dorie.blog.search.trend.infra.db;

import me.dorie.blog.search.trend.domain.Trend;
import me.dorie.blog.search.trend.domain.TrendCreateCommand;
import me.dorie.blog.search.trend.domain.TrendLog;
import me.dorie.blog.search.trend.domain.TrendService;
import me.dorie.blog.search.trend.infra.db.jpa.TrendLogJpaOperator;
import me.dorie.blog.search.trend.infra.db.redis.TrendRedisOperator;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TrendDBService implements TrendService {
    private static final int TOP_TEN_LIMIT = 10;

    private final CircuitBreaker trendCircuitBreaker;
    private final TrendLogJpaOperator trendLogOperator;
    private final TrendRedisOperator trendOperator;

    public TrendDBService(
            TrendLogJpaOperator jpaOperator,
            TrendRedisOperator redisOperator,
            CircuitBreakerFactory circuitBreakerFactory
    ) {
        this.trendLogOperator = jpaOperator;
        this.trendOperator = redisOperator;
        this.trendCircuitBreaker = circuitBreakerFactory.create("trend");
    }

    @Override
    @Transactional(readOnly = true)
    public List<Trend> getTrends() {
        return trendCircuitBreaker.run(
                () -> trendOperator.getTrendsByLimit(TOP_TEN_LIMIT),
                // 실패시 로그를 바탕으로 조회
                throwable -> trendLogOperator.getTrendsByLimit(TOP_TEN_LIMIT)
        );
    }

    @Override
    @Transactional
    public void createTrend(TrendCreateCommand command) {
        final TrendLog trend = TrendLog.from(command.getQuery());
        // 레디스 인기 검색어 갱신
        trendOperator.write(trend);
        // 검색어 로그 기록
        trendLogOperator.logging(trend);
    }
}
