package com.ms.blog.handler;

import com.alibaba.fastjson.JSON;
import com.ms.blog.common.AjaxResult;
import com.ms.blog.utils.IpUtils;
import com.ms.blog.utils.RedisUtil;
import com.ms.blog.utils.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Author      :Wud
 * @CreateDate  2022/5/31 10:31
 * @desc    识别恶意请求，进行反爬虫
 */
@Slf4j
@Component
public class IPBlockInterceptor implements HandlerInterceptor {

    /** 10s内访问50次，认为是刷接口，就要进行一个限制 */
    private static final long TIME = 10;
    private static final long CNT = 50;
    private Object lock = new Object();

    /** 根据浏览器头进行限制 */
    private static final String USERAGENT = "User-Agent";
    private static final String CRAWLER = "crawler";

    private RedisUtil getRedisUtil() {
        return  SpringUtils.getBean(RedisUtil.class);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        synchronized (lock) {
            //第一层是先拦截不合规的浏览器头，比如浏览器头包含有爬虫的信息，全部拦截掉
            boolean checkAgent = checkAgent(request);
            //第二层是一个ip的拦截。如果在10s之内，访问我的接口大于50次，我就认为你是刷接口过快，是一个爬虫。
            //此时我直接存入redis，永不过期，下次直接拦截掉
            boolean checkIP = checkIP(request, response);
            return checkAgent && checkIP;
        }
    }

    private boolean checkAgent(HttpServletRequest request) {
        String header = request.getHeader(USERAGENT);
        if (StringUtils.isEmpty(header)) {
            return false;
        }
        if (header.contains(CRAWLER)) {
            log.error("请求头有问题，拦截 ==> User-Agent = {}", header);
            return false;
        }
        return true;
    }

    private boolean checkIP(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String ip = IpUtils.getIpAddr(request);
        String url = request.getRequestURL().toString();
        String param = getAllParam(request);
        RedisUtil redisUtil = getRedisUtil();
        boolean isExist = redisUtil.hasKey(ip);
        if (isExist) {
            // 如果存在,直接cnt++
            long cnt = redisUtil.incr(ip,1);
            if (cnt > IPBlockInterceptor.CNT) {
                response.setCharacterEncoding("UTF-8");
                response.setHeader("content-type", "application/json;charset=UTF-8");
                response.getWriter().print(JSON.toJSONString(AjaxResult.error()));
                log.error("ip = {}, 请求过快，被限制", ip);
                // 设置ip不过期 加入黑名单
                redisUtil.set(ip, --cnt);
                return false;
            }
            log.info("ip = {}, {}s之内第{}次请求{}，参数为{}，通过", ip, TIME, cnt, url, param);
        } else {
            // 第一次访问
            redisUtil.set(ip, IPBlockInterceptor.TIME, 1);
            log.info("ip = {}, {}s之内第1次请求{}，参数为{}，通过", ip, TIME, url, param);
        }
        return true;
    }

    private String getAllParam(HttpServletRequest request) {
        Map<String, String[]> map = request.getParameterMap();
        StringBuilder sb = new StringBuilder("[");
        map.forEach((x, y) -> {
            String s = StringUtils.join(y, ",");
            sb.append(x + " = " + s + ";");
        });
        sb.append("]");
        return sb.toString();
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }


}
