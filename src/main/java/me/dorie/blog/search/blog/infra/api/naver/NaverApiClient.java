package me.dorie.blog.search.blog.infra.api.naver;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "NaverOpenApiFeignClient",
        url = "${open-api.naver.base-url}",
        configuration = NaverApiAuthFeignConfig.class
)
public interface NaverApiClient {

    @GetMapping("/v1/search/blog")
    NaverApiDto.BlogSearchResponse searchBlog(
            @SpringQueryMap NaverApiDto.BlogSearchRequest request
    );
}
