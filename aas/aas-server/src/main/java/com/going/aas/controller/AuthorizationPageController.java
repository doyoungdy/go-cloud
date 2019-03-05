package com.going.aas.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * 自定义授权界面
 * @author Administrator
 *
 */
@Controller
@SessionAttributes("authorizationRequest")// 必须配置
public class AuthorizationPageController {
	
	Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 跳转到自定义授权界面<pre>
	 * 覆盖默认的/oauth/confirm_access的路径
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/oauth/confirm_access")
	public String getAccessConfirmation(Map<String, Object> model, HttpServletRequest request) throws Exception {
		logger.debug("授权界面跳转");
		AuthorizationRequest authorizationRequest = (AuthorizationRequest) model.get("authorizationRequest");
		logger.debug("clientId is {} .", authorizationRequest.getClientId());
//		ModelAndView view = new ModelAndView();
//		view.addObject("clientId", authorizationRequest.getClientId());
//		view.addObject("scopes", authorizationRequest.getScope());
//		view.setViewName("");
		
	
//    Map<String, String> scopes = (Map<String, String>) );
//    List<String> scopeList = new ArrayList<>();
//    if (scopes != null) {
//        scopeList.addAll(scopes.keySet());
//    }
		model.put("scopeList", authorizationRequest.getScope());
		return "aas_approval";
}

}
