package com.rose.library.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rose.database.DatabaseConnector;
import com.rose.library.DO.CheckInBooksSearch;
import com.rose.library.DO.CheckinBooks;

public class CheckInDAO {
	private static final Logger logger = LoggerFactory.getLogger(CheckInDAO.class);
	List<CheckInBooksSearch> checkInBooks;


	public List<CheckInBooksSearch> checkinSearch(String bookId, String cardNo,
			String borrowerLastName, String borrowerFirstName) {
		checkInBooks = new ArrayList<CheckInBooksSearch>();
		Connection connection = DatabaseConnector.getConnection();

		Statement stmt;
		try {
			stmt = connection.createStatement();
			
			String query = "select bl.card_no,b.book_id,b.title,bl.branch_id,lname,fname,address " +
							" from book_loans bl, borrower br, book b " + 
							" where bl.card_no=br.card_no and bl.book_id = b.book_id and ";
			

			boolean isThisFirst = true;
			
			if(!isEmpty(cardNo)) {
				query += getFormattedString(isThisFirst, "br.card_no", cardNo);
				isThisFirst = false;
			}
			if(!isEmpty(bookId)){
				query += getFormattedString(isThisFirst, "b.book_id", bookId);
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
				CheckInBooksSearch checkInBook = new CheckInBooksSearch();
				checkInBook.setCardNo(rs.getString("card_no"));
				checkInBook.setBookId(rs.getString("book_id"));
				checkInBook.setTitle(rs.getString("title"));
				checkInBook.setBranchId(rs.getInt("branch_id"));
				checkInBook.setLastName(rs.getString("lname"));
				checkInBook.setFirstName(rs.getString("fname"));
				checkInBook.setAddress(rs.getString("address"));

				// Display values
				logger.info("Card No: " + checkInBook.getCardNo());
				logger.info(", Book Id: " + checkInBook.getBookId());
				logger.info(", Title : " + checkInBook.getTitle());
				logger.info(", Branch Id: " + checkInBook.getBranchId());
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

	public boolean checkin(List<CheckinBooks> checkedinBooks) {
		Connection connection = DatabaseConnector.getConnection();
		int rs = 0;
		Statement stmt;
		try {
			stmt = connection.createStatement();
			CheckinBooks checkinBooks = new CheckinBooks();
			Iterator<CheckinBooks> iterator = checkedinBooks.iterator();
			while (iterator.hasNext()) {
				checkinBooks = iterator.next();
				logger.info(checkinBooks.getCardNo());
				logger.info(checkinBooks.getBookId());
				logger.info(""+checkinBooks.getBranchId());
				String query = "select date_out from book_loans where card_no ='"+checkinBooks.getCardNo()+"' AND  " +
						" book_id = '"+checkinBooks.getBookId()+"' AND branch_id = "+checkinBooks.getBranchId()+";";
				logger.info(query);
				ResultSet rsSelect = stmt.executeQuery(query);
				Date dateOut;
				rsSelect.next();
				dateOut = rsSelect.getDate("date_out");
				logger.info("Date out: " + dateOut);
				String sql = "DELETE FROM `BOOK_LOANS` WHERE card_no ='"+checkinBooks.getCardNo()+"' AND  " +
						" book_id = '"+checkinBooks.getBookId()+"' AND branch_id = "+checkinBooks.getBranchId()+";";
				logger.info(sql);
				rs = stmt.executeUpdate(sql);
				if(rs > 0){
					String sqlInsert ="INSERT INTO `book_loans_hist` "+
							"(`book_id`,`branch_id`,`card_no`,`date_out`,`date_in`) VALUES"+
									" ('"+checkinBooks.getBookId()+"',"+checkinBooks.getBranchId()+
									",'"+checkinBooks.getCardNo()+"','"+dateOut+"',curdate());";
							logger.info(sqlInsert);
							rs = stmt.executeUpdate(sqlInsert);
				}
			}
			stmt.close();
			return rs > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseConnector.returnConnection(connection);
		}
		return false;
	}

}
