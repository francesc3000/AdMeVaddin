<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ page import="com.luremesoftware.adme.controlador.ControladorWeb"%>
<%@ page import="com.luremesoftware.adme.modelo.Usuario"%>
<%@ page import="com.luremesoftware.adme.modelo.Publi"%>
<%@ page import="com.luremesoftware.adme.constantes.Constante.ConstanteSession" %>
<%@ page import="com.luremesoftware.adme.bbdd.ObjectifyRegister" %>


<html>
<head>

<%
	int log = 0;
	ObjectifyRegister().run();
%>

<meta charset="ISO-8859-1">
<title>AdMe!</title>


<%
	if (request.getParameter("env") != null) {

		if (request.getParameter("env").toString().equals("2")) {

			response.sendRedirect("Busca.jsp");
		}

		else {

			if (log == 1) {
				log = 0;
			} else {
				//out.print(request.getParameter("env").toString());
				ControladorWeb cw = new ControladorWeb();

				Usuario login = cw.acceder(request, response, session);
				if(login!=null){
					log = 1;
				}
			}
		}

	}
%>


<link rel="stylesheet" type="text/css" href="Inicio.css">

<div align="right">

	<form name="acceder" method="post" action="Inicio.jsp?env=1">
		<%
			if (log == 1) {
				Usuario usuario = (Usuario)session.getAttribute(ConstanteSession.USUARIO.toString());
				out.print("HOLA ");
				out.print(usuario.getNombre());
				out.print("<button type='submit' class='login-button'>Salir</button>");
			} else

				out.print("<button type='submit' class='login-button'>Acceder</button>");
		%>
	</form>

</div>
</head>
<body>

	<br>
	<br>
	<br>
	<br>

	<div id="menu">
		<img src="../img/adme.png"></img>
	</div>

	<div id="centro">

		<form name="acceder" method="post" action="Inicio.jsp?env=2">
			<input type="text" size="65"></input> <br> <br>

			<p>
				<label for="ciudad">Ciudad:</label> <select name="ciudad">
					<option value="Barcelona">Barcelona
					<option value="Sevilla">Sevilla
					<option value="Tarragona">Tarragona
					<option value="Cuenca">Cuenca
					<option value="Zaragoza">Zaragoza
					<option value="Madrid">Madrid
				</select> &nbsp; &nbsp; &nbsp; <input class="buscar" type="submit"
					value="Buscar"></input>

			</p>

		</form>

	</div>
	<div id="pie">By LuremeSoftware</div>
</body>
</html>
