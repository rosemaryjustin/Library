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

import com.rose.library.DO.CheckInBooksSearch;
import com.rose.library.manager.CheckinManager;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/checkin")
public class CheckInController {
	
	private static final Logger logger = LoggerFactory.getLogger(CheckInController.class);
	List<CheckInBooksSearch> checkInBooks = new ArrayList<CheckInBooksSearch>();
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String checkout(Locale locale, Model model) {
		initializeModelWithEmptyStrings(model);
		
		return "checkin";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(Locale locale, Model model, HttpServletRequest request) {
		logger.info("Welcome to checkin page! The client locale is {}.", locale);
		
		String bookId = request.getParameter("bookId");
		logger.info(bookId);
		String cardNo = request.getParameter("cardNo");
		logger.info(cardNo);
		String borrowerLastName = request.getParameter("borrowerLastName");
		logger.info(borrowerLastName);
		String borrowerFirstName = request.getParameter("borrowerFirstName");
		logger.info(borrowerFirstName);
		
		
		checkInBooks = new CheckinManager().checkinSearch(bookId, cardNo, borrowerLastName,borrowerFirstName);
		model.addAttribute("checkInBooks", checkInBooks );
		
		model.addAttribute("bookId", bookId );
		model.addAttribute("cardNo", cardNo );
		model.addAttribute("borrowerLastName", borrowerLastName );
		model.addAttribute("borrowerFirstName", borrowerFirstName );
		
		/*if(CheckinManager.SUCCESS_MESSAGE.equalsIgnoreCase(statusOfCheckin)) {
			initializeModelWithEmptyStrings(model);
		}*/
		
		return "checkin";
	}
	
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public String submit(Locale locale, Model model, HttpServletRequest request) {
		String[] toCheckinBooks = request.getParameterValues("checked");
		for (int i = 0; i < toCheckinBooks.length; i++)
			logger.info(toCheckinBooks[i]);
		String checkinStatus = new CheckinManager().checkin(toCheckinBooks);
		model.addAttribute("checkinStatus", checkinStatus );
		return "checkin";
	}
	
	
	private void initializeModelWithEmptyStrings(Model model) {
		model.addAttribute("bookId", "" );
		model.addAttribute("cardNo", "" );
		model.addAttribute("borrowerLastName", "" );
		model.addAttribute("borrowerFirstName", "" );
	}
	
	
}
