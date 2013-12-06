package com.rose.library.manager;

import java.util.ArrayList;
import java.util.List;

import com.rose.library.DAO.CheckAvailablityDAO;
import com.rose.library.DO.AvailableBooks;

public class CheckAvailablityManager {
	List<AvailableBooks> availableBooks = new ArrayList<AvailableBooks>();
	int case_sql;
	
	public List<AvailableBooks> checkAvailablity(String bookId, String title,
			String authorName) {
			
			if(bookId.isEmpty() && title.isEmpty() && authorName.isEmpty() ){
				case_sql = 0;
			}else if(bookId.length() != 0 && title.isEmpty() && authorName.isEmpty()){
				case_sql = 1;
			}else if(bookId.isEmpty() && title.length() != 0 && authorName.isEmpty()){
				case_sql = 2;
			}else if(bookId.isEmpty() && title.isEmpty() && authorName.length() != 0){
				case_sql = 3;
			}else if(bookId.length() != 0 && title.length() != 0 && authorName.isEmpty()){
				case_sql = 4;
			}else if(bookId.length() != 0 && title.isEmpty() && authorName.length() != 0){
				case_sql = 5;
			}else if(bookId.isEmpty() && title.length() != 0 && authorName.length() != 0){
				case_sql = 6;
			}else if(bookId.length() != 0 && title.length() != 0 && authorName.length() != 0){
				case_sql = 7;		
			}
		
			availableBooks = new CheckAvailablityDAO().checkAvailablity(bookId, title, authorName,case_sql);
		return availableBooks;
	}

}
