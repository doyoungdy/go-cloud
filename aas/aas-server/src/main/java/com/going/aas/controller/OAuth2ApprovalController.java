package com.going.aas.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
// 必须配置
@SessionAttributes("authorizationRequest")
public class OAuth2ApprovalController {

	@RequestMapping("/oauth/confirm_access")
	public String getAccessConfirmation(Map<String, Object> model, HttpServletRequest request) throws Exception {
//		AuthorizationRequest authorizationRequest = (AuthorizationRequest) model.get("authorizationRequest");
//		ModelAndView view = new ModelAndView();
//		view.addObject("clientId", authorizationRequest.getClientId());
//		view.addObject("scopes", authorizationRequest.getScope());
//		view.setViewName("");
        @SuppressWarnings("unchecked")
        Map<String, String> scopes = (Map<String, String>) (model.containsKey("scopes") ? model.get("scopes") : request.getAttribute("scopes"));
        List<String> scopeList = new ArrayList<String>();
        if (scopes != null) {
            scopeList.addAll(scopes.keySet());
        }
        model.put("scopeList", scopeList);
		return "aas_approval";
	}

}
