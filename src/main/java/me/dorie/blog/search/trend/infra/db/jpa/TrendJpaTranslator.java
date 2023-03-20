package me.dorie.blog.search.trend.infra.db.jpa;

import me.dorie.blog.search.trend.domain.Trend;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface TrendJpaTranslator {
    List<Trend> translateToTrends(List<TrendProjection> trends);
}
