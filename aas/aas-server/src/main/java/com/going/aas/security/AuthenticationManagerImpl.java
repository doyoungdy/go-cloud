package com.going.aas.security;


import java.util.Collection;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.going.aas.domain.User;

/**
 * 基于SpringSecurity框架，基于SpringSecurity接口,实现用户认证功能。
 *
 */
public class AuthenticationManagerImpl implements AuthenticationManager {

	private UserDetailsService userDetailsService;

	public AuthenticationManagerImpl(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	/**
	 * 基于用户名及密码的用户认证逻辑
	 */
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		Authentication returnAuthentication = null;
		if (authentication instanceof UsernamePasswordAuthenticationToken) {
			String username = authentication.getName();
			String password = (String) authentication.getCredentials();
			UserDetailsImpl details = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);
			User user = details.getUser();
			if (user == null) {
				throw new BadCredentialsException("账号不存在或密码错误");
			}
			if (password.length() == 32) {
				if (!(password).equals(details.getPassword())) {
					throw new CredentialsExpiredException("证书已过期");
				}
			} else {
				//TODO 对用户登录密码的加解密操作待确认SpringSecurity版本支持,再考虑
//				if (!(Coder.encryptMd5(password)).equals(details.getPassword())) {
//					throw new BadCredentialsException("账号或密码错误");
//				}
			}
			if (!details.isEnabled()) {
				throw new DisabledException("账号已禁用");
			}
			if (!details.isAccountNonLocked()) {
				throw new LockedException("账号已锁定");
			}
			if (!details.isAccountNonExpired()) {
				throw new CredentialsExpiredException("证书已过期");
			}
			Collection<? extends GrantedAuthority> authorities = details.getAuthorities();
			return new UsernamePasswordAuthenticationToken(details, password, authorities);
		}

		return returnAuthentication;
	}

	public boolean supports(Class<?> arg0) {
		return true;
	}
}
