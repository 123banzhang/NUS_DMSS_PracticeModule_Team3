package com.sys.utils.interceptor;

import com.sys.entity.User;
import com.sys.vo.AuthVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "service", url = "auth-service-lb:8080")
public interface authApi {
    @PostMapping("/auth/verify")
    User verifyUser(@RequestBody AuthVo auth);
}