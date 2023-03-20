package me.dorie.blog.search.blog.infra.api.kakao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "KakaoOpenApiFeignClient",
        url = "${open-api.kakao.base-url}",
        configuration = KakaoApiAuthFeignConfig.class
)
public interface KakaoApiFeignClient {

    @GetMapping("/v2/search/blog")
    KakaoApiDto.BlogSearchResponse searchBlog(
            @SpringQueryMap KakaoApiDto.BlogSearchRequest request);
}
