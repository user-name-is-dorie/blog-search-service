package me.dorie.blog.search.blog.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
@Builder
public class BlogSearchCriteria {
    private final String query;
    private final String sort;
    private final Integer page;
    private final Integer size;
}
