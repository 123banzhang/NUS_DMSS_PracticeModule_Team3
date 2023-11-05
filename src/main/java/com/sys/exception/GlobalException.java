package com.sys.exception;

import com.sys.vo.RespBeanEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 全局异常
 *
 * @author: LC
 * @date 2022/3/2 5:32 下午
 * @ClassName: GlobalException
 */

@AllArgsConstructor
public class GlobalException extends RuntimeException {

    private RespBeanEnum respBeanEnum;
    public GlobalException() {
        super();
    }
    public RespBeanEnum getRespBeanEnum() {
        return respBeanEnum;
    }

}
