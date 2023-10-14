package com.sys.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class LoginVo {
    @NotNull
    private String mobile;

    @NotNull
    @Length(min = 32)
    private String password;
}
