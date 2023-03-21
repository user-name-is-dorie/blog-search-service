package me.dorie.blog.search.blog.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import me.dorie.blog.search.common.BusinessErrorCode;
import me.dorie.blog.search.common.BusinessException;

import java.util.Arrays;
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
    public BlogSearchCriteria(String keyword, String sort, Integer page, Integer size) {
        this.keyword = Objects.requireNonNull(keyword, "keyword 는 필수 입니다.");
        this.sort = sort == null ? Sort.ACCURACY : Sort.from(sort);
        this.page = Objects.requireNonNullElse(page, 1);
        this.size = Objects.requireNonNullElse(size, 50);

        if (0 >= size || size > 50) {
            throw new BusinessException(BusinessErrorCode.INVALID_CRITERIA_SIZE, size);
        }

        if (0 >= page || page > 50) {
            throw new BusinessException(BusinessErrorCode.INVALID_CRITERIA_PAGE, page);
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

        public static Sort from(String sort) {
            return Arrays.stream(Sort.values())
                    .filter(it -> it.name().equalsIgnoreCase(sort))
                    .findAny()
                    .orElseThrow(() -> new BusinessException(BusinessErrorCode.INVALID_CRITERIA_SORT, sort));
        }
    }
}

