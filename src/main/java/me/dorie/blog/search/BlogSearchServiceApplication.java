package me.dorie.blog.search;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

import java.time.ZonedDateTime;
import java.util.TimeZone;

@Slf4j
@EnableAsync
@EnableFeignClients
//@EnableJpaRepositories
@SpringBootApplication
public class BlogSearchServiceApplication {

    public static void main(String[] args) {
        initDefaultTimeZone();
        SpringApplication.run(BlogSearchServiceApplication.class, args);
    }

    private static void initDefaultTimeZone() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
        log.info("현재시각 : {}", ZonedDateTime.now());
    }
}
