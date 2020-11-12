package com.iiht.evaluation.eloan.services;

import java.sql.SQLException;

import com.iiht.evaluation.eloan.model.ApprovedLoan;

public interface ApprovedLoanService {

	public int calculateEmi(int loanAmtSanctioned,int term);
	
	public String calculateLoanClosureDate(String paymentstrtdate,int loanAmtSanctioned,int term,int emi);
	
	public boolean approveLoan(ApprovedLoan loan) throws ClassNotFoundException, SQLException;
}
