package com.app.smpt.controller;


import java.util.Date;

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

import com.app.smpt.controller.forms.UserForm;
import com.app.smpt.model.User;
import com.app.smpt.service.UserService;
import com.app.smpt.utils.SysUtilities;

@Controller
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/addUser", method = RequestMethod.POST)
	public ModelAndView addUser(@Validated UserForm userForm,  Model model, BindingResult result){
		ModelAndView mav = null;
		if (result.hasErrors()) {
			mav = new  ModelAndView("home");
		} else {
			try {
				model.addAttribute("userForm", userForm);
				// Add Book details to DB
				userService.addUser(copyUser(userForm));
				logger.info("A new user registerd Successfully : " + userForm.getFirstName());
				mav = new ModelAndView("home", "SUCCESS_KEY", "You Are Registered Successfully");
			}
			catch(Exception e) {
				mav = new ModelAndView("home", "ERROR_KEY", "Unable to register, Please try again..!");
				logger.error("Unable to register, Error is due to : " + e.getMessage());
			}
		}
		return mav;
	}
	 
	public User copyUser(UserForm form) throws Exception{
		User userInfo = new User();
		logger.debug("Enter copy User");
		
		userInfo.setCreatedDate(new Date());
		userInfo.setEmailAddress(form.getEmail());
		userInfo.setFirstName(form.getFirstName());
		userInfo.setLastName(form.getLastName());
		userInfo.setUserPassword(passwordEncoder.encode(form.getPassword()));
		userInfo.setActiveIndicator("1");
		userInfo.setDob(SysUtilities.getDateObject(form.getDob(), "dd/mm/YYYY"));
		logger.debug("Exit copy User");
		return userInfo;
	}
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}