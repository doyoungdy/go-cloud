package com.going.aas.conf;

import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import com.going.aas.oauth2.CustomTokenEnhancer;
import com.going.aas.oauth2.CustomWebResponseExceptionTranslator;

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
	private AuthenticationManager authenticationManager;
	
    @Autowired
    private DataSource dataSource;
    
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;
    
	/**
	 * ----------------------------------------------------------
	 * 重载配置令牌端点(Token Endpoint)的安全与权限访问。
	 * ----------------------------------------------------------
	 */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    	security.allowFormAuthenticationForClients();//允计通过POST方式在Form中提交客户端信息的方式进行认证
        security.tokenKeyAccess("permitAll()");
        security.checkTokenAccess("isAuthenticated()");//token
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
        final TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer()));
		endpoints
		.tokenEnhancer(tokenEnhancerChain)//
		.tokenStore(redisTokenStore())//
		.authenticationManager(authenticationManager)//token端点访问需要用户认证
		;
		 // 处理 ExceptionTranslationFilter 抛出的异常
//		endpoints.exceptionTranslator(getWebResponseExceptionTranslator());
	}
	
	/**
	 * 注入自定义异常翻译
	 * @return
	 */
//    @Bean
//    public WebResponseExceptionTranslator<OAuth2Exception> getWebResponseExceptionTranslator() {
//        return new CustomWebResponseExceptionTranslator();
//    }
	
	/**
	 * 在Token信息中添加用户角色信息
	 * @return
	 */
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new CustomTokenEnhancer();
    }
	
	/**
	 * 将Token信息存储在Reids中,包括token,refreshToken用authorization_code
	 * @return
	 */
    @Bean
    RedisTokenStore redisTokenStore(){
        return new RedisTokenStore(redisConnectionFactory);
    }
  
	
    /**
     * <p>注意，自定义TokenServices的时候，需要设置@Primary，否则报错，</p>
     * @return
     */
    @Primary
    @Bean
    public DefaultTokenServices defaultTokenServices(){
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(redisTokenStore());
        tokenServices.setSupportRefreshToken(true);//支持RefreshToken
        tokenServices.setClientDetailsService(clientDetails());
        tokenServices.setAccessTokenValiditySeconds(60*60*12); // token有效期自定义设置，默认12小时
        tokenServices.setRefreshTokenValiditySeconds(60 * 60 * 24 * 7);//默认30天，这里修改
        return tokenServices;
    }



}