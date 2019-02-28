package com.going.aas.service;

import com.going.aas.domain.User;

public interface UserService {
	
	User findByUsername(String name);

}
