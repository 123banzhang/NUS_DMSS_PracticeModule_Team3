package com.sys.controller;

import com.sys.entity.User;
import com.sys.service.IUserService;
import com.sys.vo.AuthVo;
import com.sys.vo.LoginVo;
import com.sys.vo.RegisterVo;
import com.sys.vo.RespBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@RequestMapping("/auth")
@Slf4j
@CrossOrigin
public class AuthController {
    @Resource
    private IUserService userService;

//    @RequestMapping("/toLogin")
//    public String toLogin() {
//        return "login";
//    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public RespBean login(@RequestBody LoginVo loginVo) {
        return userService.login(loginVo);
    }

    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    @ResponseBody
    public User verify(@RequestBody AuthVo authVo) {
        return userService.verify(authVo.getToken());
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public RespBean register(@RequestBody RegisterVo registerVo) {
        return userService.register(registerVo);
    }
}
