package com.app.smpt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.app.smpt.model.Portfolio;

@Controller
public class PortfolioController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value="/addPortfolio", method = RequestMethod.POST)
	public ModelAndView addPortfolio(@ModelAttribute Portfolio portfolio,  Model model, BindingResult result)
	{
		ModelAndView mav = null;
		if (result.hasErrors()) {
			mav = new  ModelAndView("dashboard");
		}else {
			try {
				System.out.println(portfolio.getPfName());
					mav = new  ModelAndView("dashboard", "ERROR_KEY", "Portfolio added successfully");
				
			} catch (Exception e) {
				mav = new ModelAndView("dashboard", "ERROR_KEY", "Could not add Portfolio, Please try again..!");
				logger.error("Could not add Portfolio, Error is due to : " + e.getMessage());
			}
		}
		return mav;
	}
}
