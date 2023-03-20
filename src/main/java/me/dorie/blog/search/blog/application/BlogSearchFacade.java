package me.dorie.blog.search.blog.application;

import lombok.RequiredArgsConstructor;
import me.dorie.blog.search.blog.domain.Blog;
import me.dorie.blog.search.blog.domain.BlogSearchCriteria;
import me.dorie.blog.search.blog.domain.BlogSearchEvent;
import me.dorie.blog.search.blog.domain.BlogService;
import me.dorie.blog.search.common.Page;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BlogSearchFacade {
    private final BlogService blogSearchService;
    private final ApplicationEventPublisher eventPublisher;

    public Page<Blog> searchBlog(BlogSearchCriteria criteria) {
        final Page<Blog> result = blogSearchService.searchBlog(criteria);
        eventPublisher.publishEvent(BlogSearchEvent.from(criteria));
        return result;
    }
}
