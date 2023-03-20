package me.dorie.blog.search.blog.infra.api.kakao;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class KakaoApiAuthFeignConfig {

    @Value("${open-api.kakao.api-key}")
    private String apiKey;

    @Bean
    public RequestInterceptor kakaoRequestInterceptor() {
        return requestTemplate -> requestTemplate.header("Authorization", apiKey);
    }
}
