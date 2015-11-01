package com.app.smpt.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.app.smpt.common.quandl.InvalidTokenException;
import com.app.smpt.common.quandl.QDataset;
import com.app.smpt.common.quandl.QEntry;
import com.app.smpt.common.quandl.QuandlConnection;
import com.app.smpt.common.quandl.query.Queries;
import com.app.smpt.common.quandl.query.SimpleQuery;
import com.app.smpt.controller.forms.StockSearchForm;
import com.app.smpt.model.StockMaster;
import com.app.smpt.service.StockMasterService;

@Controller
@RequestMapping(value = "/searchStocks")
public class StockSearchController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private static final String key = "hAi_4xzQrysyC__ErSta";

	@Autowired
	private StockMasterService stockMasterService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String stockSearchForm(Model model) {
		model.addAttribute("searchStocks", new StockSearchForm());
		return "searchStocks";
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView stockSearchSubmit(@ModelAttribute StockSearchForm stockSearchForm, Model model) {
		model.addAttribute("searchStocks", stockSearchForm);
		// Open a connection with or without an API key
		String quandlCode="GOOG/NASDAQ_";
		QuandlConnection qc = null;
		try {
			qc = key.length() >= 1 ? QuandlConnection.getFullConnection(key) : QuandlConnection.getLimitedConnection();
		} catch (InvalidTokenException e) {
			e.printStackTrace();
		}
		// find quandle code for search string
		StockMaster stockMaster= stockMasterService.getStockMasterByString(stockSearchForm.getSearchCriteria());
		quandlCode +=stockMaster.getSmTicker();
		System.out.println("inside search controller " + quandlCode);
		
		// Get a full dataset using its Quandl Code
		// http://www.quandl.com/WIKI/AAPL-Apple-Inc-AAPL-Prices-Dividends-Splits-and-Trading-Volume
		SimpleQuery datedQuery = Queries.create(quandlCode).dateRange("2015-10-29", "2015-10-30");
		QDataset filtered = qc.getDataset(datedQuery);

		// And expand on those queries later
		QDataset sorted = qc.getDataset(datedQuery.ascending().numRows(10));
		System.out.println(sorted.getName());
		System.out.println(sorted.getDescription());
		for (QEntry qe : sorted.getDataset()) {
			System.out.println("\t" + qe);
		} 

		// return a model
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("stock", sorted);
		
		return modelAndView;
	}

	@RequestMapping(value = "/getStockMasterList", method = RequestMethod.GET, headers = "Accept=*/*")
	public @ResponseBody List<String> getStockMasterList(@RequestParam("stockName") String query) {
		System.out.println("Inside the autocomplete method");
		List<String> stockList = (List<String>) stockMasterService.getStockMasterByString(query);

		return stockList;
	}

}
