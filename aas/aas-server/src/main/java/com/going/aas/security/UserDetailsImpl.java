package com.going.aas.security;


import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.going.aas.domain.User;

/**
 * Spring Security中的 UserDetails 实现
 */

public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 3957586021470480642L;

	protected User user;

	protected List<GrantedAuthority> grantedAuthorities;

	public UserDetailsImpl() {
	}

	public UserDetailsImpl(User user, List<GrantedAuthority> grantedAuthorities) {
		this.user = user;
		this.grantedAuthorities = grantedAuthorities;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.grantedAuthorities;
	}

	public String getPassword() {
		return user.getPassword();
	}

	public String getUsername() {
		return user.getUsername();
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return !user.isLocked();
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return user.isEnabled();
	}

	public User getUser() {
		return user;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("{user=").append(user);
		sb.append('}');
		return sb.toString();
	}
}