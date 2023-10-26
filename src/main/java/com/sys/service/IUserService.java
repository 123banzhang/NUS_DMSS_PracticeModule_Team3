package com.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sys.entity.User;
import com.sys.vo.LoginVo;
import com.sys.vo.RegisterVo;
import com.sys.vo.RespBean;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author zhijian-wang
 * @since 2023-10-08
 */
public interface IUserService extends IService<User> {

//    RespBean login(LoginVo loginVo, HttpServletRequest request);

    RespBean login(LoginVo loginVo);

    User verify(String token);

    RespBean register(RegisterVo registerVo);
}
