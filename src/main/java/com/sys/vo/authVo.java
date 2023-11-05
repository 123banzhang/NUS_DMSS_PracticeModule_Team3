package com.sys.vo;

import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class authVo {
    @NotNull
    public String token;

}
