<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="com.luremesoftware.adme.controlador.ControladorWeb" %>
<%@ page import="com.luremesoftware.adme.modelo.Usuario" %>
<%@ page import="com.luremesoftware.adme.modelo.Publi" %>
<%@ page import="com.luremesoftware.adme.modelo.lista.ListaMensaje" %>
<%@ page import="com.luremesoftware.adme.constantes.Constante.ConstanteSession" %>
   
<!DOCTYPE html>
<html>
    <head>
<link rel="stylesheet" type="text/css" href="../styles/estils.css">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>




    </head>
    <body>

        <h1>Registro nuevo usuario</h1>

           <%
            int ok = 1;
            if (!(request.getParameter("env") == null)) {
            	
                String nom = request.getParameter("txtNombre");
                String apellido1 = request.getParameter("txtApellido1");
                String apellido2 = request.getParameter("txtApellido2");
                String email = (String) session.getAttribute(ConstanteSession.USUARIOMAIL.toString());
                String pass = request.getParameter("txtPass");
                String pass2 = request.getParameter("txtPass2");


                if (nom.equals("")) {
                    out.print("Falta rellenar el campo nombre <br>");
                    ok = 0;
                }

                if (apellido1.equals("") || apellido2.equals("")) {
                    out.print("Falta rellenar campo apellidos <br>");
                    ok = 0;
                }

                if (email.equals("")) {
                    out.print("Falta rellenar el campo email <br>");
                    ok = 1;
                }

                /*if (!(email.contains("@") && email.contains("."))) {
                    ok = 0;
                    out.print("Email no válido <br>");
                }
     
                if (pass.equals("")) {
                    out.print("Falta rellenar el campo password <br>");
                    ok = 0;
                }
                if (pass2.equals("")) {
                    out.print("Falta rellenar el campo confirma password <br>");
                    ok = 0;
                }

                if (!(pass.equals(pass2))) {

                    out.print("<p color= \"red\">Las contraseñas no son iguales</p> <br>");
                    ok = 0;
                }*/

                if (ok == 1) {
                    
                    Usuario usu = new Usuario(email,pass,nom,apellido1,apellido2);
     
                 
                    ControladorWeb cw = new ControladorWeb();
                    ListaMensaje listaMensaje = cw.putUsuario(usu);
                    if(!listaMensaje.contieneErrores()){
                    	session.setAttribute(ConstanteSession.USUARIO.toString(),usu);
                    }
                    response.sendRedirect("Perfil.jsp");
                  

                }
            }
        %>

    
        <form name="formulario" method="post" action="Registro.jsp?env=1">
            <h4>Nombre</h4>
            <input type="text" name="txtNombre" value="<%if (!(request.getParameter("env") == null)) {
                    out.print(request.getParameter("txtNombre"));
                }%>">
            <h4>Apellido 1</h4>
            <input type="text" name="txtApellido1" value="<%if (!(request.getParameter("env") == null)) {
                    out.print(request.getParameter("txtApellido1"));
                }%>">
            <h4>Apellido 2</h4>
            <input type="text" name="txtApellido2" value="<%if (!(request.getParameter("env") == null)) {
                    out.print(request.getParameter("txtApellido2"));
                }%>">
            <!-- <h4>Email</h4>
            <input type="text" name="txtEmail" value="<%if (!(request.getParameter("env") == null)) {
                    out.print(request.getParameter("txtEmail"));
                }%>">

            <h4>Password</h4>
            <input type="password" name="txtPass">
            <h4>Confirma Password</h4>
            <input type="password" name="txtPass2">
            <br>
            <br> -->
            <input type="submit" value="Enviar"> 

        </form> 


     
    </body>
</html>
   