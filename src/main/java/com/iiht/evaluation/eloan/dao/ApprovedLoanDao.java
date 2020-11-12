package com.iiht.evaluation.eloan.dao;

import java.sql.SQLException;

import com.iiht.evaluation.eloan.model.ApprovedLoan;

public interface ApprovedLoanDao {

	public boolean approveLoan (ApprovedLoan loan) throws ClassNotFoundException, SQLException;
	
}
