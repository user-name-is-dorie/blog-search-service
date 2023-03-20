package me.dorie.blog.search.blog.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
public class BlogSearchPage<T> {
    private List<T> contents;
    private Integer totalCount;
    private Integer pageableCount;
    private Boolean isEnd;

    @Builder
    public BlogSearchPage(List<T> contents, Integer totalCount, Integer pageableCount, Boolean isEnd) {
        this.contents = contents;
        this.totalCount = totalCount;
        this.pageableCount = pageableCount;
        this.isEnd = isEnd;
    }
}
