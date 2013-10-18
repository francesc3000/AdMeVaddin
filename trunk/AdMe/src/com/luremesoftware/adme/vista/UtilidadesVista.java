package com.luremesoftware.adme.vista;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.luremesoftware.adme.constantes.Constante;

public class UtilidadesVista implements javax.servlet.Servlet, javax.servlet.jsp.HttpJspPage{
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;
	
	public UtilidadesVista( HttpServletRequest request, HttpServletResponse response, HttpSession session){
		this.request = request;
		this.response = response;
		this.session = session;
	}
	
	public String acceder() throws IOException{
		UserService userService = UserServiceFactory.getUserService();
		
        if(request.getUserPrincipal() != null) {
        	return request.getUserPrincipal().toString();
        	
        }else{
        	String thisURL = request.getRequestURI();
            this.response.sendRedirect(userService.createLoginURL(thisURL));
            User user =  userService.getCurrentUser();
            if(user.getEmail()!=null){
            	return user.getEmail();
            }else{
            	return null;
            }
        }
	}
	
	public boolean sendRedirect(String url) throws IOException{
		this.response.sendRedirect(url);
		return true;
	}
	
	public boolean setSessionAttribute(Constante constanteSession, Object object){
		this.session.setAttribute(constanteSession.toString(), object);
		return true;
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
