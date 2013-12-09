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
import com.rose.library.DO.BookLoanHistory;
import com.rose.library.DO.Borrower;
import com.rose.library.DO.Branch;

public class AdminDAO {
	private static final Logger logger = LoggerFactory.getLogger(AdminDAO.class);
	
	public List<BookLoanHistory> getBookLoanHistory(String cardNo,
			String bookId, int branchId) {
		Connection connection = DatabaseConnector.getConnection();
		List<BookLoanHistory> bookLoans = new ArrayList<BookLoanHistory>();
		Statement stmt;
		try {
			stmt = connection.createStatement();
 
			String sql = "select * from book_loans_hist " +
            "where card_no like '%"+cardNo+"%' and book_id like '%"
					+bookId+"%' ";
			if(branchId > 0)  {
				sql +=  " and branch_id = " + branchId;
			} 
			sql += ";";
			logger.info(sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				BookLoanHistory history = new BookLoanHistory();
				history.setCardNo(rs.getString("card_no"));
				history.setBookId(rs.getString("book_id"));
				history.setBranchId(rs.getInt("branch_id"));
				history.setCheckOut(rs.getDate("date_out"));
				history.setCheckIn(rs.getDate("date_in"));
				bookLoans.add(history);
			}
			rs.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseConnector.returnConnection(connection);
		}

		return bookLoans;
	}
	
	public List<Branch> getBranches() {
		Connection connection = DatabaseConnector.getConnection();
		List<Branch> branches = new ArrayList<Branch>();
		Statement stmt;
		try {
			stmt = connection.createStatement();
 
			String sql = "select * from library_branch ;";
			logger.info(sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Branch branch = new Branch();
				branch.setAddress(rs.getString("address"));
				branch.setBranchName(rs.getString("branch_name"));
				branch.setBranchId(rs.getInt("branch_id"));
				branches.add(branch);
			}
			rs.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseConnector.returnConnection(connection);
		}

		return branches;
	}
	
}
