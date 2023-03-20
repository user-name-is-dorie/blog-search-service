package me.dorie.blog.search.trend.infra.db;

import lombok.extern.slf4j.Slf4j;
import me.dorie.blog.search.trend.domain.Trend;
import me.dorie.blog.search.trend.domain.TrendCreateCommand;
import me.dorie.blog.search.trend.domain.TrendService;
import me.dorie.blog.search.trend.infra.db.jpa.TrendLogJpaOperator;
import me.dorie.blog.search.trend.infra.db.redis.TrendRedisOperator;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class TrendDBService implements TrendService {
    private static final int TOP_TEN_LIMIT = 10;

    private final CircuitBreaker trendCircuitBreaker;
    private final TrendLogJpaOperator trendLogJpaOperator;
    private final TrendRedisOperator trendRedisOperator;

    public TrendDBService(
            CircuitBreakerFactory circuitBreakerFactory,
            TrendLogJpaOperator trendLogJpaOperator,
            TrendRedisOperator trendRedisOperator
    ) {
        this.trendCircuitBreaker = circuitBreakerFactory.create("trend");
        this.trendLogJpaOperator = trendLogJpaOperator;
        this.trendRedisOperator = trendRedisOperator;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Trend> getTrends() {
        return trendCircuitBreaker.run(
                () -> trendRedisOperator.getTrendsByLimit(TOP_TEN_LIMIT),
                throwable -> {
                    log.error("레디스 바탕으로 인기검색어 조회가 실패하여 log 를 바탕으로 조회합니다.");
                    return trendLogJpaOperator.getTrendsByLimit(TOP_TEN_LIMIT);
                }
        );
    }

    @Override
    @Transactional
    public void createTrend(TrendCreateCommand command) {
        log.info("trend create by : {}", command);
        final String keyword = command.getKeyword();
        trendRedisOperator.write(keyword);
        trendLogJpaOperator.logging(keyword);
    }
}
