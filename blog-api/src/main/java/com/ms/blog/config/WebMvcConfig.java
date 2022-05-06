package com.ms.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author STEEZ
 * @version 1.0
 * @date 2022/4/25 21:11
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //处理跨域配置
        registry.addMapping("/**").allowedOrigins("http://localhost:8080");
    }
}
