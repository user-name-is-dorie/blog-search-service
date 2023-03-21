package me.dorie.blog.search.common;

public interface ErrorCode {

    String getErrorMsg(String... params);

    String getCode();
}
