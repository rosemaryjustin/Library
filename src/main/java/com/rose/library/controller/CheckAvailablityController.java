package com.rose.library.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rose.library.DO.AvailableBooks;
import com.rose.library.manager.CheckAvailablityManager;

// import com.rose.library.manager.CheckoutManager;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/checkAvailablity")
public class CheckAvailablityController {
	
	private static final Logger logger = LoggerFactory.getLogger(CheckAvailablityController.class);
	List<AvailableBooks> availableBooks = new ArrayList<AvailableBooks>();
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String checkout(Locale locale, Model model) {
		/*initializeModelWithEmptyStrings(model);*/
		
		return "checkAvailablity";
	}
	
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public String submit(Locale locale, Model model,HttpServletRequest request) {
		logger.info("Welcome to check book availablity page! The client locale is {}.", locale);
		
		String bookId = request.getParameter("bookId");
		logger.info(bookId);
		String title = request.getParameter("title");
		logger.info(title);
		String authorName = request.getParameter("authorName");
		logger.info(authorName);
		
		availableBooks = new CheckAvailablityManager().checkAvailablity(bookId, title, authorName);
		logger.info("check");
		model.addAttribute("availableBooks",availableBooks);
		logger.info("check1");

		model.addAttribute("bookId", bookId );
		model.addAttribute("title", title );
		model.addAttribute("authorName", authorName );
		
		/*if(CheckoutManager.SUCCESS_MESSAGE.equalsIgnoreCase(statusOfCheckout)) {
			initializeModelWithEmptyStrings(model);
		}*/
		
		return "checkAvailablity";
	}
	
	/*private void initializeModelWithEmptyStrings(Model model) {
		model.addAttribute("bookId", "" );
		model.addAttribute("title", "" );
		model.addAttribute("authorName", "" );
	}*/
	
	
}
