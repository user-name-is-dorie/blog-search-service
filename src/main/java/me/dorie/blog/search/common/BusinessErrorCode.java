package me.dorie.blog.search.common;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum BusinessErrorCode implements ErrorCode {

    INVALID_CRITERIA_SORT("결과 문서 정렬 방식는 accuracy 또는 recency 이어야 합니다. 요청값 :: %s"),
    INVALID_CRITERIA_SIZE("결과 페이지 번호는 1~50 사이의 값 이어야 합니다. 요청값 :: %s"),
    INVALID_CRITERIA_PAGE("페이지 번호는 1~50 사이의 값 이어야 합니다. 요청값 :: %s");

    private final String errorMsg;

    public String getErrorMsg(String... args) {
        return String.format(this.errorMsg, (Object[]) args);
    }

    public String getCode() {
        return this.name();
    }
}
