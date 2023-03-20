package me.dorie.blog.search.trend.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class Trend {
    private final String keyword;
    private final Long searchCount;
}
