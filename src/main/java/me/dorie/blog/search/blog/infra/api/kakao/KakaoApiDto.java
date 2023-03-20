package me.dorie.blog.search.blog.infra.api.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KakaoApiDto {

    @Data
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor
    public static class BlogSearchRequest {
        private String query;
        private String sort;
        private Integer page;
        private Integer size;
    }

    @Data
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class BlogSearchResponse {

        @JsonProperty("documents")
        private List<Document> documents;
        @JsonProperty("meta")
        private Meta meta;

        @Data
        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class Document {
            @JsonProperty("datetime")
            private ZonedDateTime datetime;
            @JsonProperty("thumbnail")
            private String thumbnail;
            @JsonProperty("blogname")
            private String blogname;
            @JsonProperty("url")
            private String url;
            @JsonProperty("contents")
            private String contents;
            @JsonProperty("title")
            private String title;
        }

        @Data
        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class Meta {
            @JsonProperty("is_end")
            private boolean isEnd;
            @JsonProperty("pageable_count")
            private int pageableCount;
            @JsonProperty("total_count")
            private int totalCount;
        }
    }
}
