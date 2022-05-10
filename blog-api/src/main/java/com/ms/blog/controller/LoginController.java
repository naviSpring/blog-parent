package com.ms.blog.controller;

import com.alibaba.fastjson.JSON;
import com.ms.blog.common.Cache;
import com.ms.blog.common.ServerResponse;
import com.ms.blog.param.LoginParam;
import com.ms.blog.pojo.SysUser;
import com.ms.blog.pojo.vo.UserVo;
import com.ms.blog.service.LoginService;
import com.ms.blog.service.SysUserService;
import com.ms.blog.utils.UserThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author STEEZ
 * @version 1.0
 * @date 2022/4/25 21:44
 */
@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private SysUserService userService;


    @GetMapping(value = {"/","toLogin"})
    public ServerResponse toLogin(){

        SysUser sysUser = new SysUser();
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(sysUser,userVo);
        int i = 1/0;
        return ServerResponse.Success();
    }

    @PostMapping("login")
    public ServerResponse login(@RequestBody LoginParam loginParam){
        return loginService.login(loginParam);
    }

    /**
     * @Author      :Wud
     * @CreateDate  2022/4/27 9:53
     * 获取用户信息
     */
    @RequestMapping(value = "currentUser",method = RequestMethod.GET)
    public ServerResponse currentUser(@RequestHeader("Authorization") String token){
        return userService.findUserByToken(token);
    }

    @PostMapping("getCacheData")
    @Cache(name = "getCache",expire = 60 * 1000)
    public ServerResponse getCacheData(String testId){
        //从线程隔离中获取
        SysUser sysUser = UserThreadLocal.get();

        Map<String,Object> resMap = new HashMap<>();
        List list = new ArrayList();
        list.add(1);
        list.add("1qwe");
        list.add(new DateTime(new Date()).toString("yyyy-MM-dd HH:mm"));
        resMap.put("list",list);
        resMap.put("testId",testId);



        return ServerResponse.Success(resMap);
    }






}
