package com.going.aas.oauth2;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

/**
 * 将角色信息添加到token信息中
 *
 */
public class CustomTokenEnhancer implements TokenEnhancer {
	
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Map<String, Object> additionalInfo = new HashMap<String, Object>();
		Collection<GrantedAuthority> authorities = authentication.getAuthorities();
		for(GrantedAuthority authority : authorities) {
			additionalInfo.put("role", authority.getAuthority());
		}
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
		return accessToken;
	}

}
