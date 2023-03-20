package me.dorie.blog.search.blog.ui.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "검색 요청 스키마")
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
public class BlogSearchPageRequest {

    @NotNull
    @Schema(description = "검색을 원하는 질의어", example = "이효리")
    private String keyword;

    @Schema(description = "결과 문서 정렬 방식, accuracy(정확도순) 또는 recency(최신순), 기본 값 accuracy", example = "accuracy")
    private String sort;

    @Schema(description = "결과 페이지 번호, 1~50 사이의 값, 기본 값 1", example = "1")
    @Min(1)
    @Max(50)
    @Builder.Default
    private Integer page = 1;

    @Schema(description = "한 페이지에 보여질 문서 수, 1~50 사이의 값, 기본 값 10", example = "10")
    @Min(1)
    @Max(50)
    @Builder.Default
    private Integer size = 10;
}
