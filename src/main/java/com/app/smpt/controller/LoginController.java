package com.app.smpt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.app.smpt.controller.forms.LoginForm;
import com.app.smpt.model.User;
import com.app.smpt.service.UserService;

@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public ModelAndView login(@Validated LoginForm loginForm,  Model model, BindingResult result){
		ModelAndView mav = null;
		if (result.hasErrors()) {
			mav = new  ModelAndView("home");
		}else {
			try {
				User userInfo = userService.getUser(loginForm.getEmailAddr());
				if(userInfo!=null){
					/*if(userInfo.getUserPassword().equals(passwordEncoder.encode(loginForm.getUserPassword()))){
						// Put in session and redirect to dashboard Page }*/
						mav = new  ModelAndView("redirect:dashboard","userInfo",userInfo);
					
				}else{
					mav = new  ModelAndView("redirect:home", "ERROR_KEY", "User Not Found, Please try again..!");
					mav.addObject("ERROR_KEY","User Not Found, Please try again..!");
				}
				
			} catch (Exception e) {
				mav = new ModelAndView("home", "ERROR_KEY", "Invalid Login Credentials, Please try again..!");
				logger.error("Wrong Login Attempt, Error is due to : " + e.getMessage());
			}
		}
		
		return mav;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

}
