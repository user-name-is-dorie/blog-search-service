package me.dorie.blog.search.trend.application;

import lombok.RequiredArgsConstructor;
import me.dorie.blog.search.trend.domain.Trend;
import me.dorie.blog.search.trend.domain.TrendService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TrendFacade {
    private final TrendService trendService;

    public List<Trend> getTrends() {
        return trendService.getTrends();
    }
}
