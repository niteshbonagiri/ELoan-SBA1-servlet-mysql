package com.iiht.evaluation.eloan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.iiht.evaluation.eloan.model.LoanInfo;

public class LoanInfoDaoImpl implements LoanInfoDao{

	private Connection jdbcConnection;
	Statement stmt;
	ResultSet rs;
	PreparedStatement pstmt;
	Context ctx=null;

	private DataSource ds;
	
	public LoanInfoDaoImpl() {
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
	
	public int generateApplicationNumber() throws ClassNotFoundException, SQLException {
		String sql = "select count(*) from loaninfo";
		this.connect();
		int num=0;
		stmt = this.jdbcConnection.createStatement();
		rs =  stmt.executeQuery(sql);
		if(rs.next()) {
			num=rs.getInt(1);
		}
		System.out.println(num);
		return num+1;
	}
	
	public boolean applyLoan (LoanInfo info) throws ClassNotFoundException, SQLException {
		String sql = "insert into loaninfo (applno,purpose,amtrequest,dao,bstructure,bindicator,taxindicator,address,email,mobile,status,username) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		this.connect();
		
		pstmt = this.jdbcConnection.prepareStatement(sql);
		
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
		
		pstmt = this.jdbcConnection.prepareStatement(sql);
		
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
		stmt = this.jdbcConnection.createStatement();
		rs =  stmt.executeQuery(sql);
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
	
	public ArrayList<LoanInfo> getAllLoans () throws ClassNotFoundException, SQLException {
		String sql = "select * from loaninfo";
		this.connect();
		LoanInfo info = null;
		ArrayList<LoanInfo> list=new ArrayList<LoanInfo>();
		stmt = this.jdbcConnection.createStatement();
		rs =  stmt.executeQuery(sql);
		while(rs.next()) {
			info=new LoanInfo(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
					rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12));
			list.add(info);
		}
		
		stmt.close();
		this.disconnect();
		return list;
	}
	
	public boolean updateApproved (LoanInfo info) throws ClassNotFoundException, SQLException {
		String sql = "update loaninfo set status=? where applno=?";
		this.connect();
		
		pstmt = this.jdbcConnection.prepareStatement(sql);
		
		
		pstmt.setString(1, info.getStatus());
		pstmt.setString(2, info.getApplno());
		
		int n = pstmt.executeUpdate();
		
		pstmt.close();
		this.disconnect();
		
		if(n>0)
			return true;
		return false;
	}
	
	public boolean validateApplicationNumber(String appno,String username) throws ClassNotFoundException, SQLException {
		boolean flag=false;
		String sql = "select * from loaninfo where applno ="+appno +" and username='"+username+"'";
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
	
}
