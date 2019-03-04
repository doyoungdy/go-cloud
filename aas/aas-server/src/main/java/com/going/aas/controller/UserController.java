package com.going.aas.controller;

import java.security.Principal;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @RequestMapping("/api/user")
    public Principal user(Principal user) {
        return user;
    }
    
  public static void main(String[] args) {
	 BCryptPasswordEncoder x = new BCryptPasswordEncoder();
	 System.out.println(x.encode("secret"));
}

}
