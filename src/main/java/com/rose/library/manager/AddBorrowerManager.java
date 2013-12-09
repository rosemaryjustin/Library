package com.rose.library.manager;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rose.library.DAO.AddBorrowerDAO;
import com.rose.library.DO.Borrower;

public class AddBorrowerManager {
	private static final Logger logger = LoggerFactory.getLogger(AddBorrowerManager.class);
	public static final String NAME_AND_ADDRESS_VALUES_ARE_MANDATORY_ERROR = "Name and Address Values are mandatory. Please make sure their values are entered.";
	public static final String DUPLICATE_COMBINATION_OF_NAME_AND_ADDRESS_ERROR = "Duplicate combination of Name and Address exists. Cannot issue duplicate card.";
	public static final String FAIL_MESSAGE = "Error! Could not add this Borrower.";
	public static final String SUCCESS_MESSAGE = "Borrower added successfully.";
	
	public static final String EDIT_FAIL_MESSAGE = "Error! Could not edit this Borrower.";
	public static final String EDIT_SUCCESS_MESSAGE = "Borrower updated successfully.";
	
	int phoneNumberEmpty = 1;
	
	public String addBorrower(String borrowerLastName,
			String borrowerFirstName, String address, String phoneNumber) {
		boolean isNotComplete = (borrowerLastName.isEmpty() || borrowerFirstName.isEmpty() || address.isEmpty()) ;
		if(isNotComplete){
			logger.info("Values of either the Name or Address is missing");
			return NAME_AND_ADDRESS_VALUES_ARE_MANDATORY_ERROR;
		}
		boolean isDuplicate = new AddBorrowerDAO().checkDuplicateCard(borrowerLastName, borrowerFirstName, address);
		if(isDuplicate){
			return DUPLICATE_COMBINATION_OF_NAME_AND_ADDRESS_ERROR;
		}
		int cardNo = new AddBorrowerDAO().getLastCardNoIssued();
		cardNo += 1; // The Card No for the new borrower
		logger.info(phoneNumber);
		if(phoneNumber.isEmpty())	
		{
			logger.info("Phone number is empty !");
			phoneNumberEmpty = 0;
		}
		return new AddBorrowerDAO().addBorrower(borrowerLastName, borrowerFirstName, address,phoneNumber,cardNo,phoneNumberEmpty)? SUCCESS_MESSAGE : FAIL_MESSAGE;
	}
	
	public List<Borrower> searchBorrower(String cardNo, String borrowerLastName,
			String borrowerFirstName, String phoneNumber) {

		return new AddBorrowerDAO().getBorrowers(cardNo, borrowerLastName, borrowerFirstName, phoneNumber);
	}

	public String updateBorrower(String cardNo, String borrowerFirstName,
			String borrowerLastName, String address, String phoneNumber) {
		
		return new AddBorrowerDAO().updateBorrower(cardNo,
				borrowerFirstName,borrowerLastName,address,phoneNumber)?EDIT_SUCCESS_MESSAGE : EDIT_FAIL_MESSAGE;
		
	}

}
