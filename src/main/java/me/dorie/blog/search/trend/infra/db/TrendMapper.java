package me.dorie.blog.search.trend.infra.db;

import me.dorie.blog.search.trend.domain.Trend;
import me.dorie.blog.search.trend.infra.db.jpa.TrendProjection;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface TrendMapper {
    List<Trend> toTrends(List<TrendProjection> trends);

    Trend toTrend(TrendProjection trends);

    Trend toTrend(String keyword, Double searchCount);
}
