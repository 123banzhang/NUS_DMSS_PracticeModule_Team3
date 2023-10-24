package com.sys.utils.interceptor;

import com.sys.vo.RespBean;
import com.sys.vo.RespBeanEnum;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("user");

        if (user == null) {
            response.setContentType("application/json");
            response.getWriter().write(RespBean.error(RespBeanEnum.LOGIN_ERROR).toString());
            return false;
        }
        return true;
    }
}
