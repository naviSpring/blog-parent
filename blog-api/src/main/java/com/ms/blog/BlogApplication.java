package com.ms.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.sql.DataSource;

/**
 * @author STEEZ
 * @version 1.0
 * @date 2022/4/25 21:02
 */
@SpringBootApplication
public class BlogApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(BlogApplication.class, args);
        DataSource bean = run.getBean(DataSource.class);
        System.out.println(bean.getClass());

    }
}

