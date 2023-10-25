package com.sys.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum RespBeanEnum {
    // 通用
        SUCCESS(200, "SUCCESS"),
        ERROR(500, "服务端异常"),
    // 登陆
        LOGIN_ERROR(500210, "用户名或者密码不正确"),
        MOBILE_ERROR(500211, "手机号码格式不正确"),
        BIND_ERROR(500212, "参数校验异常"),
    //注册
    REGISTRATION_ERROR(500213, "注册失败"),
    // metahuman 模块
    METAHUMAN_NOT_FOUND(500310, "Metahuman not found"),
    METAHUMAN_UPDATE_FAILED(500311, "Failed to update Metahuman"),
    METAHUMAN_CREATE_FAIL(500312, "Failed to create metahuman."),
    METAHUMAN_DELETE_FAIL(500313, "Failed to delete metahuman.");

        private final Integer code;
        private final String message;

}
