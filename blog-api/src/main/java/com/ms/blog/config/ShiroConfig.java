package com.ms.blog.config;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.session.DefaultWebSessionManager;

/**
 * @PackageName com.ms.blog.config
 * @className ShiroConfig
 * @Author :Wud
 * @CreateDate 2022/5/7 18:45
 * @Desc
 */
@Configuration
public class ShiroConfig {

//    @Autowired
//    private JwtFilter jwtFilter;

//    @Bean
//    public SessionManager sessionManager(RedisSessionDAO redisSessionDAO) {
//        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
//        sessionManager.setSessionDAO(redisSessionDAO);
//        return sessionManager;
//    }
//    @Bean
//    public DefaultWebSecurityManager securityManager(AccountRealm accountRealm,
//                                                     SessionManager sessionManager,
//                                                     RedisCacheManager redisCacheManager) {
//        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager(accountRealm);
//        securityManager.setSessionManager(sessionManager);
//        securityManager.setCacheManager(redisCacheManager);
//        /*
//         * 关闭shiro自带的session，详情见文档
//         */
//        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
//        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
//        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
//        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
//        securityManager.setSubjectDAO(subjectDAO);
//        return securityManager;
//    }
//    @Bean
//    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
//        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
//        Map<String, String> filterMap = new LinkedHashMap<>();
//        filterMap.put("/**", "jwt"); // 主要通过注解方式校验权限
//        chainDefinition.addPathDefinitions(filterMap);
//        return chainDefinition;
//    }
//    @Bean("shiroFilterFactoryBean")
//    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager,
//                                                         ShiroFilterChainDefinition shiroFilterChainDefinition) {
//        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
//        shiroFilter.setSecurityManager(securityManager);
//
//        Map<String, Filter> filters = new HashMap<>();
//        filters.put("jwt", jwtFilter);
//        shiroFilter.setFilters(filters);
//
//        Map<String, String> filterMap = shiroFilterChainDefinition.getFilterChainMap();
//
//        shiroFilter.setFilterChainDefinitionMap(filterMap);
//
//        return shiroFilter;
//    }

//    @Bean
//    public SecurityManager securityManager(){
//        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        securityManager.setSessionManager(new DefaultWebSessionManager());
//        securityManager.setRealm(new WebRealm());
//        return securityManager;
//    }






}
