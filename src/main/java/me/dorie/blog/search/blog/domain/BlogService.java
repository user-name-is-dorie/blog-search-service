package me.dorie.blog.search.blog.domain;

public interface BlogService {
    BlogSearchPage<Blog> searchBlog(BlogSearchCriteria criteria);
}
