package com.zcq.travelweb.Configures;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class myWebMvcConfigures implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/tologin").excludePathPatterns("/user/checkcode")
                .excludePathPatterns("/user/getcode");
    }
}
