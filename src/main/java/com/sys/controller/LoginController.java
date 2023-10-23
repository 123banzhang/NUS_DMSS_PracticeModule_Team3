package com.sys.controller;

import com.sys.service.IUserService;
import com.sys.vo.LoginVo;
import com.sys.vo.RespBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
@Slf4j
@CrossOrigin
public class LoginController {
    @Resource
    private IUserService userService;

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/doLogin")
    @ResponseBody
    public RespBean doLogin(LoginVo loginVo, HttpServletRequest request) {
        return userService.login(loginVo, request);
    }
}
