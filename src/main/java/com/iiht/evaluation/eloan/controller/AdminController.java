package com.iiht.evaluation.eloan.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iiht.evaluation.eloan.dao.ConnectionDao;
import com.iiht.evaluation.eloan.dto.LoanDto;
import com.iiht.evaluation.eloan.model.ApprovedLoan;
import com.iiht.evaluation.eloan.model.LoanInfo;


@WebServlet("/admin")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ConnectionDao connDao;
	
	public void setConnDao(ConnectionDao connDao) {
		this.connDao = connDao;
	}
	public void init(ServletConfig config) {
		String jdbcURL = config.getServletContext().getInitParameter("jdbcUrl");
		String jdbcUsername = config.getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = config.getServletContext().getInitParameter("jdbcPassword");
		System.out.println(jdbcURL + jdbcUsername + jdbcPassword);
		this.connDao = new ConnectionDao(jdbcURL, jdbcUsername, jdbcPassword);
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
		
		int termPaymentAmount=loanAmtSanctioned*(1+10/100)^term;
		int emi=termPaymentAmount/term;
		int months=termPaymentAmount/emi;
		String loanclosureDate=LocalDate.parse(paymentstrtdate, DateTimeFormatter.ofPattern("yyyy-MM-dd")).plusMonths(months).toString();
		String appno= request.getParameter("appno") ;
		ApprovedLoan loan=new ApprovedLoan(appno,loanAmtSanctioned,term,paymentstrtdate,loanclosureDate,emi);
		if(connDao.approveLoan(loan)) {
			request.setAttribute("message", "Loan Approved");
			LoanInfo info=new LoanInfo(appno,"Approved");
			LoanInfo info2=connDao.getLoanStatus(appno);
			request.setAttribute("LoanInfo", info2);
			if(connDao.updateApproved(info))
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
			connDao.connect();
			String appno=request.getParameter("appno");
			if(connDao.validateApplicationNumber(appno)) {
				//request.setAttribute("message", "Application found");
				request.setAttribute("appno", appno);
				LoanInfo info=connDao.getLoanStatus(appno);
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
			HttpSession session = request.getSession();
			session.setAttribute("isAdmin", null);
			view = "index.jsp";
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			view = "error.jsp";
		}
		return view;
	}

	private String listall(HttpServletRequest request, HttpServletResponse response) throws SQLException {
	/* write the code to display all the loans */
		String view="";
		try {
			connDao.connect();
			ArrayList<LoanInfo> list=connDao.getAllLoans();
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