package com.iiht.evaluation.eloan.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iiht.evaluation.eloan.dao.ConnectionDao;
import com.iiht.evaluation.eloan.model.LoanInfo;
import com.iiht.evaluation.eloan.model.User;




@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ConnectionDao connDao;
	HttpSession session;
	
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
		String action = request.getParameter("action");
		
		String viewName = "";
		try {
			switch (action) {
			case "registernewuser":
				viewName=registernewuser(request,response);
				break;
			case "validate":
				viewName=validate(request,response);
				break;
			case "placeloan":
				viewName=placeloan(request,response);
				break;
			case "application1":
				viewName=application1(request,response);
				break;
			case "editLoanProcess"  :
				viewName=editLoanProcess(request,response);
				break;
			case "registeruser":
				viewName=registerUser(request,response);
				break;
			case "register":
				viewName = register(request, response);
				break;
			case "application":
				viewName = application(request, response);
				break;
			case "trackloan":
				viewName = trackloan(request, response);
				break;
			case "editloan":
				viewName = editloan(request, response);
				break;	
			case  "displaystatus" :
				viewName=displaystatus(request,response);
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
	
	private String validate(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		/* write the code to validate the user */
		String view = "";
		try {
			session = request.getSession();
			connDao.connect();
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			//HttpSession session = request.getSession();
			if ((username.equals("admin")) && (password.equals("admin"))) {
				session.setAttribute("isAdmin", true);
				view = "adminhome1.jsp";
			} else {
				if(connDao.validateUser(username, password)) {
					session.setAttribute("username", username);
					view = "userhome1.jsp";
				} else {
					request.setAttribute("message", "Incorrect User Name or Password");
					view = "index.jsp";
				}
					
			}
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			view = "errorPage.jsp";
		}
		return view;
	}
	private String placeloan(HttpServletRequest request, HttpServletResponse response) {
	/* write the code to place the loan information */
		String view="";
		try {
		String loanappnumber=request.getParameter("loanappnumber");
		String loanname=request.getParameter("loanname");
		String loanamtrequested=request.getParameter("loanamtrequested");
		String loanapplicationdate=request.getParameter("loanapplicationdate");
		String businessstructure=request.getParameter("businessstructure");
		String billingindicator=request.getParameter("billingindicator");
		String taxpayer=request.getParameter("taxpayer");
		String address=request.getParameter("address");
		String email=request.getParameter("email");
		String mobile=request.getParameter("mobile");
		String username=(String) session.getAttribute("username");
		LoanInfo info=new LoanInfo(loanappnumber,loanname,Integer.parseInt(loanamtrequested),loanapplicationdate,
				businessstructure,billingindicator,taxpayer,address,email,mobile,"Pending",username);
		if(connDao.applyLoan(info)) {
			request.setAttribute("message", "Loan application successful");
				view = "application.jsp";
		}
	} catch (Exception e) {
		request.setAttribute("error", e.getMessage());
		view = "errorPage.jsp";
	}
	return view;
		
	}
	private String application1(HttpServletRequest request, HttpServletResponse response) {
	/* write the code to display the loan application page */
		
		return "application1.jsp";
	}
	private String editLoanProcess(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		/* write the code to edit the loan info */
		String view="";
		try {
		String loanappnumber=request.getParameter("loanappnumber");
		String loanname=request.getParameter("loanname");
		String loanamtrequested=request.getParameter("loanamtrequested");
		String loanapplicationdate=request.getParameter("loanapplicationdate");
		String businessstructure=request.getParameter("businessstructure");
		String billingindicator=request.getParameter("billingindicator");
		String taxpayer=request.getParameter("taxpayer");
		String address=request.getParameter("address");
		String email=request.getParameter("email");
		String mobile=request.getParameter("mobile");
		String username=(String) session.getAttribute("username");
		LoanInfo info=new LoanInfo(loanappnumber,loanname,Integer.parseInt(loanamtrequested),loanapplicationdate,
				businessstructure,billingindicator,taxpayer,address,email,mobile,"Pending",username);
		if(connDao.editLoan(info)) {
			request.setAttribute("message", "Loan application edit successful");
				view = "application.jsp";
		}
	} catch (Exception e) {
		request.setAttribute("error", e.getMessage());
		view = "errorPage.jsp";
	}
	return view;
	}
	private String registerUser(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		/* write the code to redirect page to read the user details */
		String view = "";
		try {
			connDao.connect();
			String username=request.getParameter("username");
			if(connDao.validateUsernameExists(username)) {
				request.setAttribute("message", "Username "+username +" already exists.Please choose a different Username");
					view = "newuserui.jsp";
			} else {
				view="user?action=registernewuser";
			}
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			view = "errorPage.jsp";
		}
		return view;
	}
	private String registernewuser(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		/* write the code to create the new user account read from user 
		   and return to index page */
		String view = "";
		try {
			connDao.connect();
			String firstname=request.getParameter("firstname");
			String lastname=request.getParameter("lastname");
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			User user=new User(firstname,lastname,username,password);
			if(connDao.addUserRecord(user)) {
				request.setAttribute("message", "Registration Successful");
					view = "newuserui.jsp";
			}
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			view = "errorPage.jsp";
		}
		return view;
	}
	
	private String register(HttpServletRequest request, HttpServletResponse response) {
		/* write the code to redirect to register page */
		
		return "newuserui.jsp";
	}
	private String displaystatus(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		/* write the code the display the loan status based on the given application
		   number 
		*/
		String view="";
		try {
			connDao.connect();
			String appno=(String) request.getAttribute("appno");
			LoanInfo info=connDao.getLoanStatus(appno);
			request.setAttribute("LoanInfo", info);
			view= "loanDetails.jsp";
		}
		catch(Exception e) {
			request.setAttribute("error", e.getMessage());
			view = "errorPage.jsp";
		}
		return view;
	}

	private String editloan(HttpServletRequest request, HttpServletResponse response) {
	/* write a code to return to editloan page */
		String view = "";
		try {
			connDao.connect();
			String appno=request.getParameter("appno");
			String username=(String) session.getAttribute("username");
			if(connDao.validateApplicationNumber(appno,username)) {
				request.setAttribute("appno", appno);
				LoanInfo info=connDao.getLoanStatus(appno);
				if(info.getStatus().equals("Approved")) {
					request.setAttribute("message", "Cannot Edit Application Already Approved");
					view = "editloan.jsp";
				}else {
				request.setAttribute("LoanInfo", info);
				view= "editloanui.jsp";
				}
			} else
			{
				request.setAttribute("message", "Application Not found");
				view = "editloan.jsp";
			}
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			view = "errorPage.jsp";
		}
		return view;
	}

	private String trackloan(HttpServletRequest request, HttpServletResponse response) {
	/* write a code to return to trackloan page */
		String view = "";
		try {
			connDao.connect();
			String appno=request.getParameter("appno");
			String username=(String) session.getAttribute("username");
			if(connDao.validateApplicationNumber(appno,username)) {
				request.setAttribute("message", "Application found");
				request.setAttribute("appno", appno);
				view = "user?action=displaystatus";
			} else
			{
				request.setAttribute("message", "Application Not found");
				view = "trackloan.jsp";
			}
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			view = "errorPage.jsp";
		}
		return view;
	}

	private String application(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	/* write a code to return to trackloan page */
		String view="";
		try {
			connDao.connect();
			request.setAttribute("appno",connDao.generateApplicationNumber());
			view="application.jsp";
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			view = "errorPage.jsp";
		}
		return view;
	}
}