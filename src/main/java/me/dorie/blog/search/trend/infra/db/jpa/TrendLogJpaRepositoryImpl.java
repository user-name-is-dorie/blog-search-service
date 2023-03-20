package me.dorie.blog.search.trend.infra.db.jpa;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static me.dorie.blog.search.trend.domain.QTrendLog.trendLog;

@Repository
@RequiredArgsConstructor
public class TrendLogJpaRepositoryImpl implements TrendLogJpaRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public List<TrendProjection> getTrendProjectionsByLimit(int limit) {
        return queryFactory
                .from(trendLog)
                .groupBy(trendLog.keyword)
                .select(new QTrendProjection(
                                trendLog.keyword,
                                trendLog.keyword.count()
                        )
                )
                .orderBy(trendLog.keyword.count().desc())
                .limit(limit)
                .fetch();
    }
}
