package com.going.aas.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * 基于spring-oauth2配置资源访问安全配置
 * 
 * <pre>
 * 通过继承于ResourceServerConfigurerAdapter类,覆盖默认oauth2资源访问安全配置
 * 
 */
//@Configuration
//@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
    http
    .authorizeRequests()
    .anyRequest().authenticated().and()
    .requestMatchers().antMatchers("/api/**");
	}

}
