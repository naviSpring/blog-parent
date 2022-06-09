package com.ms.blog.handler;

import com.alibaba.fastjson.JSON;
import com.ms.blog.common.AjaxResult;
import com.ms.blog.utils.IpUtils;
import com.ms.blog.utils.RedisUtil;
import com.ms.blog.utils.SpringUtils;
import com.ms.blog.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @package: com.technicalinterest.group.interceptor
 * @className: IpUrlLimitInterceptor
 * @description: ip+url重复请求现在拦截器,解决 SpringBoot 接口恶意刷新和暴力请求！
 * @author:
 * @since: 0.1
 **/
@Slf4j
@Component
public class IpUrlLimitInterceptor implements HandlerInterceptor {

    private RedisUtil getRedisUtil() {
        return  SpringUtils.getBean(RedisUtil.class);
    }

    private static final String LOCK_IP_URL_KEY="lock_ip_";

    private static final String IP_URL_REQ_TIME="ip_url_times_";

    private static final long LIMIT_TIMES=5;

    private static final int IP_LOCK_TIME=60;



    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        log.info("request请求地址uri={},ip={}", httpServletRequest.getRequestURI(), IpUtils.getIpAddr(httpServletRequest));
        if (ipIsLock(IpUtils.getIpAddr(httpServletRequest))){
            log.info("ip访问被禁止={}",IpUtils.getIpAddr(httpServletRequest));
            returnJson(httpServletResponse, JSON.toJSONString(AjaxResult.error()));
            return false;
        }
        if(!addRequestTime(IpUtils.getIpAddr(httpServletRequest),httpServletRequest.getRequestURI())){
            returnJson(httpServletResponse, JSON.toJSONString(AjaxResult.error()));
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }


    /**
     * @Description: 判断ip是否被禁用
     * @author: shuyu.wang
     * @date: 2019-10-12 13:08
     * @param ip
     * @return java.lang.Boolean
     */
    private Boolean ipIsLock(String ip){
        RedisUtil redisUtil=getRedisUtil();
        if(redisUtil.hasKey(LOCK_IP_URL_KEY+ip)){
            return true;
        }
        return false;
    }
    /**
     * @Description: 记录请求次数
     * @author: shuyu.wang
     * @date: 2019-10-12 17:18
     * @param ip
     * @param uri
     * @return java.lang.Boolean
     */
    private Boolean addRequestTime(String ip,String uri){
        String key=IP_URL_REQ_TIME+ip+uri;
        RedisUtil redisUtil=getRedisUtil();
        if (redisUtil.hasKey(key)){
            long time=redisUtil.incr(key,(long)1);
            if (time>=LIMIT_TIMES){
                redisUtil.getLock(LOCK_IP_URL_KEY+ip,ip,IP_LOCK_TIME);
                return false;
            }
        }else {
            redisUtil.getLock(key,(long)1,1);
        }
        return true;
    }

    private void returnJson(HttpServletResponse response, String json) throws Exception {
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(json);
        } catch (IOException e) {
            log.error("LoginInterceptor response error ---> {}", e.getMessage(), e);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }


}
