package com.ms.blog.config;

import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.server.session.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.*;

/**
 * @PackageName com.ms.blog.config
 * @className StatelessSessionManager
 * @Author :Wud
 * @CreateDate 2022/5/9 9:59
 * @Desc
 */
public class StatelessSessionManager extends DefaultWebSessionManager {

    /**
     * 这个是服务端要返回给客户端，
     */
    public final static String TOKEN_NAME = "TOKEN";
    /**
     * 这个是客户端请求给服务端带的header
     */
    public final static String HEADER_TOKEN_NAME = "token";
    public final static Logger LOG = LoggerFactory.getLogger(StatelessSessionManager.class);



}
