package com.ms.blog.controller;

import com.alibaba.fastjson.JSON;
import com.ms.blog.common.ServerResponse;
import com.ms.blog.param.LoginParam;
import com.ms.blog.pojo.SysUser;
import com.ms.blog.pojo.vo.UserVo;
import com.ms.blog.service.LoginService;
import com.ms.blog.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author STEEZ
 * @version 1.0
 * @date 2022/4/25 21:44
 */
@Slf4j
@RestController
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
    @ResponseBody
    public ServerResponse currentUser(@RequestHeader("Authorization") String token){
        return userService.findUserByToken(token);
    }





}
