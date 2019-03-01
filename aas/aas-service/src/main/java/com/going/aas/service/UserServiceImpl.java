package com.going.aas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.going.aas.domain.User;
import com.going.aas.persistence.UserMapper;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper userMapper;

	public User findByUsername(String username) {
		return userMapper.selectByUsername(username);
	}

}
