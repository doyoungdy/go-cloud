package com.going.aas.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.going.aas.domain.User;
import com.going.aas.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;

    @RequestMapping("/api/user")
    public ResponseEntity<User> getUerInfo() {
        String username = (String) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        User user = userService.findByUsername(username);
        return ResponseEntity.ok(user);
    }
    
//    public static void main(String[] args) {
//    	 BCryptPasswordEncoder x = new BCryptPasswordEncoder();
//    	 System.out.println(x.encode(""));
//	}

}
