package me.dorie.blog.search.trend.ui.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "인기 검색어 결과 스키마")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class TrendResponse {

    @Schema(description = "검색 키워드")
    private String keyword;

    @Schema(description = "검색어 별로 검색된 횟수")
    private Long searchCount;
}
