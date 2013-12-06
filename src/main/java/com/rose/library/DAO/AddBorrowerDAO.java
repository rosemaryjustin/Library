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
		Connection connection = DatabaseConnector.getConnection();

		Statement stmt;
		try {
			stmt = connection.createStatement();

			String sql = "select max(card_no) as last_card_no from borrower;";
			logger.info(sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int lastCardNo = rs.getInt("last_card_no");
				logger.info("Last Card No: " + lastCardNo);
				return lastCardNo;
			}
			rs.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseConnector.returnConnection(connection);
		}

		return 0;
	}
	
	public boolean addBorrower(String borrowerLastName,
			String borrowerFirstName, String address, String phoneNumber, int cardNo, int phoneNumberEmpty) {
		
		Connection connection = DatabaseConnector.getConnection();

		Statement stmt;
		try {
			stmt = connection.createStatement();
			String sql = "";
			if(phoneNumberEmpty != 0){
				sql ="INSERT INTO `BORROWER` (`card_no`,`fname`,`lname`,`address`,`phone`) VALUES"
						+ " ('%s','%s','%s','%s','%s');";
				sql = String.format(sql, cardNo, borrowerFirstName, borrowerLastName, address,phoneNumber);
			}else if(phoneNumberEmpty == 0){
				sql ="INSERT INTO `BORROWER` (`card_no`,`fname`,`lname`,`address`) VALUES"
						+ " ('%s','%s','%s','%s');";
				sql = String.format(sql, cardNo, borrowerFirstName, borrowerLastName, address);
			}
			
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
