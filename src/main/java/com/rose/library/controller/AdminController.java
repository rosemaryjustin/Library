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

import com.rose.library.DO.BookLoanHistory;
import com.rose.library.DO.Branch;
import com.rose.library.manager.AdminManager;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "admin";
	}
	
	@RequestMapping(value = "/history", method = RequestMethod.GET)
	public String history(Locale locale, Model model) {
		return "history";
	}
	
	@RequestMapping(value = "/branch", method = RequestMethod.GET)
	public String branch(Locale locale, Model model) {
		List<Branch> branches =  new AdminManager().searchBranch();
		model.addAttribute("branches", branches );
		return "branch";
	}
	
	@RequestMapping(value = "/history/submit", method = RequestMethod.POST)
	public String historySubmit(Locale locale, Model model, HttpServletRequest request) {
		String cardNo = request.getParameter("cardNo");
		if(cardNo == null) cardNo = "";
		logger.info(cardNo);
		String bookId = request.getParameter("bookId");
		if(bookId == null) bookId = "";
		logger.info(bookId);
		String branchId = request.getParameter("branchId");
		logger.info(branchId);
		int branch = 0;
		try {
			branch = Integer.parseInt(branchId);
		} catch(Exception e) {
			branch = 0; 
		}
		
		
		List<BookLoanHistory> bookLoanHistory = new AdminManager().searchBorrower(cardNo, bookId, branch);
		model.addAttribute("checkinHistoryList", bookLoanHistory );
		model.addAttribute("cardNo", cardNo );
		model.addAttribute("bookId", bookId );
		model.addAttribute("branchId", branchId );
		return "history";
	}
	
	
}
