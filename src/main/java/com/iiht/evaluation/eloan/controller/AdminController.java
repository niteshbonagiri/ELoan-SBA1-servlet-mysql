package com.iiht.evaluation.eloan.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iiht.evaluation.eloan.model.ApprovedLoan;
import com.iiht.evaluation.eloan.model.LoanInfo;
import com.iiht.evaluation.eloan.services.ApprovedLoanServiceImpl;
import com.iiht.evaluation.eloan.services.LoanInfoServiceImpl;


@WebServlet("/admin")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ApprovedLoanServiceImpl approveLoanImpl;
	private LoanInfoServiceImpl loanInfoImpl;
	
	public void init() {
		approveLoanImpl=new ApprovedLoanServiceImpl();
		loanInfoImpl=new LoanInfoServiceImpl();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action =  request.getParameter("action");
		System.out.println(action);
		String viewName = "";
		try {
			switch (action) {
			case "listall" : 
				viewName = listall(request, response);
				break;
			case "process":
				viewName=process(request,response);
				break;
			case "callemi":
				viewName=calemi(request,response);
				break;
			case "updatestatus":
				viewName=updatestatus(request,response);
				break;
			case "logout":
				viewName = adminLogout(request, response);
				break;	
			default : viewName = "notfound.jsp"; break;		
			}
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
		RequestDispatcher dispatch = 
					request.getRequestDispatcher(viewName);
		dispatch.forward(request, response);
		
		
	}

	private String updatestatus(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		/* write the code for updatestatus of loan and return to admin home page */
		String view="";
		try {
		int loanAmtSanctioned=Integer.parseInt(request.getParameter("loanAmtSanctioned"));
		int term=Integer.parseInt(request.getParameter("term"));
		String paymentstrtdate=request.getParameter("paymentstrtdate");
		int emi=approveLoanImpl.calculateEmi(loanAmtSanctioned,term);
		String loanclosureDate=approveLoanImpl.calculateLoanClosureDate(paymentstrtdate, loanAmtSanctioned, term, emi);
		String appno= request.getParameter("appno") ;
		ApprovedLoan loan=new ApprovedLoan(appno,loanAmtSanctioned,term,paymentstrtdate,loanclosureDate,emi);
		if(approveLoanImpl.approveLoan(loan)) {
			request.setAttribute("message", "Loan Approved");
			LoanInfo info=new LoanInfo(appno,"Approved");
			LoanInfo info2=loanInfoImpl.getLoanStatus(appno);
			request.setAttribute("LoanInfo", info2);
			if(loanInfoImpl.updateApproved(info))
			{
				request.setAttribute("status", "Approved");
			}
				view = "calemi.jsp";
		}
			} catch (Exception e) {
				request.setAttribute("error", e.getMessage());
				view = "errorPage.jsp";
			}
	return view;
	}
	
	private String calemi(HttpServletRequest request, HttpServletResponse response) throws SQLException {
	/* write the code to calculate emi for given applno and display the details */
		String view = "";
		try {
			String appno=request.getParameter("appno");
			if(loanInfoImpl.validateApplicationNumber(appno)) {
				request.setAttribute("appno", appno);
				LoanInfo info=loanInfoImpl.getLoanStatus(appno);
				if(info.getStatus().equals("Approved")) {
					request.setAttribute("message", "Loan Already Approved");
					view = "process.jsp";
				}else {
					request.setAttribute("LoanInfo", info);
					view = "calemi.jsp";
				}
				
			} else {
				request.setAttribute("message", "Application Not found");
				view = "process.jsp";
			}
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			view = "errorPage.jsp";
		}
		return view;
	}
	private String process(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
	/* return to process page */
		String view = "";
		try {
			view = "process.jsp";
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			view = "errorPage.jsp";
		}
		return view;
	}
	private String adminLogout(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	/* write code to return index page */
		String view = "";
		try {
			HttpSession session = request.getSession(false);
			if(session!=null) {
				session.invalidate();
			}
			//session.setAttribute("username", null);
			view = "index.jsp";
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			view = "errorPage.jsp";
		}
		return view;
	}

	private String listall(HttpServletRequest request, HttpServletResponse response) throws SQLException {
	/* write the code to display all the loans */
		String view="";
		try {
			ArrayList<LoanInfo> list=loanInfoImpl.getAllLoans();
			request.setAttribute("LoanInfo", list);
			view= "listall.jsp";
		}
		catch(Exception e) {
			request.setAttribute("error", e.getMessage());
			view = "errorPage.jsp";
		}
		return view;
	}

	
}