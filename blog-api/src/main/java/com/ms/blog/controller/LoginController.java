package com.ms.blog.controller;

import com.ms.blog.common.ServerResponse;
import com.ms.blog.param.LoginParam;
import com.ms.blog.pojo.SysUser;
import com.ms.blog.pojo.vo.UserVo;
import com.ms.blog.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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





}
