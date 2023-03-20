package me.dorie.blog.search.blog.infra.api;

import me.dorie.blog.search.blog.infra.api.kakao.KakaoApiBlogReader;
import me.dorie.blog.search.blog.infra.api.naver.NaverApiBlogReader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.HttpServerErrorException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class BlogApiServiceFailCaseTest {

    @Autowired
    BlogApiService blogApiService;

    @MockBean
    KakaoApiBlogReader kakaoApiBlogReader;

    @MockBean
    NaverApiBlogReader naverApiBlogReader;

    @Test
    void 카카오_API_조회가_실패할_경우_네이버_API_를_통해_조회합니다() {
        when(kakaoApiBlogReader.searchBlog(any())).thenThrow(HttpServerErrorException.class);

        // when
        blogApiService.searchBlog(any());

        // then
        verify(naverApiBlogReader, times(1)).searchBlog(any());
    }
}
