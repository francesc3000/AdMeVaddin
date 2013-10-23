<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="com.luremesoftware.adme.controlador.ControladorWeb" %>
<%@ page import="com.luremesoftware.adme.constantes.Constante.ConstanteSession" %>
<%@ page import="com.luremesoftware.adme.modelo.Usuario" %>
<%@ page import="com.luremesoftware.adme.modelo.Publi" %>	
	

<html>
<head>

<link rel="stylesheet" type="text/css" href="estils.css">
<div style="color:green" align="left">
<font size="7">AdMe!</font>
</div>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Nueva Publicación here</title>
</head>
<body>

	<h1>NUEVA PUBLICACIÓN</h1>

	<%
		Usuario usuario = (Usuario)session.getAttribute(ConstanteSession.USUARIO.toString());
	
	
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
                   
                	Publi publi = new Publi(usuario,titulo,ciudad,mensaje);
                	
                    ControladorWeb cw = new ControladorWeb();
                         	         
                    cw.putPubli(usuario, publi);

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

  </textarea><br><br>
  	<input type="submit" value="Publicar"> 
	</form>

	<%
		//out.print(usuario);
	%>


</body>
</html>