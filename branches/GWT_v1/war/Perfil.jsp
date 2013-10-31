<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page
	import="com.luremesoftware.adme.constantes.Constante.ConstanteSession"%>
<%@ page import="com.luremesoftware.adme.modelo.Usuario"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../styles/Inicio.css">

<div align="right">

	<form name="acceder" method="post" action="Inicio.jsp?env=1">

		<button type='submit' class='login-button'>Salir</button>

	</form>
</div>

<nav id="botonera">

	<menu>
		<a href="NuevaPublicacion.jsp">Nueva Publicación</a>
	</menu>
	<menu>
		<a href="NuevoGrupo.jsp">Nuevo Grupo</a>
	</menu>
	<menu>
		<a href="Puesto de Control.jsp">Mi Perfil</a>
	</menu>
	<menu>
		<a href="Busca.jsp">Realizar busqueda</a>
	</menu>
	<!-- <input placeholder="Buscar... "type="search" name="buscar" id="buscar"/>
        <input type="submit" value="Buscar" id="submit" />   -->
</nav>


</head>
<body>
	<br>
	<br>
	<br>
	<h1>PERFIL DE USUARIO</h1>
	<%
		Usuario usuario = (Usuario) session
				.getAttribute(ConstanteSession.USUARIO.toString());
	%>

	<div id="contenedor1" align="left">
		<h2 align="left">Tus Datos</h2>
		<form name="datos" method="post" action="Perfil.jsp?env=1">
			<label for="nombre">Nombre:</label>&nbsp;&nbsp;&nbsp; <input
				placeholder="<%out.print(usuario.getNombre());%>" type="text"
				name="nombre"></input><br>
			<br> <label for="ap1">Apellido 1:</label> <input
				placeholder="<%out.print(usuario.getApellido1());%>" type="text"
				name="ap1"></input><br>
			<br> <label for="ap2">Apellido 2:</label> <input
				placeholder="<%out.print(usuario.getApellido1());%>" type="text"
				name="ap2"></input><br>
			<br> <label for="email">Email:</label>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
				placeholder="<%out.print(usuario.getCorreo());%>" type="text"
				name="email"></input><br>
			<br> <label for="telefono">Teléfono:</label> &nbsp; <input
				placeholder="<%out.print(usuario.getCorreo());%>" type="text"
				name="telefono"></input><br>
			<br>

			<button type='submit' class='login-button'>Guardar cambios</button>


		</form>

	</div>
	
	
	<div id="contenedor2">
		<h2>Avatar</h2>
		
		
		<table border="2" align="center">

			<tr>
				<td><img src="../img/user.png"></td>
			</tr>

		</table>
		<form name="envio" method="post" enctype="multipart/form-data"
			action="Perfil.jsp?env=2">
			<br> <label for="file">Seleccionar Archivo:</label> <input
				type=file size=60 name="file"><br>
			<br>
			<button type='submit' class='login-button'>Subir imagen</button>
		</form>
		<br>

		<h2>Video de Presentación</h2>
	</div>

</body>
</html>