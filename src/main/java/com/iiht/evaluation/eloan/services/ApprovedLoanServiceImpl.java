package com.iiht.evaluation.eloan.services;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.iiht.evaluation.eloan.dao.ApprovedLoanDaoImpl;
import com.iiht.evaluation.eloan.model.ApprovedLoan;

public class ApprovedLoanServiceImpl implements ApprovedLoanService {

	private ApprovedLoanDaoImpl approvedLoanDao;
	
	public ApprovedLoanServiceImpl() {
		approvedLoanDao = new ApprovedLoanDaoImpl();
	}
	
	public int calculateEmi(int loanAmtSanctioned,int term)  {
		
		int termPaymentAmount=loanAmtSanctioned*(1+(10/100))^term;
		int emi=termPaymentAmount/term;
		
		return emi;	
	}
	
	public String calculateLoanClosureDate(String paymentstrtdate,int loanAmtSanctioned,int term,int emi)  {
		
		int months=(loanAmtSanctioned*(1+(10/100))^term)/emi;
		String loanclosureDate=LocalDate.parse(paymentstrtdate, DateTimeFormatter.ofPattern("yyyy-MM-dd")).plusMonths(months).toString();
		
		return loanclosureDate;	
	}
	
	public boolean approveLoan(ApprovedLoan loan) throws ClassNotFoundException, SQLException {
		
		return approvedLoanDao.approveLoan(loan);
	}
	
}
