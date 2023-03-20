package me.dorie.blog.search.trend.infra.db.jpa;

import lombok.RequiredArgsConstructor;
import me.dorie.blog.search.trend.domain.Trend;
import me.dorie.blog.search.trend.domain.TrendLog;
import me.dorie.blog.search.trend.domain.TrendReader;
import me.dorie.blog.search.trend.infra.db.TrendMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TrendLogJpaOperator implements TrendReader {
    private final TrendLogJpaRepository trendLogRepository;
    private final TrendMapper mapper;

    @Override
    public List<Trend> getTrendsByLimit(int limit) {
        final List<TrendProjection> projections = trendLogRepository.getTrendProjectionsByLimit(limit);
        return mapper.toTrends(projections);
    }

    public void logging(TrendLog trend) {
        trendLogRepository.save(trend);
    }
}
