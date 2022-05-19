package com.ms.blog.config;

import com.ms.blog.filter.RepeatableFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @PackageName com.ms.blog.config
 * @className CommonConfig
 * @Author :Wud
 * @CreateDate 2022/5/19 11:06
 * @Desc    配置 装饰者模式对 HttpServletRequest 的功能进行增强的拦截器
 */
@Configuration
public class CommonConfig {

    @Bean
    FilterRegistrationBean<RepeatableFilter> repeatableFilterBean() {
        FilterRegistrationBean<RepeatableFilter> bean = new FilterRegistrationBean<>();
        bean.addUrlPatterns("/*");
        bean.setFilter(new RepeatableFilter());
        return bean;
    }


}
