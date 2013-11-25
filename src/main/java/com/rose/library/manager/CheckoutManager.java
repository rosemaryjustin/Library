package com.rose.library.manager;

import com.rose.library.DAO.CheckoutDAO;

public class CheckoutManager {
	
	public static final String FAIL_MESSAGE = "Error! Could not check out this book.";
	public static final String SUCCESS_MESSAGE = "Book checked out successfully.";
	public static final String BORROWER_CANNOT_BORROW_ERROR = "Borrower cannot borrow more books.";
	public static final String NO_BOOKS_ARE_AVAILABLE_ERROR = "No Books are available at this branch.";
	private static final int BORROW_LIMIT = 3;

	public String checkout(String bookId, String branchId, String cardNo) {
		boolean isAvailable = new CheckoutDAO().isAvailable(bookId, branchId, cardNo);
		int numberOfBorrowedBooks = new CheckoutDAO().getNumberOfBorrowedBooks(bookId, branchId, cardNo);
		if (!isAvailable){
			return NO_BOOKS_ARE_AVAILABLE_ERROR;
		}
		if (numberOfBorrowedBooks >= BORROW_LIMIT){
			return BORROWER_CANNOT_BORROW_ERROR;
		}

		return new CheckoutDAO().checkout(bookId, branchId, cardNo)? SUCCESS_MESSAGE : FAIL_MESSAGE;
	}

}
