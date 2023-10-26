package com.sys.utils.interceptor;

import com.sys.vo.RespBean;
import com.sys.vo.RespBeanEnum;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sys.authApi;
import com.sys.vo.authVo;
import com.sys.entity.User;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    private ObjectProvider<authApi> remoteServiceProvider;

    // 构造函数中注入ObjectProvider
    @Autowired
    public AuthInterceptor(ObjectProvider<authApi> remoteServiceProvider) {
        this.remoteServiceProvider = remoteServiceProvider;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 当您需要使用Feign客户端时，从ObjectProvider获取它
        authApi remoteServiceClient = remoteServiceProvider.getIfAvailable();
        authVo authReq = new authVo();
        authReq.token = request.getHeader("AuthToken");
        User userInfo = remoteServiceClient.verifyUser(authReq);
        if (userInfo == null) {
            response.setContentType("application/json");
            response.getWriter().write(RespBean.error(RespBeanEnum.LOGIN_ERROR).toString());
            return false;
        }
        request.setAttribute("userInfo", userInfo);
        return true;
    }


}
