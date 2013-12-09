package com.rose.library.manager;

import java.util.List;

import com.rose.library.DAO.AdminDAO;
import com.rose.library.DO.BookLoanHistory;
import com.rose.library.DO.Branch;

public class AdminManager {
			

	public List<BookLoanHistory> searchBorrower(String cardNo, String bookId,
			int branchId) {

		return new AdminDAO().getBookLoanHistory(cardNo, bookId, branchId);
	}
	
	public List<Branch> searchBranch() {

		return new AdminDAO().getBranches();
	}


}
