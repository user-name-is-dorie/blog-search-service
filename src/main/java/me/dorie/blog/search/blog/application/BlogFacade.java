package me.dorie.blog.search.blog.application;

import lombok.RequiredArgsConstructor;
import me.dorie.blog.search.blog.domain.Blog;
import me.dorie.blog.search.blog.domain.BlogSearchCriteria;
import me.dorie.blog.search.blog.domain.BlogSearchEvent;
import me.dorie.blog.search.blog.domain.BlogSearchPage;
import me.dorie.blog.search.blog.domain.BlogService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BlogFacade {
    private final BlogService blogSearchService;
    private final ApplicationEventPublisher eventPublisher;

    public BlogSearchPage<Blog> searchBlog(BlogSearchCriteria criteria) {
        final BlogSearchPage<Blog> result = blogSearchService.searchBlog(criteria);
        eventPublisher.publishEvent(BlogSearchEvent.from(criteria));
        return result;
    }
}
