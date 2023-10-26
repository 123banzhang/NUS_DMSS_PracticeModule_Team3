package com.sys.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AuthVo {
    @NotNull
    public String token;

}
