package com.going.aas.boot.support.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author yuit
 * @date  2018/10/19 9:44
 *
 * 配置
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "going.aas")
public class AasProperties {

    private String loginProcessUrl="/auth/authorize";
    
    private String loginPage = "/auth/login";


    public String getLoginProcessUrl() {
        return loginProcessUrl;
    }

    public void setLoginProcessUrl(String loginProcessUrl) {
        this.loginProcessUrl = loginProcessUrl;
    }

	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}
    

}
