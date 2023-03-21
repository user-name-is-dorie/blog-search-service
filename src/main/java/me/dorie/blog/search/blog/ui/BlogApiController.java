package me.dorie.blog.search.blog.ui;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.dorie.blog.search.blog.application.BlogFacade;
import me.dorie.blog.search.blog.domain.Blog;
import me.dorie.blog.search.blog.domain.BlogSearchCriteria;
import me.dorie.blog.search.blog.domain.BlogSearchPage;
import me.dorie.blog.search.blog.ui.dto.BlogSearchPageRequest;
import me.dorie.blog.search.blog.ui.dto.BlogSearchPageResponse;
import me.dorie.blog.search.blog.ui.dto.BlogSearchResponse;
import me.dorie.blog.search.common.CommonResponse;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "블로그 API")
@RequestMapping("/v1/search/blog")
@RestController
@RequiredArgsConstructor
public class BlogApiController {
    private final BlogFacade blogSearchFacade;
    private final BlogSearchDtoMapper mapper;

    @Operation(description = "블로그 검색하기")
    @GetMapping
    public CommonResponse<BlogSearchPageResponse<BlogSearchResponse>> searchBlog(
            @ParameterObject @Valid BlogSearchPageRequest request
    ) {
        final BlogSearchCriteria criteria = mapper.toCriteria(request);
        final BlogSearchPage<Blog> result = blogSearchFacade.searchBlog(criteria);
        return CommonResponse.success(BlogSearchPageResponse.from(result, mapper::toResponse));
    }
}
