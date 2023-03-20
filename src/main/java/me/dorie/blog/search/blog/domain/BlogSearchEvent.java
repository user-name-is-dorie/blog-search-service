package me.dorie.blog.search.blog.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BlogSearchEvent {
    private final String query;

    @Builder(access = AccessLevel.PRIVATE)
    private BlogSearchEvent(String query) {
        this.query = query;
    }

    public static BlogSearchEvent from(BlogSearchCriteria criteria) {
        return BlogSearchEvent.builder()
                .query(criteria.getQuery())
                .build();
    }
}
