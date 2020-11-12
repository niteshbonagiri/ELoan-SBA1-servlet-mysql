package com.iiht.evaluation.eloan.services;

import java.sql.SQLException;
import java.util.ArrayList;

import com.iiht.evaluation.eloan.dao.LoanInfoDaoImpl;
import com.iiht.evaluation.eloan.model.LoanInfo;

public class LoanInfoServiceImpl implements LoanInfoService {

	private LoanInfoDaoImpl loanDao;
	
	public LoanInfoServiceImpl() {
		loanDao = new LoanInfoDaoImpl();
	}
	
	public boolean applyLoan(LoanInfo info) throws ClassNotFoundException, SQLException {
		
		if(loanDao.applyLoan(info))
			return true;
		else
			return false;
	}
	
	public boolean editLoan(LoanInfo info) throws ClassNotFoundException, SQLException {
		
		if(loanDao.editLoan(info))
			return true;
		else
			return false;
	}
	
	public LoanInfo getLoanStatus(String appno) throws ClassNotFoundException, SQLException {
		
		return loanDao.getLoanStatus(appno);
	}
	
	public boolean validateApplicationNumber(String appno,String username) throws ClassNotFoundException, SQLException {
		
		if(loanDao.validateApplicationNumber(appno,username))
			return true;
		else
			return false;
	}
	
	public int generateApplicationNumber() throws ClassNotFoundException, SQLException {
		
		return loanDao.generateApplicationNumber();
	}
	
	public boolean updateApproved(LoanInfo info) throws ClassNotFoundException, SQLException {
		
		return loanDao.updateApproved(info);
	}
	
	public boolean validateApplicationNumber(String appno) throws ClassNotFoundException, SQLException {
		
		if(loanDao.validateApplicationNumber(appno))
			return true;
		else
			return false;
	}
	
	public ArrayList<LoanInfo> getAllLoans() throws ClassNotFoundException, SQLException {
		
		return loanDao.getAllLoans();
		
	}
	
}
