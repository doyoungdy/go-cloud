package com.going.aas.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.util.HtmlUtils;

import com.going.aas.exception.CustomOAuth2Exception;

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
		model.put("scopeList", authorizationRequest.getScope());
		return "aas_approval";
	}
	
	@RequestMapping("/oauth/error")
	public String handleError(Model model, HttpServletRequest request) {
		logger.debug("授权异常界面提示");
		Object error = request.getAttribute("error");

		if (error instanceof OAuth2Exception) {
			OAuth2Exception oauthError = (OAuth2Exception) error;
			model.addAttribute("errorMessage", oauthError.getLocalizedMessage());
			model.addAttribute("httpErrorCode",oauthError.getHttpErrorCode());
			model.addAttribute("oauth2ErrorCode",oauthError.getOAuth2ErrorCode());
		}
		else {
			model.addAttribute("errorMessage", "UnKonwnErrorMessage");
			model.addAttribute("httpErrorCode","UnKownHttpErrorCode");
			model.addAttribute("oauth2ErrorCode","UnKonwnOauth2ErrorCode");
		}
		return "aas_oauth_error";
	}

}
