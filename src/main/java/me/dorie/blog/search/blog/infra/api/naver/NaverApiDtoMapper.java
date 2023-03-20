package me.dorie.blog.search.blog.infra.api.naver;

import me.dorie.blog.search.blog.domain.Blog;
import me.dorie.blog.search.blog.domain.BlogSearchCriteria;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class NaverApiDtoMapper {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    public List<Blog> toBlogs(List<NaverApiDto.BlogSearchResponse.Item> items) {
        return items.stream()
                .map(item -> Blog.builder()
                        .title(item.getTitle())
                        .contents(item.getDescription())
                        .url(item.getBloggerlink())
                        .blogname(item.getBloggername())
                        .thumbnail(null)
                        .datetime(toZonedDateTime(item.getPostdate()))
                        .build())
                .toList();
    }

    private ZonedDateTime toZonedDateTime(String postdate) {
        return ZonedDateTime.of(LocalDate.parse(postdate, DATE_TIME_FORMATTER).atStartOfDay(), ZoneId.systemDefault());
    }

    public NaverApiDto.BlogSearchRequest toRequest(BlogSearchCriteria criteria) {
        final Integer page = criteria.getPage();
        final Integer size = criteria.getSize();
        final int offset = (page - 1) * size;
        return NaverApiDto.BlogSearchRequest.builder()
                .query(criteria.getQuery())
                .start(offset + 1)
                .display(offset + size)
                .sort(convertSort(criteria.getSort()))
                .build();
    }

    private String convertSort(String sort) {
        if (sort == null) return null;
        return switch (sort) {
            case "accuracy" -> "sim";
            case "recency" -> "date";
            default -> null;
        };
    }
}
