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
	List<CheckInBooks> checkInBooks = new ArrayList<CheckInBooks>();


	public List<CheckInBooks> checkin(String bookId, String cardNo,
			String borrowerLastName, String borrowerFirstName) {
		
		Connection connection = DatabaseConnector.getConnection();

		Statement stmt;
		try {
			stmt = connection.createStatement();

			String sql = "select card_no,book_id,lname,fname,address \n" +
		            "from book_loans bl natural join borrower br \n" +
		            "where (book_id = '"+bookId+"' or card_no = '"+cardNo+"' or lname = '"+borrowerLastName+"' or fname = '"+borrowerFirstName+"');\n";
			
			logger.info(sql);
			ResultSet rs = stmt.executeQuery(sql);

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

}
