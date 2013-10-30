
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ page import="com.luremesoftware.adme.controlador.ControladorWeb"%>
<%@ page import="com.luremesoftware.adme.constantes.Constante.ConstanteSession"%>
<%@ page import="com.luremesoftware.adme.modelo.Usuario"%>
<%@ page import="com.luremesoftware.adme.modelo.Grupo"%>
<html>
<head>

<link rel="stylesheet" type="text/css" href="estils.css">
<div style="color: green" align="left">
	<font size="7">AdMe!</font>
</div>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Nuevo Grupo here</title>
</head>
<body>
	<h1>CREAR NUEVO GRUPO</h1>
	<% Usuario usuario = (Usuario)session.getAttribute(ConstanteSession.USUARIO.toString()); %>
	
	<%

        int ok = 1;
        if (!(request.getParameter("env") == null)) {

            String nGrupo = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");
            String ciudad = request.getParameter("ciudad");
            
        

            if (nGrupo.equals("")) {
                out.print("Falta rellenar el campo titulo <br>");
                ok = 0;
            }
            
            if (descripcion.equals("")) {
                out.print("Falta rellenar el campo descripcion <br>");
                ok = 0;
            }

            if (ok == 1) {
               
            

				Grupo grupo = new Grupo(usuario,nGrupo,descripcion,ciudad);
				usuario.addGrupo(grupo);
            	
                ControladorWeb cw = new ControladorWeb();
                
                cw.putGrupo(grupo);
                cw.putUsuario(usuario);
            }
        }
    %>


	<form name="formulario" method="post" action="NuevoGrupo.jsp?env=1">

		Introduce el nombre del grupo:<br> <input type="text"
			maxlength="40" size="40" name="nombre"> <br></br> Selecciona
		ciudad:<br> <select name="ciudad">
			<option value="Barcelona">Barcelona
			<option value="Sevilla">Sevilla
			<option value="Tarragona">Tarragona
			<option value="Cuenca">Cuenca
			<option value="Zaragoza">Zaragoza
			<option value="Madrid">Madrid
		
		</select> <br>
		<br> Descripción:<br>
		<textarea name="descripcion" cols="40" rows="15"></textarea>
		<br><br>
		<input type="submit" value="Crear Grupo">
	</form>

</body>
</html>