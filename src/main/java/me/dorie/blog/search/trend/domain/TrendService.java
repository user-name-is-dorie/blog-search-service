package me.dorie.blog.search.trend.domain;

import java.util.List;

public interface TrendService {
    List<Trend> getTrends();

    void createTrend(TrendCreateCommand command);
}
