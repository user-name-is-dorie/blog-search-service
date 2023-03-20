package me.dorie.blog.search.trend.ui.api;

import me.dorie.blog.search.trend.domain.Trend;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface TrendDtoMapper {
    List<TrendResponse> toResponses(List<Trend> result);
}
