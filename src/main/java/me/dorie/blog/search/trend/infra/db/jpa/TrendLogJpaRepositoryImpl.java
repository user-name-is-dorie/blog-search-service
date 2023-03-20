package me.dorie.blog.search.trend.infra.db.jpa;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static me.dorie.blog.search.trend.infra.db.jpa.QTrendLogEntity.trendLogEntity;


@Repository
@RequiredArgsConstructor
public class TrendLogJpaRepositoryImpl implements TrendLogJpaRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public List<TrendProjection> getTrendProjectionsByLimit(int limit) {
        return queryFactory
                .from(trendLogEntity)
                .groupBy(trendLogEntity.keyword)
                .select(new QTrendProjection(
                                trendLogEntity.keyword,
                                trendLogEntity.keyword.count()
                        )
                )
                .orderBy(trendLogEntity.keyword.count().desc())
                .limit(limit)
                .fetch();
    }
}
