package com.ms.blog.config;

import com.ms.blog.handler.IPBlockInterceptor;
import com.ms.blog.handler.IpUrlLimitInterceptor;
import com.ms.blog.handler.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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

    /**
     * 登录拦截器，统一解析token
     */
    @Autowired
    private LoginInterceptor loginInterceptor;

    /**
     * ip+url重复请求现在拦截器
     */
    @Autowired
    private  IpUrlLimitInterceptor ipUrlLimitInterceptor;

    /**
     * 识别恶意请求，进行反爬虫
     */
    @Autowired
    private IPBlockInterceptor ipBlockInterceptor;


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //处理跨域配置
        registry.addMapping("/**").allowedOrigins("http://localhost:8080");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //假设拦截test接口后续实际遇到拦截的接口是时，再配置真正的拦截接口
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/login/*")
                .excludePathPatterns("/login","/currentUser")
                .excludePathPatterns("/","/login","/css/**","/fonts/**","/images/**","/js/**","/aa/**");

        //解决 SpringBoot 接口恶意刷新和暴力请求！
        registry.addInterceptor(ipUrlLimitInterceptor).addPathPatterns("/**");
        //识别恶意请求，进行反爬虫
        registry.addInterceptor(ipBlockInterceptor).addPathPatterns("/**");



    }






}
