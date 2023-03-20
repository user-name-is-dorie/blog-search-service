package me.dorie.blog.search.blog.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BlogSearchEvent {
    private final String keyword;

    @Builder(access = AccessLevel.PRIVATE)
    private BlogSearchEvent(String keyword) {
        this.keyword = keyword;
    }

    public static BlogSearchEvent from(BlogSearchCriteria criteria) {
        return BlogSearchEvent.builder()
                .keyword(criteria.getKeyword())
                .build();
    }
}
