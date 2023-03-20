package me.dorie.blog.search.trend.domain;

import lombok.Getter;
import lombok.ToString;
import me.dorie.blog.search.blog.domain.BlogSearchEvent;

@Getter
@ToString
public class TrendCreateCommand {
    private final String keyword;

    private TrendCreateCommand(String keyword) {
        this.keyword = keyword;
    }

    public static TrendCreateCommand from(BlogSearchEvent event) {
        return new TrendCreateCommand(event.getKeyword());
    }

    public static TrendCreateCommand from(String keyword) {
        return new TrendCreateCommand(keyword);
    }
}
