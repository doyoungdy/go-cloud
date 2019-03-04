package com.going.aas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.going.aas.boot.support.properties.AasProperties;

@Controller
public class PageController {
	
	@Autowired
	private AasProperties properties;

	@GetMapping("/auth/login")
	public String loginPage(Model model) {

		model.addAttribute("loginProcessUrl", properties.getLoginProcessUrl());

		return "aas_login";
	}

}
