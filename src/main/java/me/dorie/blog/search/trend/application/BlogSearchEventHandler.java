package me.dorie.blog.search.trend.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.dorie.blog.search.blog.domain.BlogSearchEvent;
import me.dorie.blog.search.trend.domain.TrendCreateCommand;
import me.dorie.blog.search.trend.domain.TrendService;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BlogSearchEventHandler {
    private final TrendService trendService;

    @Async
    @EventListener
    public void createTrend(BlogSearchEvent event) {
        log.info("BlogSearchEventHandler.createTrend");
        trendService.createTrend(TrendCreateCommand.from(event));
    }
}
