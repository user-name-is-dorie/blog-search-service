package me.dorie.blog.search.blog.infra.api.kakao;

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
public class KakaoApiBlogReader implements BlogReader {
    private final KakaoApiFeignClient client;
    private final KakaoApiTranslator translator;

    @Override
    public BlogSearchPage<Blog> searchBlog(BlogSearchCriteria criteria) {
        final KakaoApiDto.BlogSearchRequest request = translator.translateToRequest(criteria);
        final KakaoApiDto.BlogSearchResponse response = client.searchBlog(request);
        final List<Blog> blogs = translator.translateToBlogs(response.getDocuments());
        final KakaoApiDto.BlogSearchResponse.Meta meta = response.getMeta();
        return new BlogSearchPage<>(blogs, meta.getTotalCount(), meta.getPageableCount(), meta.isEnd());
    }
}
