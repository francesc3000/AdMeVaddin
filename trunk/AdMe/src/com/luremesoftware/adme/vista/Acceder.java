package com.luremesoftware.adme.vista;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class Acceder implements javax.servlet.Servlet, javax.servlet.jsp.HttpJspPage{
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public Acceder( HttpServletRequest request, HttpServletResponse response){
		this.request = request;
		this.response = response;
	}
	
	public String runAcceder() throws IOException{
		UserService userService = UserServiceFactory.getUserService();
		String thisURL = request.getRequestURI();
		
        if(request.getUserPrincipal() != null) {
        	return request.getUserPrincipal().toString();
        	
        }else{
            this.response.sendRedirect(userService.createLoginURL(thisURL));
            User user =  userService.getCurrentUser();
            if(user!=null){
            	return user.getEmail();
            }else{
            	return null;
            }
            
        }

	}

	@Override
	public void jspDestroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void jspInit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void _jspService(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init(ServletConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
