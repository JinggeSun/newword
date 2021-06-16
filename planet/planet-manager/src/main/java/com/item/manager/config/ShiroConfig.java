package com.item.manager.config;


import com.item.manager.custom.CustomRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * @author zcm
 */
@Configuration
public class ShiroConfig {

    private static final String ALGORITH_NAME = "md5";
    private static final Integer HASH_ITERATIONS = 5 ;

    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultApp = new DefaultAdvisorAutoProxyCreator();
        defaultApp.setProxyTargetClass(true);
        return defaultApp;
    }

    /**
     * 将自己的验证方式加入容器
     * @return
     */
    @Bean
    public CustomRealm myShiroRealm() {
        //设置凭证匹配器，修改为hash凭证匹配器
        HashedCredentialsMatcher myCredentialsMatcher = new HashedCredentialsMatcher();
        //设置算法
        myCredentialsMatcher.setHashAlgorithmName(getAlgorithName());
        //散列次数
        myCredentialsMatcher.setHashIterations(getHashIterations());
        CustomRealm customRealm = new CustomRealm();
        customRealm.setCredentialsMatcher(myCredentialsMatcher);
        return customRealm;
    }

    /**
     * 权限管理，配置主要是Realm的管理认证
     * @return
     */
    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        /**
         * 关闭session功能
         */
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);

        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }

    /**
     * 创建ShiroFilter拦截器
     * @param securityManager
     * @return
     */
    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //配置不拦截路径和拦截路径，顺序不能反
        HashMap<String, String> filterChainMap = new HashMap<>(5);

        //authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问
        filterChainMap.put("/login","anon");
        filterChainMap.put("/logout","logout");
        filterChainMap.put("/**","authc");


        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);
        //覆盖默认的登录url
        shiroFilterFactoryBean.setLoginUrl("/loginVerification");
        return shiroFilterFactoryBean;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    public static Integer getHashIterations() {
        return HASH_ITERATIONS;
    }

    public static String getAlgorithName() {
        return ALGORITH_NAME;
    }
}
