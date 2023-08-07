package com.ssafy.ssuk.utils.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessCode {
    /* 예시 */
    SUCCESS_EXAMPLE(HttpStatus.OK, "대충 성공했단 메시지"),


    SUCCESS_FCM(HttpStatus.OK, "OK");
    ;

    private final HttpStatus httpStatus;
    private final String message;

}
