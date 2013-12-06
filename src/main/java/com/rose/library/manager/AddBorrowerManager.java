package com.rose.library.manager;

import com.rose.library.DAO.AddBorrowerDAO;

public class AddBorrowerManager {

	private static final String DUPLICATE_COMBINATION_OF_NAME_AND_ADDRESS_ERROR = "Duplicate combination of Name and Address exists. Cannot issue duplicate card.";

	public String addBorrower(String borrowerLastName,
			String borrowerFirstName, String address, String phoneNumber) {
		boolean isDuplicate = new AddBorrowerDAO().checkDuplicateCard(borrowerLastName, borrowerFirstName, address);
		if(isDuplicate){
			return DUPLICATE_COMBINATION_OF_NAME_AND_ADDRESS_ERROR;
		}
		int cardNo = new AddBorrowerDAO().getLastCardNoIssued();
		
		String msg = new AddBorrowerDAO().addBorrower(borrowerLastName, borrowerFirstName, address,phoneNumber,cardNo);

		return msg;
	}

}
