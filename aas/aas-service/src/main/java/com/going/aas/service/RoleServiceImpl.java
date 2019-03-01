package com.going.aas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.going.aas.domain.Role;
import com.going.aas.persistence.RoleMapper;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleMapper roleMapper;

	public List<Role> getRoleByUserId(String userId) {
		return roleMapper.getRoleByUserId(userId);
	}

}
