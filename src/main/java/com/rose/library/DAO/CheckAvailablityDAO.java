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
import com.rose.library.DO.AvailableBooks;

public class CheckAvailablityDAO {
	private static final Logger logger = LoggerFactory.getLogger(CheckoutDAO.class);
	List<AvailableBooks> availableBooks = new ArrayList<AvailableBooks>();

	public List<AvailableBooks> checkAvailablity(String bookId, String title,
			String authorName, int case_sql) {
		
		Connection connection = DatabaseConnector.getConnection();

		Statement stmt;
		try {
			stmt = connection.createStatement();
			
			String sql = "select b.book_id,lb.branch_id,no_of_copies as total_copies," +
		            "no_of_copies - (select count(*) from book_loans where book_id = b.book_id " +
					"and branch_id= lb.branch_id) as available_copies " +
		            "from book b,book_authors ba,book_copies bc,library_branch lb " +
		            "where b.book_id = ba.book_id and b.book_id = bc.book_id and lb.branch_id = bc.branch_id ";
		            switch(case_sql){
		            	case 0: sql = sql + "and (b.book_id ='"+bookId+"' or title ='"+title+"' or author_name ='"+authorName+"') ";
		            		break;
		            	case 1: sql = sql + "and (b.book_id like '%"+bookId+"%') ";
		            		break;
		            	case 2: sql = sql + "and (title like '%"+title+"%') ";
		            		break;
		            	case 3: sql = sql + "and (author_name like '%"+authorName+"%') ";
		            		break;
		            	case 4: sql = sql +  "and (b.book_id like '%"+bookId+"%' and title like '%"+title+"%')";
		            		break;
		            	case 5: sql = sql + "and (b.book_id like '%"+bookId+"%' and author_name like '%"+authorName+"%') ";
		            		break;
		            	case 6: sql = sql + "and (title like '%"+title+"%' and author_name like '%"+authorName+"%') ";
		            		break;
		            	case 7: sql = sql + "and (b.book_id like '%"+bookId+"%' and title like '%"+title+"%' and author_name like '%"+authorName+"%') ";
		            		break;
		            }
		           
		            
		         sql = sql + "group by book_id,branch_id;";
			
			logger.info(sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				AvailableBooks availableBook = new AvailableBooks();
				availableBook.setBookId(rs.getString("book_id"));  
				availableBook.setBranchId(rs.getInt("branch_id"));
				availableBook.setTotalCopies(rs.getInt("total_Copies"));
				availableBook.setAvailableCopies(rs.getInt("available_copies"));

				// Display values
				logger.info("Book Id: " + availableBook.getBookId());
				logger.info(",Branch Id: " + availableBook.getBranchId());
				logger.info(", Total Copies: " + availableBook.getTotalCopies());
				logger.info(", Available copies: " + availableBook.getAvailableCopies());
				availableBooks.add(availableBook);
			}
			rs.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseConnector.returnConnection(connection);
		}

		return availableBooks;
	}

}
