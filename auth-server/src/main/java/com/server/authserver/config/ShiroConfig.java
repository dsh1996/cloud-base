package com.server.authserver.config;

import com.server.authserver.shiro.AuthRealm;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Configuration
public class ShiroConfig {

    public ShiroConfig() {
        log.info("-------------初始化shiro 权限配置中... -------------- ");
    }

    /**
     * 密码加密
     *
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        // 散列算法（加密）
        credentialsMatcher.setHashAlgorithmName("MD5");
        // 散列次数（加密次数）
        credentialsMatcher.setHashIterations(1024);
        // storedCredentialsHexEncoded 默认是true，此时用的是密码加密用的是Hex编码；false时用Base64编码
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return credentialsMatcher;
    }

    @Bean
    public SecurityManager securityManager(AuthRealm authRealm, HashedCredentialsMatcher matcher) {
        DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
        authRealm.setCredentialsMatcher(matcher);
        defaultSecurityManager.setRealm(authRealm);
        return defaultSecurityManager;
    }

    /**
     * 配置自定义权限过滤规则
     */
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/login", "anon");
        filterMap.put("/register", "anon");
        filterMap.put("/v2/api-docs", "anon");
        filterMap.put("/v2/api-docs-ext", "anon");
        filterMap.put("/swagger-resources", "anon");
        filterMap.put("/user/init", "anon");
        filterMap.put("/webjars/**", "anon");
        filterMap.put("/*.html", "anon");
        filterMap.put("/*.js", "anon");
        filterMap.put("/*.css", "anon");
        filterMap.put("/*.jpg", "anon");
        filterMap.put("/*.peng", "anon");
        filterMap.put("/**", "authc");
        shiroFilterFactoryBean.setLoginUrl("/unauth");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 开启权限注解
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }


}
