package com.ms.blog.pojo.vo;

import lombok.Data;

/**
 * @author STEEZ
 * @version 1.0
 * @date 2022/4/25 21:45
 */
@Data
public class UserVo {

    private String id;

    private String account;

    private Integer admin;

    private String nickname;

    private String password;

    private String salt;
}
