package com.sys.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

//    private final AuthInterceptor authInterceptor;
//
//    public WebConfig(AuthInterceptor authInterceptor) {
//        this.authInterceptor = authInterceptor;
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        //注册TestInterceptor拦截器
//        InterceptorRegistration registration = registry.addInterceptor(authInterceptor);
//        registration.addPathPatterns("/**"); //所有路径都被拦截
//        registration.excludePathPatterns(    //添加不拦截路径
//                "/auth/**",                    //登录路径
//                "/**/*.html",                //html静态资源
//                "/**/*.js",                  //js静态资源
//                "/**/*.css"                  //css静态资源
//        );
//    }
}

