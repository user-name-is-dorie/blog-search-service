package me.dorie.blog.search.blog.infra.api.kakao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.dorie.blog.search.blog.domain.Blog;
import me.dorie.blog.search.blog.domain.BlogReader;
import me.dorie.blog.search.blog.domain.BlogSearchCriteria;
import me.dorie.blog.search.common.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class KakaoApiBlogReader implements BlogReader {
    private final KakaoApiClient kakaoApiCaller;
    private final KakaoApiDtoMapper mapper;

    @Override
    public Page<Blog> searchBlog(BlogSearchCriteria criteria) {
        log.info("KakaoApiBlogReader.searchBlog");
        final KakaoApiDto.BlogSearchRequest request = mapper.toRequest(criteria);
        final KakaoApiDto.BlogSearchResponse response = kakaoApiCaller.searchBlog(request);
        final List<Blog> blogs = mapper.toBlogs(response.getDocuments());
        final KakaoApiDto.BlogSearchResponse.Meta meta = response.getMeta();
        return new Page<>(blogs, meta.getTotalCount(), meta.getPageableCount(), meta.isEnd());
    }
}
