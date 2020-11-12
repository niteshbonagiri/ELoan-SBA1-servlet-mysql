package com.iiht.evaluation.eloan.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AdminFilter
 */
@WebFilter({"/adminhome1.jsp","/listall.jsp","/process.jsp","/calemi.jsp"})
public class AdminFilter implements Filter {
	
	private ServletContext context;
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("In filter");
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse res=(HttpServletResponse) response;
		HttpSession session = ((HttpServletRequest) req).getSession();
		
		if(session.getAttribute("username")==null||session.getAttribute("username")!="admin"){
			//res.sendRedirect(req.getContextPath()+"/index.jsp");
			request.getRequestDispatcher("notfound.jsp").forward(request, response);
		}else {
			System.out.println(session.getAttribute("username"));
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.context=fConfig.getServletContext();
	}

}
