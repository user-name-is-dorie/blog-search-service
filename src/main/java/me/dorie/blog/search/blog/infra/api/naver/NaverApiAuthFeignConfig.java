package me.dorie.blog.search.blog.infra.api.naver;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class NaverApiAuthFeignConfig {

    @Value("${open-api.naver.client-secret}")
    private String clientSecret;

    @Value("${open-api.naver.client-id}")
    private String clientId;

    @Bean
    public RequestInterceptor naverRequestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("X-Naver-Client-Id", clientId);
            requestTemplate.header("X-Naver-Client-Secret", clientSecret);
        };
    }
}
