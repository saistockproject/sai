package com.app.smpt.controller;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.app.smpt.model.FeedItem;
import com.app.smpt.service.FeedReciever;
import com.app.smpt.service.ItemsRetriever;
import com.app.smpt.service.StockMasterService;

@Controller
public class DashboardController {
 
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private static final String key = "hAi_4xzQrysyC__ErSta";
	
	private final FeedReciever feedReciever;
	private final ItemsRetriever itemsRetriever;
	
	@Autowired
	private StockMasterService stockMasterService;

	
	@Autowired
	public DashboardController(FeedReciever feedReciever, ItemsRetriever itemsRetriever) {
		this.feedReciever = feedReciever;
		this.itemsRetriever = itemsRetriever;
	}
	
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView Dashboard(Locale locale, Model model) {
		System.out.println("In Dashboard Controller");
		logger.info("Welcome home! The client locale is {}.", locale);
		try {
			System.out.println(stockMasterService.getStockMasterByString("AAPL"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		  
		String feedUrl = "http://finance.yahoo.com/rss/headline?s=AAPL";
		feedReciever.addFeed(feedUrl); 
		
		List<FeedItem> items = itemsRetriever.get();
		ModelAndView modelAndView = new ModelAndView("dashboard");
		modelAndView.addObject("items", items);

		return modelAndView;

	}

}
