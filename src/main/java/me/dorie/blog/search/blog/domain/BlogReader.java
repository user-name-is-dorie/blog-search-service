package me.dorie.blog.search.blog.domain;

public interface BlogReader {
    BlogSearchPage<Blog> searchBlog(BlogSearchCriteria criteria);
}
