package com.sys.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RegisterVo {
    @NotNull
    private String mobile;

    @NotNull
    private String password;

    @NotNull
    private String nickname;

    private String major;

    /**
     * 头像
     */
    private String head;

    // Add other necessary fields based on your requirements
}
