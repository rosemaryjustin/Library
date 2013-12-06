package com.rose.library.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rose.library.manager.AddBorrowerManager;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/addBorrower")
public class AddBorrowerController {
	
	private static final Logger logger = LoggerFactory.getLogger(AddBorrowerController.class);
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String checkout(Locale locale, Model model) {
		initializeModelWithEmptyStrings(model);
		
		return "addBorrower";
	}
	
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public String submit(Locale locale, Model model, HttpServletRequest request) {
		logger.info("Welcome to Add Borrower page! The client locale is {}.", locale);
		
		
		String borrowerLastName = request.getParameter("borrowerLastName");
		logger.info(borrowerLastName);
		String borrowerFirstName = request.getParameter("borrowerFirstName");
		logger.info(borrowerFirstName);
		String address = request.getParameter("address");
		logger.info(address);
		String phoneNumber = request.getParameter("phoneNumber");
		logger.info(phoneNumber);
		
		
		String statusOfAddBorrower = new AddBorrowerManager().addBorrower(borrowerLastName, borrowerFirstName, address,phoneNumber);
		logger.info(statusOfAddBorrower);
		model.addAttribute("statusOfAddBorrower", statusOfAddBorrower );
		
		model.addAttribute("borrowerLastName", borrowerLastName );
		model.addAttribute("borrowerFirstName", borrowerFirstName );
		model.addAttribute("address", address );
		model.addAttribute("phoneNumber", phoneNumber );
		
		/*if(CheckinManager.SUCCESS_MESSAGE.equalsIgnoreCase(statusOfAddBorrower)) {
			initializeModelWithEmptyStrings(model);
		}*/
		
		return "addBorrower";
	}
	
	private void initializeModelWithEmptyStrings(Model model) {
		model.addAttribute("borrowerLastName", "" );
		model.addAttribute("borrowerFirstName", "" );
		model.addAttribute("address", "" );
		model.addAttribute("phoneNumber", "" );
	}
	
	
}
