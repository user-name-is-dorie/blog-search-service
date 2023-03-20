package me.dorie.blog.search.blog.infra.api;

import lombok.extern.slf4j.Slf4j;
import me.dorie.blog.search.blog.domain.Blog;
import me.dorie.blog.search.blog.domain.BlogSearchCriteria;
import me.dorie.blog.search.blog.domain.BlogSearchPage;
import me.dorie.blog.search.blog.domain.BlogService;
import me.dorie.blog.search.blog.infra.api.kakao.KakaoApiBlogReader;
import me.dorie.blog.search.blog.infra.api.naver.NaverApiBlogReader;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BlogApiService implements BlogService {
    private final KakaoApiBlogReader kakaoApiBlogReader;
    private final NaverApiBlogReader naverApiBlogReader;
    private final CircuitBreaker searchBlogCircuitBreaker;

    public BlogApiService(
            CircuitBreakerFactory circuitBreakerFactory,
            KakaoApiBlogReader kakaoApiBlogReader,
            NaverApiBlogReader naverApiBlogReader
    ) {
        this.searchBlogCircuitBreaker = circuitBreakerFactory.create("searchBlog");
        this.kakaoApiBlogReader = kakaoApiBlogReader;
        this.naverApiBlogReader = naverApiBlogReader;
    }

    @Override
    public BlogSearchPage<Blog> searchBlog(BlogSearchCriteria criteria) {
        log.info("search blog by : {}", criteria);
        return searchBlogCircuitBreaker.run(
                () -> kakaoApiBlogReader.searchBlog(criteria),
                throwable -> {
                    log.error("카카오 API 바탕으로 블로그 조회가 실패하여 네이버 API 바탕으로 조회합니다.");
                    return naverApiBlogReader.searchBlog(criteria);
                }
        );
    }
}
