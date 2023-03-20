package me.dorie.blog.search.trend.infra.db.redis;

import me.dorie.blog.search.trend.domain.Trend;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface TrendRedisTranslator {
    Trend translateToTrend(String keyword, Double searchCount);
}
