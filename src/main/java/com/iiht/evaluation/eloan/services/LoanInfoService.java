package com.iiht.evaluation.eloan.services;

import java.sql.SQLException;
import java.util.ArrayList;

import com.iiht.evaluation.eloan.model.LoanInfo;

public interface LoanInfoService {
	
	public boolean applyLoan(LoanInfo info) throws ClassNotFoundException, SQLException;
	
	public boolean editLoan(LoanInfo info) throws ClassNotFoundException, SQLException;
	
	public LoanInfo getLoanStatus(String appno) throws ClassNotFoundException, SQLException;
	
	public boolean validateApplicationNumber(String appno,String username) throws ClassNotFoundException, SQLException;
	
	public int generateApplicationNumber() throws ClassNotFoundException, SQLException;
	
	public boolean updateApproved(LoanInfo info) throws ClassNotFoundException, SQLException;
	
	public boolean validateApplicationNumber(String appno) throws ClassNotFoundException, SQLException;
	
	public ArrayList<LoanInfo> getAllLoans() throws ClassNotFoundException, SQLException;
}
