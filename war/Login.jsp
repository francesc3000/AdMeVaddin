<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="com.luremesoftware.adme.controlador.ControladorWeb" %>
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
               
         		
               Usuario login= cw.acceder(request, response);
               
               if(login!=null){
            	   if(login.getNombre()==null){
            		   session.setAttribute( "userMail", login.getCorreo());
                       response.sendRedirect("Registro.jsp");
            	   }else{
            	   	session.setAttribute( "user", login );
                   response.sendRedirect("Perfil.jsp");
            	   }
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
