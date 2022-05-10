package com.ms.blog.utils;

import com.ms.blog.pojo.SysUser;

/**
 * @PackageName com.ms.blog.utils
 * @className UserThreadLocal
 * @Author :Wud
 * @CreateDate 2022/5/9 9:09
 * @Desc
 */
public class UserThreadLocal {
    private UserThreadLocal(){}
    //线程变量隔离
    private static final ThreadLocal<SysUser> LOCAL = new ThreadLocal<>();

    public static void put(SysUser sysUser){
        LOCAL.set(sysUser);
    }
    public static SysUser get(){
        return LOCAL.get();
    }
    public static void remove(){
        LOCAL.remove();
    }
}
