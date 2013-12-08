package com.rose.library.manager;

import java.util.ArrayList;
import java.util.List;







import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rose.library.DAO.CheckInDAO;
import com.rose.library.DO.CheckInBooksSearch;
import com.rose.library.DO.CheckinBooks;

public class CheckinManager {
	private static final Logger logger = LoggerFactory.getLogger(CheckinManager.class);
	private static final String NO_BOOKS_ARE_SELECTED_MESSAGE = "No Books are selected to Check In. If you want to checkin books please select some books from the list.";
	private static final String SUCCESS_MESSAGE = "Books Checked In Successfully";
	private static final String FAIL_MESSAGE = " Error ! There was some problem checking in books";
	List<CheckInBooksSearch> checkInBooks = new ArrayList<CheckInBooksSearch>();
	List<CheckinBooks> checkedinBooks = new ArrayList<CheckinBooks>();
	
	public List<CheckInBooksSearch> checkinSearch(String bookId, String cardNo,
			String borrowerLastName, String borrowerFirstName) {
		checkInBooks = new CheckInDAO().checkinSearch(bookId, cardNo, borrowerLastName,borrowerFirstName);
		return checkInBooks;
	}

	public String checkin(String[] toCheckinBooks) {
		String delimiter = "#";
		String checkinBook;
		String[] temp;
		CheckinBooks toCheckin;
		checkedinBooks = new ArrayList<CheckinBooks>();
		
		if(toCheckinBooks.length == 0)
			return NO_BOOKS_ARE_SELECTED_MESSAGE;
		
		// Splitting the string to list 
		for(int i = 0; i< toCheckinBooks.length ; i++){
			checkinBook = toCheckinBooks[i];
			temp = checkinBook.split(delimiter,3);
			toCheckin = new CheckinBooks();
			logger.info(temp[0]);
			logger.info(temp[1]);
			logger.info("" + Integer.parseInt(temp[2]));
			toCheckin.setCardNo(temp[0]);
			toCheckin.setBookId(temp[1]);
			toCheckin.setBranchId(Integer.parseInt(temp[2]));
			checkedinBooks.add(toCheckin);
		}
		
		return new CheckInDAO().checkin(checkedinBooks)? SUCCESS_MESSAGE : FAIL_MESSAGE;
	}

}
