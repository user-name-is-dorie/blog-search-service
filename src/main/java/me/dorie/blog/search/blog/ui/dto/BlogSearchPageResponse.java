package me.dorie.blog.search.blog.ui.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.dorie.blog.search.blog.domain.BlogSearchPage;

import java.util.List;
import java.util.function.Function;

@Schema(description = "검색된 페이지네이션 스키마", name = "CommonPageResponse")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
public class BlogSearchPageResponse<T> {
    private List<T> contents;

    @Schema(description = "검색된 문서 수", example = "5")
    private Integer totalCount;

    @Schema(description = "검색된 문서 수 중 노출 가능 문서 수", example = "5")
    private Integer pageableCount;

    @Schema(description = "현재 페이지가 마지막 페이지인지 여부, 값이 false 면 다음 페이지를 요청할 수 있음", example = "true")
    private Boolean isEnd;

    @Builder
    public BlogSearchPageResponse(List<T> contents, Integer totalCount, Integer pageableCount, Boolean isEnd) {
        this.contents = contents;
        this.totalCount = totalCount;
        this.pageableCount = pageableCount;
        this.isEnd = isEnd;
    }

    public static <T, R> BlogSearchPageResponse<T> from(BlogSearchPage<R> page, Function<? super R, T> mapper) {
        final List<T> contents = page.getContents()
                .stream()
                .map(mapper)
                .toList();
        return new BlogSearchPageResponse<>(contents, page.getTotalCount(), page.getPageableCount(), page.getIsEnd());
    }

}
