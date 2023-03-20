package me.dorie.blog.search.trend.infra.db.jpa;

import java.util.List;

public interface TrendLogJpaRepositoryCustom {
    List<TrendProjection> getTrendProjectionsByLimit(int limit);
}
