package com.rose.library.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rose.database.DatabaseConnector;

public class CheckoutDAO {
	private static final Logger logger = LoggerFactory.getLogger(CheckoutDAO.class);

	public boolean isAvailable(String bookId, String branchId, String cardNo) {
		Connection connection = DatabaseConnector.getConnection();

		Statement stmt;
		try {
			stmt = connection.createStatement();

			String sql = "select no_of_copies - (select count(*) from book_loans where book_id = bc.book_id and  branch_id = bc.branch_id) as available_copies\n"
					+ "from book_copies bc where book_id = '"
					+ bookId
					+ "' and branch_id = " + branchId + ";";
			logger.info(sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int availableCopies = rs.getInt("available_copies");
				System.out.print("Available Copies: " + availableCopies);
				return availableCopies > 0;
			}
			rs.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseConnector.returnConnection(connection);
		}

		return false;
	}

	public int getNumberOfBorrowedBooks(String bookId, String branchId,
			String cardNo) {

		Connection connection = DatabaseConnector.getConnection();
		Statement stmt;
		try {
			stmt = connection.createStatement();

			String sql = "select count(*) as borrowed_books from book_loans where card_no='"
					+ cardNo + "';";
			logger.info(sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int borrowedBooks = rs.getInt("borrowed_books");
				System.out.print("Borrowed Books: " + borrowedBooks);
				return borrowedBooks;
			}
			rs.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseConnector.returnConnection(connection);
		}

		return Integer.MAX_VALUE;
	}

	public boolean checkout(String bookId, String branchId, String cardNo) {
		// TODO Auto-generated method stub
		Connection connection = DatabaseConnector.getConnection();

		Statement stmt;
		try {
			stmt = connection.createStatement();

			String sql = "INSERT INTO `BOOK_LOANS` (`book_id`,`branch_id`,`card_no`,`date_out`,`due_date`) "
					+ "VALUES ('%s',%s,'%s',curdate(),date_add(curdate(),INTERVAL 14 DAY));";
			sql = String.format(sql, bookId, branchId, cardNo);
			logger.info(sql);
			int rs = stmt.executeUpdate(sql);
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
