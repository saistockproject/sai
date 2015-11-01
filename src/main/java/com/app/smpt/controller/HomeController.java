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

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private final FeedReciever feedReciever;
	private final ItemsRetriever itemsRetriever; 
	
	@Autowired
	private StockMasterService stockMasterService;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@Autowired
	public HomeController(FeedReciever feedReciever, ItemsRetriever itemsRetriever) {
		this.feedReciever = feedReciever;
		this.itemsRetriever = itemsRetriever;
	}
  
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		logger.debug("index()");
		return "redirect:/home";
	}
	
	@RequestMapping(value = "/home",  method = RequestMethod.GET)
	public ModelAndView home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		try {
			System.out.println(stockMasterService.getStockMasterByString("AAPL"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String feedUrl = "http://finance.yahoo.com/rss/headline?s=KSU,MIDD,ORCL,QCOM,EMC,DIOD,BIIB";
		feedReciever.addFeed(feedUrl); 
		
		List<FeedItem> items = itemsRetriever.get();
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("items", items);

		return modelAndView;
	}

	public StockMasterService getStockMasterService() {
		return stockMasterService;
	}

	public void setStockMasterService(StockMasterService stockMasterService) {
		this.stockMasterService = stockMasterService;
	}

}
