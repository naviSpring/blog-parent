package com.ms.blog.service.impl;

import com.ms.blog.service.ThreadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @PackageName com.ms.blog.service.impl
 * @className ThreadServiceImpl
 * @Author :Wud
 * @CreateDate 2022/5/11 0:08
 * @Desc
 */
@Slf4j
@Service
public class ThreadServiceImpl implements ThreadService {


    @Override
    @Async("taskExecutor")
    public void sendMsg() {
        try {
            Thread.sleep(2000l);
            log.info("---至此---");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
