package com.iiht.evaluation.eloan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.iiht.evaluation.eloan.model.ApprovedLoan;

public class ApprovedLoanDaoImpl implements ApprovedLoanDao {

	private Connection jdbcConnection;
	PreparedStatement pstmt;
	Context ctx=null;
	private DataSource ds;
	
	public ApprovedLoanDaoImpl() {
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
	
	public boolean approveLoan (ApprovedLoan loan) throws ClassNotFoundException, SQLException {
		String sql = "insert into approvedloan (applno,amotsanctioned,loanterm,psd,lcd,emi) values(?,?,?,?,?,?)";
		this.connect();
		
		pstmt = this.jdbcConnection.prepareStatement(sql);
		
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
	
}
