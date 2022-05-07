package com.ms.blog.service;

import com.ms.blog.common.ServerResponse;
import com.ms.blog.param.LoginParam;
import com.ms.blog.pojo.SysUser;

/**
 * @author STEEZ
 * @version 1.0
 * @date 2022/4/25 22:53
 */
public interface LoginService {


    ServerResponse login(LoginParam loginParam);

    SysUser checkToken(String token);
}
