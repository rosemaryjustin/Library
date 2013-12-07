package com.rose.library.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rose.database.DatabaseConnector;
import com.rose.library.DO.CheckInBooks;

public class CheckInDAO {
	private static final Logger logger = LoggerFactory.getLogger(CheckInDAO.class);
	List<CheckInBooks> checkInBooks;


	public List<CheckInBooks> checkin(String bookId, String cardNo,
			String borrowerLastName, String borrowerFirstName) {
		checkInBooks = new ArrayList<CheckInBooks>();
		Connection connection = DatabaseConnector.getConnection();

		Statement stmt;
		try {
			stmt = connection.createStatement();
			
			String query = "select card_no,book_id,lname,fname,address \n" +
		            "from book_loans bl natural join borrower br \n" +
		            "where ";
			//book_id = ''
			//and 

			boolean isThisFirst = true;
			
			if(!isEmpty(cardNo)) {
				query += getFormattedString(isThisFirst, "card_no", cardNo);
				isThisFirst = false;
			}
			if(!isEmpty(bookId)){
				query += getFormattedString(isThisFirst, "book_id", bookId);
				isThisFirst = false;
			}
			if(!isEmpty(borrowerLastName)){
				query += getFormattedString(isThisFirst, "lname", borrowerLastName);
				isThisFirst = false;
			}
			if(!isEmpty(borrowerFirstName)){
				query += getFormattedString(isThisFirst, "fname", borrowerFirstName);
				isThisFirst = false;
			}
			
			if(isThisFirst) {
				return checkInBooks;
			}
			
			logger.info(query);
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				CheckInBooks checkInBook = new CheckInBooks();
				checkInBook.setCardNo(rs.getString("card_no"));
				checkInBook.setBookId(rs.getString("book_id"));  
				checkInBook.setLastName(rs.getString("lname"));
				checkInBook.setFirstName(rs.getString("fname"));
				checkInBook.setAddress(rs.getString("address"));

				// Display values
				logger.info("Card No: " + checkInBook.getCardNo());
				logger.info(", Book Id: " + checkInBook.getBookId());
				logger.info(",Last Name: " + checkInBook.getLastName());
				logger.info(", First Name: " + checkInBook.getFirstName());
				logger.info(", Address: " + checkInBook.getAddress());
				checkInBooks.add(checkInBook);
			}
			rs.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseConnector.returnConnection(connection);
		}

		return checkInBooks;
	}
	
	private static String getFormattedString(boolean isFirstArgument, String columnName, String value) {
		if(isEmpty(value)) return "";
		String subQuery = columnName + " like '%"+value+"%'";
		if(isFirstArgument) {
			return subQuery;
		}
		return " and " + subQuery;
	}
	
	private static boolean isEmpty(String text) {
		if(text == null || text.isEmpty()) {
			return true;
		}
		return false;
	}

}
