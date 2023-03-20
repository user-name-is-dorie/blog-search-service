package me.dorie.blog.search.blog.infra.api.naver;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NaverApiDto {

    @Builder
    @Data
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor
    public static class BlogSearchRequest {
        private String query;
        private Integer display;
        private Integer start;
        private String sort;
    }

    @Data
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor
    public static class BlogSearchResponse {
        @JsonProperty("items")
        private List<Item> items;
        @JsonProperty("display")
        private int display;
        @JsonProperty("start")
        private int start;
        @JsonProperty("total")
        private int total;
        @JsonProperty("lastBuildDate")
        private String lastBuildDate;

        @Data
        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        @AllArgsConstructor
        public static class Item {
            @JsonProperty("postdate")
            private String postdate;
            @JsonProperty("bloggerlink")
            private String bloggerlink;
            @JsonProperty("bloggername")
            private String bloggername;
            @JsonProperty("description")
            private String description;
            @JsonProperty("link")
            private String link;
            @JsonProperty("title")
            private String title;
        }
    }


}
