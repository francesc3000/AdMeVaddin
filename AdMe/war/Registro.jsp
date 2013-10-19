<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="com.luremesoftware.adme.controlador.ControladorWeb" %>
<%@ page import="com.luremesoftware.adme.modelo.Usuario" %>
<%@ page import="com.luremesoftware.adme.modelo.Publi" %>
   
<!DOCTYPE html>
<html>
    <head>
        <style type="text/css">
            input, textarea {   
                padding: 9px;  
                border: solid 1px #E5E5E5;  
                outline: 0;  
                font: normal 13px/100% Verdana, Tahoma, sans-serif;  
                width: 200px;  
                background: #FFFFFF;  
            }  

            textarea {   
                width: 400px;  
                max-width: 400px;  
                height: 150px;  
                line-height: 150%;  
            }  

            input:hover, textarea:hover,  
            input:focus, textarea:focus {   
                border-color: #C9C9C9;   
            }  

            .form label {   
                margin-left: 10px;   
                color: #999999;   
            }  

            .submit input {  
                width: auto;  
                padding: 9px 15px;  
                background: #617798;  
                border: 0;  
                font-size: 12px;  
                color: #888;  
            }  
        </style>

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
                String email = (String) session.getAttribute("userMail");
                //String email = request.getParameter("txtEmail");
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
                    cw.putUsuario(usu);
                 
   
                    String name = request.getParameter( "txtNombre" );
                    session.setAttribute( "user", usu );
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
   