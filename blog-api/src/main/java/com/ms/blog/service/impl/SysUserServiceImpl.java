package com.ms.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ms.blog.common.ServerResponse;
import com.ms.blog.enums.ErrorCode;
import com.ms.blog.mapper.SysUserMapper;
import com.ms.blog.pojo.SysUser;
import com.ms.blog.pojo.vo.UserVo;
import com.ms.blog.service.LoginService;
import com.ms.blog.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author STEEZ
 * @version 1.0
 * @date 2022/4/25 22:59
 */
@Slf4j
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private LoginService loginService;

    @Override
    public SysUser findUser(String account, String password) {
        log.info("加密后的密码：{}",password);
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(SysUser::getAccount,account);
        queryWrapper.eq(SysUser::getPassword,password);
        queryWrapper.select(SysUser::getAccount,SysUser::getEmail,SysUser::getNickname,SysUser::getId);
        queryWrapper.last("limit 1");
        return sysUserMapper.selectOne(queryWrapper);
    }

    @Override
    public ServerResponse findUserByToken(String token) {
        SysUser user = loginService.checkToken(token);
        if(user == null){
            return ServerResponse.Error(ErrorCode.TOKEN_ILLEGAL.getMsg());
        }
        UserVo userVO = new UserVo();
        BeanUtils.copyProperties(user,userVO);
        return ServerResponse.Success(userVO);
    }
}
