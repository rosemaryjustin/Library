package com.rose.library.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rose.library.DO.Borrower;
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
		if(statusOfAddBorrower != null && !statusOfAddBorrower.startsWith("Success:")) {
			model.addAttribute("statusOfAddBorrower", statusOfAddBorrower );
			
			model.addAttribute("borrowerLastName", borrowerLastName );
			model.addAttribute("borrowerFirstName", borrowerFirstName );
			model.addAttribute("address", address );
			model.addAttribute("phoneNumber", phoneNumber );
			
			return "addBorrower";
		} else {
			String cardNo = statusOfAddBorrower.substring("Success:".length());
			List<Borrower> borrowers = new AddBorrowerManager()
			.searchBorrower(cardNo, "", "", "");
			
			model.addAttribute("statusOfAddBorrower", "Successfully added the following borrower." );
			model.addAttribute("borrowers", borrowers );
			model.addAttribute("cardNo", cardNo );
			model.addAttribute("borrowerLastName", borrowerLastName );
			model.addAttribute("borrowerFirstName", borrowerFirstName );
			model.addAttribute("address", address );
			model.addAttribute("phoneNumber", phoneNumber );
			return "searchBorrower";
		}
		
	
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(Locale locale, Model model, HttpServletRequest request) {
		
		return "searchBorrower";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Locale locale, Model model, HttpServletRequest request) {
		String cardNo = request.getParameter("cardNo");
		logger.info(cardNo);
		String borrowerLastName = request.getParameter("borrowerLastName");
		logger.info(borrowerLastName);
		String borrowerFirstName = request.getParameter("borrowerFirstName");
		logger.info(borrowerFirstName);
		String phoneNumber = request.getParameter("phoneNumber");
		logger.info(phoneNumber);
		String address = request.getParameter("address");
		logger.info(address);
		
		model.addAttribute("cardNo", cardNo );
		model.addAttribute("borrowerLastName", borrowerLastName );
		model.addAttribute("borrowerFirstName", borrowerFirstName );
		model.addAttribute("phoneNumber", phoneNumber );
		model.addAttribute("address", address );
		return "editBorrower";
	}
	
	@RequestMapping(value = "/edit/submit", method = RequestMethod.POST)
	public String editSubmit(Locale locale, Model model, HttpServletRequest request) {
		String cardNo = request.getParameter("cardNo");
		logger.info(cardNo);
		String borrowerLastName = request.getParameter("borrowerLastName");
		logger.info(borrowerLastName);
		String borrowerFirstName = request.getParameter("borrowerFirstName");
		logger.info(borrowerFirstName);
		String phoneNumber = request.getParameter("phoneNumber");
		logger.info(phoneNumber);
		String address = request.getParameter("address");
		logger.info(address);
		
		String editBorrowerMessage = new AddBorrowerManager().updateBorrower(cardNo,
				borrowerFirstName,borrowerLastName,address,phoneNumber);
		
		
		model.addAttribute("editBorrowerMessage", editBorrowerMessage );
		model.addAttribute("cardNo", cardNo );
		model.addAttribute("borrowerLastName", borrowerLastName );
		model.addAttribute("borrowerFirstName", borrowerFirstName );
		model.addAttribute("phoneNumber", phoneNumber );
		model.addAttribute("address", address );
		return "editBorrower";
	}
	
	@RequestMapping(value = "/search/submit", method = RequestMethod.POST)
	public String searchSubmit(Locale locale, Model model, HttpServletRequest request) {
		String cardNo = request.getParameter("cardNo");
		logger.info(cardNo);
		String borrowerLastName = request.getParameter("borrowerLastName");
		logger.info(borrowerLastName);
		String borrowerFirstName = request.getParameter("borrowerFirstName");
		logger.info(borrowerFirstName);
		String phoneNumber = request.getParameter("phoneNumber");
		logger.info(phoneNumber);
		
		List<Borrower> borrowers = new AddBorrowerManager().searchBorrower(cardNo, 
				borrowerLastName, borrowerFirstName, phoneNumber);
		
		model.addAttribute("borrowers", borrowers );
		if(cardNo != null) {
			model.addAttribute("cardNo", cardNo );
		}
		
		model.addAttribute("borrowerLastName", borrowerLastName );
		model.addAttribute("borrowerFirstName", borrowerFirstName );
		model.addAttribute("phoneNumber", phoneNumber );
		return "searchBorrower";
	}
	
	
	
	private void initializeModelWithEmptyStrings(Model model) {
		model.addAttribute("borrowerLastName", "" );
		model.addAttribute("borrowerFirstName", "" );
		model.addAttribute("address", "" );
		model.addAttribute("phoneNumber", "" );
	}
	
	
}
