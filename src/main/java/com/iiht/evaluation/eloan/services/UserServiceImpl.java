package com.iiht.evaluation.eloan.services;

import java.sql.SQLException;

import com.iiht.evaluation.eloan.dao.UserDaoImpl;
import com.iiht.evaluation.eloan.model.User;

public class UserServiceImpl implements UserService {
	
	private UserDaoImpl userDao;
	
	public UserServiceImpl() {
		userDao = new UserDaoImpl();
	}

	public boolean doUserLogin(String username,String password) throws ClassNotFoundException, SQLException  {
				
		if(userDao.validateUser(username, password))
			return true;
		else
			return false;	
	}
	
	
	public boolean registrationValidation(String username) throws ClassNotFoundException, SQLException {
		
		if(userDao.validateUsernameExists(username))
			return true;
		else
			return false;
	}
	
	public boolean addUser(User user) throws ClassNotFoundException, SQLException {
		
		if(userDao.addUserRecord(user))
			return true;
		else
			return false;
	}
	
	
}
