package me.dorie.blog.search.blog.domain;

import me.dorie.blog.search.common.Page;

public interface BlogReader {
    Page<Blog> searchBlog(BlogSearchCriteria criteria);
}
