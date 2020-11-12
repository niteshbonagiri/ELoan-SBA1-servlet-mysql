package com.iiht.evaluation.eloan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.iiht.evaluation.eloan.model.User;

public class UserDaoImpl implements UserDao {

	private Connection jdbcConnection;
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;
	Context ctx=null;

	private DataSource ds;
	
	public UserDaoImpl() {
	try {
		ctx=new InitialContext();
		ds=(DataSource) ctx.lookup("java:/comp/env/jdbc/eloan");
		this.connect();
	} catch (NamingException | SQLException e) {
		e.printStackTrace();
		}
    }
	
	public  Connection connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {	
			jdbcConnection = ds.getConnection();
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
		
		stmt = this.jdbcConnection.createStatement();
		rs =  stmt.executeQuery(sql);
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
		
		stmt = this.jdbcConnection.createStatement();
		rs =  stmt.executeQuery(sql);
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
		
		pstmt = this.jdbcConnection.prepareStatement(sql);
		
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
