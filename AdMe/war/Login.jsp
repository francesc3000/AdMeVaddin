<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page session="true"%>
<%@ page import="com.luremesoftware.adme.controlador.ControladorWeb" %>
<%@ page import="com.luremesoftware.adme.controlador.vista.ControladorVista" %>
<%@ page import="com.luremesoftware.adme.modelo.Usuario" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>LOGIN</h1>



	<%
	
		//if (!(request.getParameter("env") == null)) {

			   String usuario = request.getParameter("user");
               String pass = request.getParameter("pass");
               
               
               ControladorWeb cw = new ControladorWeb();
               ControladorVista cv = new ControladorVista();
               
         		out.print(usuario);
               Usuario login= cv.acceder(request, response);
               
               if(login!=null){
            	   
            	   
            	   String correo= login.getCorreo();
            	   
            	   //session.setAttribute( "user", login );
                   //response.sendRedirect("Perfil.jsp");
               }
	
		//}
	%>



	<!-- <form name="formulario" method="post" action="Login.jsp?env=1">

		<h4>Usuario</h4>
		<input type="text" name="user">
		<h4>Password</h4>
		<input type="password" name="pass"> <br> <br> <input
			type="submit" value="Login">


	</form> -->


</body>
</html>
