package me.dorie.blog.search.blog.infra.api.naver;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.dorie.blog.search.blog.domain.Blog;
import me.dorie.blog.search.blog.domain.BlogReader;
import me.dorie.blog.search.blog.domain.BlogSearchCriteria;
import me.dorie.blog.search.blog.domain.BlogSearchPage;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class NaverApiBlogReader implements BlogReader {
    private final NaverApiFeignClient client;
    private final NaverApiTranslator translator;

    @Override
    public BlogSearchPage<Blog> searchBlog(BlogSearchCriteria criteria) {
        final NaverApiDto.BlogSearchRequest request = translator.translateToRequest(criteria);
        final NaverApiDto.BlogSearchResponse response = client.searchBlog(request);
        final List<Blog> blogs = translator.translateToBlogs(response.getItems());
        return applyPagination(criteria, response.getTotal(), blogs);
    }

    private BlogSearchPage<Blog> applyPagination(BlogSearchCriteria criteria, int total, List<Blog> contents) {
        final Integer page = criteria.getPage();
        final Integer size = criteria.getSize();
        final int pageableCount = total / size + (total % size > 0 ? 1 : 0);
        final boolean isEnd = pageableCount == page;
        return new BlogSearchPage<>(contents, total, pageableCount, isEnd);
    }
}
