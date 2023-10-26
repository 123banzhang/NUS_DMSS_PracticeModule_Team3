package com.sys.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class RegisterVo {
    @NotNull
    private String mobile;

    @NotNull
    @Length(min = 32)
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
