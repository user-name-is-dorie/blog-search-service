package me.dorie.blog.search.blog.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.ZonedDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
@ToString
public class Blog {
    private String title;
    private String contents;
    private String url;
    private String blogname;
    private String thumbnail;
    private ZonedDateTime datetime;
}
