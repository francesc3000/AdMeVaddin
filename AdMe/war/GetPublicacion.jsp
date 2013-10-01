
    
    
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page session="true" %>

<%@ page import="com.luremesoftware.adme.controlador.ControladorWeb" %>
<%@ page import="com.luremesoftware.adme.modelo.Usuario" %>
<%@ page import="com.luremesoftware.adme.modelo.lista.ListaPubli" %>
<%@ page import="com.luremesoftware.adme.modelo.lista.ListaMetadato" %>
   
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
        <title>Retorno Publicaciones</title>




    </head>
    <body>

        <h1>Retorno de publicaciones</h1>

           <%
            	ControladorWeb cw = new ControladorWeb();
           		ListaMetadato listaMetadato = new ListaMetadato();
           
           		cw.getListaPubli(listaMetadato);
        	%>
    </body>
</html>
   