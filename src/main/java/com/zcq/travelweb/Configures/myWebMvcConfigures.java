package com.zcq.travelweb.Configures;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class myWebMvcConfigures implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new StopWatchHandlerInterceptor());
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                .excludePathPatterns(
                        "/",
                        "/user/toLogin",
                        "/user/checkAccount",
                        "/user/getLogInfo",
                        "/toIndex",
                        "/register/toRegister",
                        "/register/getRcode",
                        "/register/UserRegister",
                        "/register/active",
                        "/register/activeEmail",
                        "/toroutelist",
                        "/toroutedetail",
                        "/favorite/getFavoriteRank",
                        "/favorite/getFavoriteRank?**",
                        "/favorite/cancelFavorite",
                        "/favorite/addFavorite",
                        "/user/getcode",
                        "/**/*.jpg",
                        "/**/*.js",
                        "/**/*.css",
                        "/**/*.png",
                        "/**/*.jpeg",
                        "/**/*.eot",
                        "/**/*.svg",
                        "/**/*.ttf",
                        "/**/*.woff",
                        "/**/*.woff2");
    }
}
