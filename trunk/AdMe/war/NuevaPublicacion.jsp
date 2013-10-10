<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ page session="true" %>

<%@ page import="com.luremesoftware.adme.controlador.ControladorWeb" %>
<%@ page import="com.luremesoftware.adme.modelo.Usuario" %>
<%@ page import="com.luremesoftware.adme.modelo.Publi" %>	
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h1>NUEVA PUBLICACIÓN</h1>

	<%
		Usuario usuario = (Usuario)session.getAttribute("user");
	
            int ok = 1;
            if (!(request.getParameter("env") == null)) {

                String titulo = request.getParameter("titulo");
                String mensaje = request.getParameter("mensaje");
                String ciudad = request.getParameter("ciudad");
                
            

                if (titulo.equals("")) {
                    out.print("Falta rellenar el campo titulo <br>");
                    ok = 0;
                }

                
                if (mensaje.equals("")) {
                    out.print("Falta rellenar el campo mensaje <br>");
                    ok = 0;
                }

                if (ok == 1) {
                   
                //Usuario usu = new Usuario(usuario);	
                
   
				Publi publi = new Publi(usuario,titulo,ciudad,mensaje);
                	
                    ControladorWeb cw = new ControladorWeb();
                         	         
                    cw.putPubli(publi);

                }
            }
        %>
	
	

	<form name="formulario" method="post"
		action="NuevaPublicacion.jsp?env=1">

		Introduce el titulo:<br> <input type="text" maxlength="40"
			size="40" name="titulo"> <br></br> 
			Selecciona ciudad:<br>
		<select name="ciudad">
			<option value="Barcelona">Barcelona
			<option value="Sevilla">Sevilla
			<option value="Tarragona">Tarragona
			<option value="Barcelona">Barcelona
			<option value="Sevilla">Sevilla
			<option value="Tarragona">Tarragona
			<option value="Barcelona">Barcelona
			<option value="Sevilla">Sevilla
			<option value="Tarragona">Tarragona
			<option value="Barcelona">Barcelona
			<option value="Sevilla">Sevilla
			<option value="Tarragona">Tarragona
			<option value="Barcelona">Barcelona
			<option value="Sevilla">Sevilla
			<option value="Tarragona">Tarragona
			
			
		</select>
		 <br><br>
		Introduce tu anuncio:<br>
		<textarea name="mensaje" cols="40" rows="15">

  </textarea>
  	<input type="submit" value="Enviar"> 
	</form>

	<%
		//out.print(usuario);
	%>


</body>
</html>