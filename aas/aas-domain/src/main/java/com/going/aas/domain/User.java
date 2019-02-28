package com.going.aas.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.going.framework.persistence.domain.AbstractBaseEntity;

import lombok.Data;

/**
 * 用户
 * @author Administrator
 *
 */
@Data
@JsonIgnoreProperties({ "password"})
public class User extends AbstractBaseEntity {
	
	private static final long serialVersionUID = -2588305310675560457L;

	private String username;
	
	private String password;
	
	private String mail;
	
	private String phone;
	
	private boolean locked;
	
	private boolean enabled;
	
//	private String roleId;
	
	
}
