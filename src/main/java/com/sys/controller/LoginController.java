package com.sys.controller;

import com.sys.service.IUserService;
import com.sys.vo.LoginVo;
import com.sys.vo.RespBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
@Slf4j
@CrossOrigin
public class LoginController {
    @Resource
    private IUserService userService;

//    @RequestMapping("/toLogin")
//    public String toLogin() {
//        return "login";
//    }

    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    @ResponseBody
    public RespBean doLogin(@RequestBody LoginVo loginVo, HttpServletRequest request) {
        return userService.login(loginVo, request);
    }
}
