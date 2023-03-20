package me.dorie.blog.search.blog.ui;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.dorie.blog.search.blog.application.BlogSearchFacade;
import me.dorie.blog.search.blog.domain.BlogSearchCriteria;
import me.dorie.blog.search.common.Page;
import me.dorie.blog.search.common.PageRequest;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "블로그 API")
@RequestMapping("/v1/search/blog")
@RestController
@RequiredArgsConstructor
public class BlogApiController {
    private final BlogSearchFacade blogSearchFacade;
    private final BlogSearchDtoMapper mapper;

    @Operation(description = "블로그 검색하기")
    @GetMapping
    public Page<SearchBlogResponse> searchBlog(
            @ParameterObject @Valid PageRequest request
    ) {
        final BlogSearchCriteria criteria = mapper.toCriteria(request);
        return blogSearchFacade.searchBlog(criteria)
                .map(mapper::toResponse);
    }
}
