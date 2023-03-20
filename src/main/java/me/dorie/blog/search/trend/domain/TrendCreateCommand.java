package me.dorie.blog.search.trend.domain;

import lombok.Getter;
import lombok.ToString;
import me.dorie.blog.search.blog.domain.BlogSearchEvent;

@Getter
@ToString
public class TrendCreateCommand {
    private final String query;

    private TrendCreateCommand(String query) {
        this.query = query;
    }

    public static TrendCreateCommand from(BlogSearchEvent event) {
        return new TrendCreateCommand(event.getQuery());
    }
}
