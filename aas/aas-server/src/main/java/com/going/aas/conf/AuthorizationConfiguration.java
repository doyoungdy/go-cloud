package com.going.aas.conf;

import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import com.going.aas.oauth2.JwkTokenEnhancer;

/**
 * 基于spring-oauth2配置授权服务
 * 
 * <pre>
 * 通过继承于AuthorizationServerConfigurerAdapter类,覆盖默认oauth2配置
 * 
 *
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationConfiguration extends AuthorizationServerConfigurerAdapter {

	/**
	 * 用户认证
	 */
	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;
	
    @Autowired
    private DataSource dataSource;
    
    @Autowired
    private TokenStore tokenStore;
    
    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;
	
//    @Autowired
//    private RedisConnectionFactory redisConnectionFactory;
	
//    @Bean
//    RedisTokenStore redisTokenStore(){
//        return new RedisTokenStore(redisConnectionFactory);
//    }
	
	/**
	 * ----------------------------------------------------------
	 * 重载配置令牌端点(Token Endpoint)的安全与权限访问。
	 * ----------------------------------------------------------
	 */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()");
        security.checkTokenAccess("isAuthenticated()");//token
        security.allowFormAuthenticationForClients();//使用/oauth/token支持client_id以及client_secret作登录认证
    }
    
	/**
	 * ----------------------------------------------------------
	 * 用来配置客户端详情信息
	 * ----------------------------------------------------------
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.withClientDetails(clientDetails());
	}
	
	/**
	 * 将客户端信息配置在数据库中
	 * @return
	 */
    @Bean
    public ClientDetailsService clientDetails() {
        return new JdbcClientDetailsService(dataSource);
    }

	/**
	 * ----------------------------------------------------------
	 * 用来配置授权以及令牌（Token）的访问端点和令牌服务（比如：配置令牌的签名与存储方式）
	 * ----------------------------------------------------------
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
	    tokenEnhancerChain.setTokenEnhancers(
	      Arrays.asList(tokenEnhancer(), jwtAccessTokenConverter));
	    
		endpoints.authenticationManager(authenticationManager)//token端点访问需要用户认证
		.tokenStore(tokenStore)//基于jwtToken
		.tokenEnhancer(tokenEnhancerChain)
		.accessTokenConverter(jwtAccessTokenConverter)
		;
	}
	
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new JwkTokenEnhancer();
    }



}