package com.going.aas.service;

import java.util.List;

import com.going.aas.domain.Role;

/**
 * 角色服务接口
 * @author Administrator
 *
 */
public interface RoleService {
	
	List<Role> getRoleByUserId(String userId);

}
