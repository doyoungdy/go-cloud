package com.going.aas.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.going.aas.properties.AasProperties;

/**
 * 认证前端界面跳转
 * @author Administrator
 *
 */
@Controller
public class AuthenticationPageController {
	
	@Autowired
	private AasProperties properties;

	/**
	 * 覆盖默认用户名密码登录认证界面,跳转到自定义登录界面<pre>
	 * @param model
	 * @return
	 */
	@GetMapping("/auth/login")
	public String loginPage(Model model, HttpServletRequest request) {
		model.addAttribute("loginProcessUrl", properties.getLoginProcessUrl());
		return "aas_login";
	}

}
