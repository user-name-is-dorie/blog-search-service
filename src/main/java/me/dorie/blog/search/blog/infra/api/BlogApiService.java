package me.dorie.blog.search.blog.infra.api;

import me.dorie.blog.search.blog.domain.Blog;
import me.dorie.blog.search.blog.domain.BlogSearchCriteria;
import me.dorie.blog.search.blog.domain.BlogService;
import me.dorie.blog.search.blog.infra.api.kakao.KakaoApiBlogReader;
import me.dorie.blog.search.blog.infra.api.naver.NaverApiBlogReader;
import me.dorie.blog.search.common.Page;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

@Service
public class BlogApiService implements BlogService {
    private final KakaoApiBlogReader kakaoApiBlogReader;
    private final NaverApiBlogReader naverApiBlogReader;
    private final CircuitBreaker searchBlogCircuitBreaker;

    public BlogApiService(
            KakaoApiBlogReader kakaoApiBlogReader,
            NaverApiBlogReader naverApiBlogReader,
            CircuitBreakerFactory circuitBreakerFactory
    ) {
        this.searchBlogCircuitBreaker = circuitBreakerFactory.create("searchBlog");
        this.kakaoApiBlogReader = kakaoApiBlogReader;
        this.naverApiBlogReader = naverApiBlogReader;
    }

    @Override
    public Page<Blog> searchBlog(BlogSearchCriteria criteria) {
        return searchBlogCircuitBreaker.run(
                () -> kakaoApiBlogReader.searchBlog(criteria),
                throwable -> naverApiBlogReader.searchBlog(criteria)
        );
    }
}
