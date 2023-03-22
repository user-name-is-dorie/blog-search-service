package me.dorie.blog.search.blog.infra.api;

import me.dorie.blog.search.blog.domain.BlogSearchCriteria;
import me.dorie.blog.search.blog.infra.api.naver.NaverApiBlogReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NaverApiBlogReaderTest {

    @Autowired
    NaverApiBlogReader naverApiBlogReader;

    @Test
    void 네이버_API_를_통해_블로그를_조회할_수_있다() {
        final BlogSearchCriteria criteria = BlogSearchCriteria.builder()
                .keyword("미움받을 용기")
                .page(1)
                .sort(BlogSearchCriteria.Sort.ACCURACY.name())
                .size(5)
                .build();

        Assertions.assertDoesNotThrow(() -> naverApiBlogReader.searchBlog(criteria));
    }
}
