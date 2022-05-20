package com.ms.blog;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@Slf4j
//@RunWith(SpringRunner.class)
@SpringBootTest
public class mainTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public void test1(){
        try {
            System.out.println("213234");
            Long aLong = jdbcTemplate.queryForObject("select count(*) from user", Long.class);
            log.info("记录总数：{}",aLong);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
