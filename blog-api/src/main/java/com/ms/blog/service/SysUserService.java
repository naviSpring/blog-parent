package com.ms.blog.service;

import com.ms.blog.pojo.SysUser;

/**
 * @author STEEZ
 * @version 1.0
 * @date 2022/4/25 22:58
 */
public interface SysUserService {
    SysUser findUser(String account, String password);
}
