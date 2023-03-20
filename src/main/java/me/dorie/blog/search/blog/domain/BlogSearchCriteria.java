package me.dorie.blog.search.blog.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Locale;
import java.util.Objects;

@Getter
@ToString
public class BlogSearchCriteria {
    private final String keyword;
    private final Sort sort;
    private final Integer page;
    private final Integer size;

    @Builder
    public BlogSearchCriteria(String keyword, Sort sort, Integer page, Integer size) {
        this.keyword = Objects.requireNonNull(keyword, "keyword 는 필수 입니다.");
        this.sort = Objects.requireNonNullElse(sort, Sort.ACCURACY);
        this.page = Objects.requireNonNullElse(page, 1);
        this.size = Objects.requireNonNullElse(size, 50);

        if (0 >= size || size > 50) {
            throw new IllegalArgumentException("size 는 1~50 사이의 값 이어야 합니다. size :: " + size);
        }

        if (0 >= page || page > 50) {
            throw new IllegalArgumentException("page 는 1~50 사이의 값 이어야 합니다. page :: " + page);
        }
    }

    public String getSort() {
        return sort.name().toLowerCase(Locale.ROOT);
    }

    @RequiredArgsConstructor
    public enum Sort {
        ACCURACY("정확도순"),
        RECENCY("최신순");

        private final String description;

    }
}

