package com.sys.controller;

import com.sys.service.IUserService;
import com.sys.vo.LoginVo;
import com.sys.vo.RespBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {
    @Resource
    private IUserService userService;

    /**
     * 跳转登录页 *
     *
     * @return
     */
    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    /**
     * 登录
     *
     * @return
     */
    @RequestMapping("/doLogin")
    @ResponseBody
    public RespBean doLogin(LoginVo loginVo) {
        log.info(loginVo.toString());
        System.out.println(userService.login(loginVo));
        return userService.login(loginVo);
    }
}