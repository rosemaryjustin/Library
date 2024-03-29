package com.rose.library.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rose.library.manager.CheckoutManager;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/checkout")
public class CheckOutController {
	
	private static final Logger logger = LoggerFactory.getLogger(CheckOutController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String checkout(Locale locale, Model model) {
		initializeModelWithEmptyStrings(model);
		
		return "checkout";
	}
	
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public String submit(Locale locale, Model model, HttpServletRequest request) {
		logger.info("Welcome to checkout page! The client locale is {}.", locale);
		
		String bookId = request.getParameter("bookId");
		logger.info(bookId);
		String branchId = request.getParameter("branchId");
		logger.info(branchId);
		String cardNo = request.getParameter("cardNo");
		logger.info(cardNo);
		
		String statusOfCheckout = new CheckoutManager().checkout(bookId, branchId, cardNo);
		model.addAttribute("statusOfCheckout", statusOfCheckout );
		
		model.addAttribute("bookId", bookId );
		model.addAttribute("branchId", branchId );
		model.addAttribute("cardNo", cardNo );
		
		if(CheckoutManager.SUCCESS_MESSAGE.equalsIgnoreCase(statusOfCheckout)) {
			initializeModelWithEmptyStrings(model);
		}
		
		return "checkout";
	}
	
	private void initializeModelWithEmptyStrings(Model model) {
		model.addAttribute("bookId", "" );
		model.addAttribute("branchId", "" );
		model.addAttribute("cardNo", "" );
	}
	
	
}
