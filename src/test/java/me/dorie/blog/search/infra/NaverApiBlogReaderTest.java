package me.dorie.blog.search.infra;

import me.dorie.blog.search.blog.domain.BlogSearchCriteria;
import me.dorie.blog.search.blog.infra.api.naver.NaverApiBlogReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NaverApiBlogReaderTest {

    @Autowired
    NaverApiBlogReader naverApiBlogReader;

    @DisplayName("네이버 API 를 통해서 블로그를 검색 할 수 있다.")
    @Test
    void searchBlog() {
        final BlogSearchCriteria criteria = BlogSearchCriteria.builder()
                .query("미움받을 용기")
                .page(1)
                .sort("accuracy")
                .size(5)
                .build();

        Assertions.assertDoesNotThrow(() -> naverApiBlogReader.searchBlog(criteria));
    }
}
