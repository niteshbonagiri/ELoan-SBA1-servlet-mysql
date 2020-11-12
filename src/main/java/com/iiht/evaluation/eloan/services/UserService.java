package com.iiht.evaluation.eloan.services;

import java.sql.SQLException;

import com.iiht.evaluation.eloan.model.User;

public interface UserService {
	
	public boolean doUserLogin(String username,String password) throws ClassNotFoundException, SQLException;
	
	public boolean registrationValidation(String username) throws ClassNotFoundException, SQLException;
	
	public boolean addUser(User user) throws ClassNotFoundException, SQLException;

}
