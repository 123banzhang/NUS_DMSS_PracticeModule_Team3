package com.sys.utils.interceptor;

import com.sys.entity.User;
import com.sys.service.IUserService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Resource
    private IUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Unauthorized\"}");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        String token = authorizationHeader.substring(7); // Remove "Bearer " prefix
        User user = userService.verify(token);

        if (user == null) {
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Invalid token\"}");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        // Optionally, you could store the user information in the request for use in controllers
        request.setAttribute("user", user);

        return true;
    }
}
