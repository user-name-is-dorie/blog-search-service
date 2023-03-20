package me.dorie.blog.search.trend.domain;

import java.util.List;

public interface TrendReader {
    List<Trend> getTrendsByLimit(int limit);
}
