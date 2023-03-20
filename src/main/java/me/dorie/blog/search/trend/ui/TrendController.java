package me.dorie.blog.search.trend.ui;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import me.dorie.blog.search.trend.application.TrendFacade;
import me.dorie.blog.search.trend.domain.Trend;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "인기 검색어 API")
@RequestMapping("/v1/trends")
@RestController
@RequiredArgsConstructor
public class TrendController {
    private final TrendFacade trendFacade;
    private final TrendDtoMapper mapper;

    @Operation(description = "인기 검색어 목록")
    @GetMapping
    public List<TrendResponse> getTrends() {
        final List<Trend> result = trendFacade.getTrends();
        return mapper.toResponses(result);
    }
}
