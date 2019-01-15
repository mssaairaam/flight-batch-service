package com.tslservices.flight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tslservices.flight.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	LoginService loginService;

	@RequestMapping( value = "/authenticate", method = RequestMethod.POST)
	public String authenticate(
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			ModelMap modelMap) {
		String redirectionPage = "login";
//		String username = (String) modelMap.get("username");
//		String password = (String) modelMap.get("password");
		boolean userValidationFlag = loginService.validateUser(username, password);
		if (userValidationFlag) {
			redirectionPage = "maintain-flight-batch-view";
		} else {
			modelMap.addAttribute("message", "Invalid User Credentials !");
		}
		return redirectionPage;
	}
}
