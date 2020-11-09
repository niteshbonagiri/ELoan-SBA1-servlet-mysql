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

public class ConnectionDao {
	private static final long serialVersionUID = 1L;
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public ConnectionDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
	
	public int generateApplicationNumber() throws ClassNotFoundException, SQLException {
		String sql = "select count(*) from loaninfo";
		this.connect();
		int num=0;
		Statement stmt = this.jdbcConnection.createStatement();
		ResultSet rs =  stmt.executeQuery(sql);
		if(rs.next()) {
			num=rs.getInt(1);
		}
		System.out.println(num);
		return num+1;
	}
	
	public boolean applyLoan (LoanInfo info) throws ClassNotFoundException, SQLException {
		String sql = "insert into loaninfo (applno,purpose,amtrequest,dao,bstructure,bindicator,taxindicator,address,email,mobile,status,username) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		this.connect();
		
		PreparedStatement pstmt = this.jdbcConnection.prepareStatement(sql);
		
		pstmt.setString(1, info.getApplno());
		pstmt.setString(2, info.getPurpose());
		pstmt.setInt(3, info.getAmtrequest());
		pstmt.setString(4, info.getDoa());
		pstmt.setString(5, info.getBstructure());
		pstmt.setString(6, info.getBindicator());
		pstmt.setString(7, info.getTaxindicator());
		pstmt.setString(8, info.getAddress());
		pstmt.setString(9, info.getEmail());
		pstmt.setString(10, info.getMobile());
		pstmt.setString(11, info.getStatus());
		pstmt.setString(12, info.getUsername());
		
		int n = pstmt.executeUpdate();
		
		pstmt.close();
		this.disconnect();
		
		if(n>0)
			return true;
		return false;
	}
	
	public boolean editLoan (LoanInfo info) throws ClassNotFoundException, SQLException {
		String sql = "update loaninfo set purpose=?,amtrequest=?,dao=?,bstructure=?,bindicator=?,taxindicator=?,address=?,email=?,mobile=?,status=?,username=? where applno=?";
		this.connect();
		
		PreparedStatement pstmt = this.jdbcConnection.prepareStatement(sql);
		
		pstmt.setString(1, info.getPurpose());
		pstmt.setInt(2, info.getAmtrequest());
		pstmt.setString(3, info.getDoa());
		pstmt.setString(4, info.getBstructure());
		pstmt.setString(5, info.getBindicator());
		pstmt.setString(6, info.getTaxindicator());
		pstmt.setString(7, info.getAddress());
		pstmt.setString(8, info.getEmail());
		pstmt.setString(9, info.getMobile());
		pstmt.setString(10, info.getStatus());
		pstmt.setString(11, info.getUsername());
		pstmt.setString(12, info.getApplno());
		
		int n = pstmt.executeUpdate();
		
		pstmt.close();
		this.disconnect();
		
		if(n>0)
			return true;
		return false;
	}
	
	public LoanInfo getLoanStatus (String appno) throws ClassNotFoundException, SQLException {
		String sql = "select * from loaninfo where applno="+appno;
		this.connect();
		LoanInfo info = null;
		Statement stmt = this.jdbcConnection.createStatement();
		ResultSet rs =  stmt.executeQuery(sql);
		if(rs.next()) {
			info=new LoanInfo(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
					rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12));
		}
		
		stmt.close();
		this.disconnect();
	
		return info;
	}
	
	public boolean validateApplicationNumber(String appno) throws ClassNotFoundException, SQLException {
		boolean flag=false;
		String sql = "select * from loaninfo where applno ="+appno;
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
	
	public ArrayList<LoanInfo> getAllLoans () throws ClassNotFoundException, SQLException {
		String sql = "select * from loaninfo";
		this.connect();
		LoanInfo info = null;
		ArrayList<LoanInfo> list=new ArrayList<LoanInfo>();
		Statement stmt = this.jdbcConnection.createStatement();
		ResultSet rs =  stmt.executeQuery(sql);
		while(rs.next()) {
			info=new LoanInfo(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
					rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12));
			list.add(info);
		}
		
		stmt.close();
		this.disconnect();
		return list;
	}
	
	public boolean approveLoan (ApprovedLoan loan) throws ClassNotFoundException, SQLException {
		String sql = "insert into approvedloan (applno,amotsanctioned,loanterm,psd,lcd,emi) values(?,?,?,?,?,?)";
		this.connect();
		
		PreparedStatement pstmt = this.jdbcConnection.prepareStatement(sql);
		
		pstmt.setString(1, loan.getApplno());
		pstmt.setInt(2, loan.getAmotsanctioned());
		pstmt.setInt(3, loan.getLoanterm());
		pstmt.setString(4, loan.getPsd());
		pstmt.setString(5, loan.getLcd());
		pstmt.setInt(6, loan.getEmi());
	
		
		int n = pstmt.executeUpdate();
		
		pstmt.close();
		this.disconnect();
		
		if(n>0)
			return true;
		return false;
	}
	
	public boolean updateApproved (LoanInfo info) throws ClassNotFoundException, SQLException {
		String sql = "update loaninfo set status=? where applno=?";
		this.connect();
		
		PreparedStatement pstmt = this.jdbcConnection.prepareStatement(sql);
		
		
		pstmt.setString(1, info.getStatus());
		pstmt.setString(2, info.getApplno());
		
		int n = pstmt.executeUpdate();
		
		pstmt.close();
		this.disconnect();
		
		if(n>0)
			return true;
		return false;
	}
}
