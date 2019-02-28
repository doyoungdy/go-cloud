package com.going.aas.security;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.going.aas.domain.Role;
import com.going.aas.domain.User;
import com.going.aas.service.RoleService;
import com.going.aas.service.UserService;


/**
 * 实现SpringSecurity框架中UserDetailsService接口,重载默认配置<pre>
 * 1.根据用户名称加载用户信息
 * 2.根据用户主键加载用户授权信息
 * 
 */
public class UserDetailsServiceImpl implements UserDetailsService {
	
	Logger logger = LoggerFactory.getLogger(getClass());

	private UserService userService;
	
	private RoleService roleService; 
	
	public UserDetailsServiceImpl(UserService userService, RoleService roleService) {
		super();
		this.userService = userService;
		this.roleService = roleService;
	}

	/**
	 * 加载用户认证信息
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.debug("加载用户{}信息",username);
		User user = userService.findByUsername(username);
		if (user == null)
			throw new RuntimeException("用户不存在");
		return new UserDetailsImpl(user, getAuthorities(user));
	}

	/**
	 * 加载用户授权信息
	 * @param user
	 * @return
	 */
	private List<GrantedAuthority> getAuthorities(User user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		List<Role> roles = roleService.getRoleByUserId(user.getId());
		StringBuilder roleSb = new StringBuilder();
		for (Role role : roles) {
			roleSb.append(role.getName()).append(";");
		}
		logger.debug("加载到用户角色信息[{}]", roleSb.toString());
		authorities.add(new SimpleGrantedAuthority(roleSb.toString()));
		return authorities;
	}
}
