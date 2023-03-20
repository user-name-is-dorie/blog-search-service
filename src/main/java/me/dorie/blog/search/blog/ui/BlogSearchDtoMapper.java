package me.dorie.blog.search.blog.ui;

import me.dorie.blog.search.blog.domain.Blog;
import me.dorie.blog.search.blog.domain.BlogSearchCriteria;
import me.dorie.blog.search.blog.ui.dto.BlogSearchPageRequest;
import me.dorie.blog.search.blog.ui.dto.BlogSearchResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.Locale;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface BlogSearchDtoMapper {
    BlogSearchResponse toResponse(Blog blog);

    BlogSearchCriteria toCriteria(BlogSearchPageRequest request);

    default BlogSearchCriteria.Sort map(String sort) {
        if (sort == null) {
            return null;
        }

        return Enum.valueOf(BlogSearchCriteria.Sort.class, sort.toUpperCase(Locale.ROOT));
    }
}
