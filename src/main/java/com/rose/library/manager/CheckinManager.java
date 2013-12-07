package com.rose.library.manager;

import java.util.ArrayList;
import java.util.List;


import com.rose.library.DAO.CheckInDAO;
import com.rose.library.DO.CheckInBooks;

public class CheckinManager {
	List<CheckInBooks> checkInBooks = new ArrayList<CheckInBooks>();

	public List<CheckInBooks> checkin(String bookId, String cardNo,
			String borrowerLastName, String borrowerFirstName) {
		checkInBooks = new CheckInDAO().checkin(bookId, cardNo, borrowerLastName,borrowerFirstName);
		return checkInBooks;
	}

}
