package com.ms.blog.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * @author STEEZ
 * @version 1.0
 * @date 2022/4/25 21:36
 */
@Data
@TableName("ms_sys_user")
public class SysUser {

    /**
     * @Author:         STEEZ
     * @CreateDate:     2022/4/25 21:37
     * 分布式雪花id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String account;

    private Integer admin;

    private String avatar;

    private Long createDate;

    private Integer deleted;

    private String email;

    private Long lastLogin;

    private String mobilePhoneNumber;

    private String nickname;

    private String password;

    private String salt;

    private String status;

}
