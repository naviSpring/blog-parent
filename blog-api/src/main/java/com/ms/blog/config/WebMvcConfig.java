package com.ms.blog.config;

import com.ms.blog.handler.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author STEEZ
 * @version 1.0
 * @date 2022/4/25 21:11
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //处理跨域配置
        registry.addMapping("/**").allowedOrigins("http://localhost:8080");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //假设拦截test接口后续实际遇到拦截的接口是时，再配置真正的拦截接口
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/login/*").excludePathPatterns("/login","/currentUser");
    }


}
