package me.dorie.blog.search.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> {
    private Result result;
    private T data;
    private String message;
    private String errorCode;

    public static <T> CommonResponse<T> success(T data, String message) {
        return CommonResponse.<T>builder()
                .result(Result.SUCCESS)
                .data(data)
                .message(message)
                .build();
    }

    public static <T> CommonResponse<T> success(T data) {
        return success(data, null);
    }

    public static <T> CommonResponse<T> fail(String message, ErrorCode errorCode) {
        return CommonResponse.<T>builder()
                .result(Result.FAIL)
                .message(message)
                .errorCode(errorCode.getCode())
                .build();
    }

    public enum Result {
        SUCCESS, FAIL;

        public boolean isSuccess() {
            return this == SUCCESS;
        }
    }
}
