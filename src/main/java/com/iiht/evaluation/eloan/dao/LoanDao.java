package com.iiht.evaluation.eloan.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;

import com.iiht.evaluation.eloan.dto.LoanDto;
import com.iiht.evaluation.eloan.model.ApprovedLoan;
import com.iiht.evaluation.eloan.model.LoanInfo;
import com.iiht.evaluation.eloan.model.User;

public class LoanDao {
	private static final long serialVersionUID = 1L;
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public LoanDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

	public  Connection connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}
		return jdbcConnection;
	}

	public void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}
	
	public boolean validateUser(String username, String password) throws ClassNotFoundException, SQLException {
		boolean flag=false;
		String sql = "select * from user where username = '"+username+"'";
		this.connect();
		
		Statement stmt = this.jdbcConnection.createStatement();
		ResultSet rs =  stmt.executeQuery(sql);
		if(rs.next()) {
			String dbPassword = rs.getString(4);
			if (dbPassword.equals(password)) {
				flag=true;
			}
		}
		rs.close();
		stmt.close();
		this.disconnect();
		return flag;
	}
	
	public boolean validateUsernameExists(String username) throws ClassNotFoundException, SQLException {
		boolean flag=false;
		String sql = "select * from user where username = '"+username+"'";
		this.connect();
		
		Statement stmt = this.jdbcConnection.createStatement();
		ResultSet rs =  stmt.executeQuery(sql);
		if(rs.next()) {
				flag=true;
			}
		rs.close();
		stmt.close();
		this.disconnect();
		return flag;
	}
	
	public boolean addUserRecord (User user) throws ClassNotFoundException, SQLException {
		String sql = "insert into user (firstname,lastname,username,password) values(?,?,?,?)";
		this.connect();
		
		PreparedStatement pstmt = this.jdbcConnection.prepareStatement(sql);
		
		pstmt.setString(1, user.getFirstname());
		pstmt.setString(2, user.getLastname());
		pstmt.setString(3, user.getUsername());
		pstmt.setString(4, user.getPassword());
		
		int n = pstmt.executeUpdate();
		
		pstmt.close();
		this.disconnect();
		
		if(n>0)
			return true;
		return false;
	}
}
