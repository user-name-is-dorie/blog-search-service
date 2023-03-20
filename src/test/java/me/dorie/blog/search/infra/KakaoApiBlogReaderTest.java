package me.dorie.blog.search.infra;

import me.dorie.blog.search.blog.domain.BlogSearchCriteria;
import me.dorie.blog.search.blog.infra.api.kakao.KakaoApiBlogReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KakaoApiBlogReaderTest {

    @Autowired
    KakaoApiBlogReader kakaoApiBlogReader;

    @Test
    void 카카오_API_를_통해_블로그를_조회할_수_있다() {
        final BlogSearchCriteria criteria = BlogSearchCriteria.builder()
                .keyword("미움받을 용기")
                .page(1)
                .sort(BlogSearchCriteria.Sort.ACCURACY)
                .size(5)
                .build();

        Assertions.assertDoesNotThrow(() -> kakaoApiBlogReader.searchBlog(criteria));
    }
}
