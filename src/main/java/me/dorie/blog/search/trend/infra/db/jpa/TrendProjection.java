package me.dorie.blog.search.trend.infra.db.jpa;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TrendProjection {
    private String keyword;
    private Long searchCount;

    @QueryProjection
    public TrendProjection(String keyword, Long searchCount) {
        this.keyword = keyword;
        this.searchCount = searchCount;
    }
}
