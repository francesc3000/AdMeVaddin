<%-- 
    Document   : index
    Created on : 29/04/2013, 01:14:48
    Author     : cfgs
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*"%>

<%@ page import="com.luremesoftware.adme.controlador.ControladorWeb"%>
<%@ page import="com.luremesoftware.adme.modelo.Usuario"%>
<%@ page import="com.luremesoftware.adme.modelo.Publi"%>
<%@ page import="com.luremesoftware.adme.modelo.Metadato"%>
<%@ page import="com.google.appengine.api.datastore.Query.FilterOperator"%>
<%@ page import="com.luremesoftware.adme.constantes.Constante"%>
<%@ page import="com.luremesoftware.adme.modelo.lista.ListaMetadato"%>


<!DOCTYPE html>
<html>


<head>
<link rel="stylesheet" type="text/css"  href="../styles/estils.css">

<nav id="botonera">

	<menu>
		<a href="NuevaPublicacion.jsp">Nueva Publicación</a>
	</menu>
	<menu>
		<a href="NuevoGrupo.jsp">Nuevo Grupo</a>
	</menu>
	<menu>
		<a href="Puesto de Control.jsp">Puesto de Control</a>
	</menu>
	<menu>
		<a href="Perfil.jsp">Mi Perfil</a>
	</menu>
	</nav>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Búsqueda de publicaciones</title>


</head>
<body>

	<h1>BUSQUEDA DE PUBLICACIONES</h1>


	<%
		List<Publi> a = new ArrayList<Publi>();
		int ok = 1;
		if (!(request.getParameter("env") == null)) {

			ListaMetadato listaMetadato = new ListaMetadato();
			
			Metadato metadato =
						new Metadato(Constante.Tabla.PUBLICACION,
									 Constante.ConstantePubli.CIUDAD,
									 FilterOperator.EQUAL,
									 "Barcelona");
			
			listaMetadato.add(metadato);

			ControladorWeb cw = new ControladorWeb();

			a = cw.getListaPubli(listaMetadato);

			Iterator<Publi> itr = a.iterator();

			out.print("1");
			while (itr.hasNext()) {

				Publi p = itr.next();

				String d = p.getDescripcion();

				out.println(d);
				out.print("2");
			}
		}
	%>



	<form name="formulario" method="post" action="Busca.jsp?env=1">
		<h4>Busca:</h4>
		<input type="text" name="txtNom"
			value="<%if (!(request.getParameter("env") == null)) {
				out.print(request.getParameter("txtNom"));
			}%>">

		<br><br> <select name="ciudad">
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
		</select> <br><br> <input type="submit" value="Buscar">

	</form>

<br>

<div id="filtro">

<h3 align="left">FILTROS</h3>
<ul>
  <li><input type="checkbox" value="Barcelona"></input></li>
  <li><input type="checkbox" value="Sevilla"></input></li>
  <li><input type="checkbox" value="Tarragona"></input></li>
  <li><input type="checkbox" value="Madrid"></input></li>
 </ul>
<br>
  <input align="left" type="submit" value="Filtrar Busqueda"> 

</div>

</body>
</html>
