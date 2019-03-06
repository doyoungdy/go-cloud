package com.going.aas.conf;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.going.aas.properties.AasProperties;
import com.going.aas.security.LoginFailureHandler;
import com.going.aas.security.UserDetailsServiceImpl;
import com.going.aas.service.RoleService;
import com.going.aas.service.UserService;

/**
 * 基于spring-security配置用户认证<pre>
 * 继承于WebSecurityConfigurerAdapter,修改默认用户认证配置。
 *
 */
@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService; 
	
	@Autowired
	private AasProperties aasProperties;
	
	/**
	 * 重载用户信息加载服务
	 */
	@Bean
	@Override
    public UserDetailsService userDetailsService(){
        return new UserDetailsServiceImpl(userService, roleService);
    }
	
	
	/**
	 * 用户认证密码加密
	 * @return
	 */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    /**
     * 重载用户管理者<pre>
     * 通过自定义的用户表,加载用户信息
     * 对用户的认证密码进行加解密码
     */
    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

	/**
	 * 重载用户认证管理者服务
	 */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(
				aasProperties.getLoginPage(), 
				aasProperties.getLoginProcessUrl(), 
				"/oauth/authorize",
				"/oauth/token"
				).permitAll()//
		.anyRequest().authenticated();
		http.formLogin()//
//		.failureHandler(getAuthenticationFailureHandler())//
		.loginPage(aasProperties.getLoginPage())//
		.loginProcessingUrl(aasProperties.getLoginProcessUrl()).permitAll();
		//
		http.httpBasic().disable();
		http.csrf().disable();
    }
    
//    @Bean
//    public AuthenticationFailureHandler getAuthenticationFailureHandler() throws Exception {
//    	return new LoginFailureHandler();
//    }
    
    
    

}
