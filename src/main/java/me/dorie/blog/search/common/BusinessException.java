package me.dorie.blog.search.common;


import lombok.Getter;

import java.util.Arrays;

@Getter
public class BusinessException extends RuntimeException {

    private final ErrorCode errorCode;
    private final String message;

    public BusinessException(ErrorCode errorCode, Object... args) {
        this.errorCode = errorCode;
        this.message = errorCode.getErrorMsg(Arrays.stream(args).map(String::valueOf).toArray(String[]::new));
    }
}
