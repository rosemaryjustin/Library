package com.rose.library.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rose.database.DatabaseConnector;

public class AddBorrowerDAO {
	private static final Logger logger = LoggerFactory.getLogger(AddBorrowerDAO.class);

	public boolean checkDuplicateCard(String borrowerLastName,
			String borrowerFirstName, String address) {
		Connection connection = DatabaseConnector.getConnection();

		Statement stmt;
		try {
			stmt = connection.createStatement();

			String sql = "select count(*) as duplicate_count from borrower " +
            "where lname='"+borrowerLastName+"' and fname ='"+borrowerFirstName+"' and address = '"+address+"';";
			logger.info(sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int noDuplicateBorrowers = rs.getInt("duplicate_count");
				System.out.print("Duplicate Borrower Count: " + noDuplicateBorrowers);
				return noDuplicateBorrowers > 0;
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

	public int getLastCardNoIssued() {

		return 0;
	}
	
	public String addBorrower(String borrowerLastName,
			String borrowerFirstName, String address, String phoneNumber, int cardNo) {

		return null;
	}
}
