package me.dorie.blog.search.trend.infra.db.jpa;

import lombok.RequiredArgsConstructor;
import me.dorie.blog.search.trend.domain.Trend;
import me.dorie.blog.search.trend.domain.TrendReader;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TrendLogJpaOperator implements TrendReader {
    private final TrendLogJpaRepository trendLogRepository;
    private final TrendJpaTranslator translator;

    @Override
    public List<Trend> getTrendsByLimit(int limit) {
        final List<TrendProjection> projections = trendLogRepository.getTrendProjectionsByLimit(limit);
        return translator.translateToTrends(projections);
    }

    public void logging(String keyword) {
        final TrendLogEntity trendLogEntity = TrendLogEntity.from(keyword);
        trendLogRepository.save(trendLogEntity);
    }
}
