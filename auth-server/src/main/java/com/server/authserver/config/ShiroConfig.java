package com.server.authserver.config;

import com.server.authserver.shiro.AuthFilter;
import com.server.authserver.shiro.AuthRealm;
import com.server.authserver.shiro.ShiroProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.Resource;
import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Configuration
public class ShiroConfig {

    public ShiroConfig() {
        log.info("-------------初始化shiro 权限配置中... -------------- ");
    }


    @Resource
    private ShiroProperties shiroProperties;

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
        Map<String, String> filterChainDefinitionMap = Objects.isNull(shiroProperties.getFilterPath()) ?
                new LinkedHashMap<>(2) : shiroProperties.getFilterPath();
        filterChainDefinitionMap.put("/**", "authFilter");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        Map<String, Filter> filterMap = new LinkedHashMap<>(1);
        filterMap.put("authFilter", new AuthFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
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
