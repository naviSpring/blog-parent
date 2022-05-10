package com.ms.blog.controller;

import com.ms.blog.common.AjaxResult;
import com.ms.blog.service.ThreadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @PackageName com.ms.blog.controller
 * @className ThreadController
 * @Author :Wud
 * @CreateDate 2022/5/11 0:04
 * @Desc
 */
@Slf4j
@RestController
@RequestMapping("thread")
public class ThreadController {

    @Autowired
    private ThreadService threadService;

    @GetMapping("sendMsg")
    public AjaxResult sendMsg(){

        //调用方法由线程池异步执行
        threadService.sendMsg();

        return AjaxResult.success();
    }




}
