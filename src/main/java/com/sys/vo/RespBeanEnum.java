package com.sys.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum RespBeanEnum {
    // General
    SUCCESS(200, "SUCCESS"),
    ERROR(500, "Service Error"),
    // 登陆
    LOGIN_ERROR(500210, "Username or password error."),
    MOBILE_ERROR(500211, "Wrong phone number format."),
    BIND_ERROR(500212, "Validate param failed.");

    private final Integer code;
    private final String message;

}
