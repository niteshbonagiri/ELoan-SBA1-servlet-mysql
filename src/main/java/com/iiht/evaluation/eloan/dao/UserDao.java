package com.iiht.evaluation.eloan.dao;

import java.sql.SQLException;

import com.iiht.evaluation.eloan.model.User;

public interface UserDao {

	public boolean validateUser(String username,String password) throws ClassNotFoundException, SQLException;
	
	public boolean validateUsernameExists(String username) throws ClassNotFoundException, SQLException;
	
	public boolean addUserRecord (User user) throws ClassNotFoundException, SQLException ;
	
}
