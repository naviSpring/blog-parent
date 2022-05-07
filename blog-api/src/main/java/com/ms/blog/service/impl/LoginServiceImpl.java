package com.ms.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.ms.blog.common.ServerResponse;
import com.ms.blog.enums.ErrorCode;
import com.ms.blog.param.LoginParam;
import com.ms.blog.pojo.SysUser;
import com.ms.blog.service.LoginService;
import com.ms.blog.service.SysUserService;
import com.ms.blog.utils.JwtUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author STEEZ
 * @version 1.0
 * @date 2022/4/25 22:53
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Lazy
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    private static final String salt = "Steez!@#";

    public LoginServiceImpl() {
    }

    @Override
    public ServerResponse login(LoginParam loginParam) {
        /**
         * 1.检查参数是否合法
         * 2.用户名密码去数据库查询
         * 3.不存在，登录失败
         * 4.若存在，使用jwt生成token返回给前端
         * 5.将token放入redis，token：user+过期时间
         */
        if(StringUtils.isBlank(loginParam.getAccount()) || StringUtils.isBlank(loginParam.getPassword())){
            return ServerResponse.Error(ErrorCode.PARAMS_ERROR.getMsg());
        }
        SysUser sysUser =  sysUserService.findUser(loginParam.getAccount(), DigestUtils.md5Hex(loginParam.getPassword()+salt));
        if(sysUser == null){
            return ServerResponse.Error(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }
        String token = JwtUtil.createToken(sysUser.getId());
        redisTemplate.opsForValue().set("TOKEN_"+token, JSON.toJSONString(sysUser),100, TimeUnit.DAYS);
        return ServerResponse.Success(token);
    }

    @Override
    public SysUser checkToken(String token) {
        if(StringUtils.isBlank(token)){
            return null;
        }
        Map<String, Object> stringObjectMap = JwtUtil.checkToken(token);
        if(stringObjectMap == null){
            return null;
        }
        String userJson = redisTemplate.opsForValue().get("TOKEN_" + token);
        SysUser sysUser = JSON.parseObject(userJson, SysUser.class);
        return sysUser;
    }
}
