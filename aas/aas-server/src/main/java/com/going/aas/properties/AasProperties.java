package com.going.aas.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * @author yuit
 * @date  2018/10/19 9:44
 *
 * 配置
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "going.aas")
@Data
public class AasProperties {

	private String loginPage = "/auth/login";

	private String loginProcessUrl="/auth/authorize";
    
    
}
