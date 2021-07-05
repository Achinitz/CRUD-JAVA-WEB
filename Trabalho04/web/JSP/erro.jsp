<%-- 
    Document   : erro
    Created on : 01/06/2021, 23:49:46
    Author     : Gustavo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
    String mensagem =(String)request.getAttribute("msg");
    String pagina =(String)request.getAttribute("page");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Portal</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    </head>
    <body>       
        <div>
            <a class="btn btn-primary" href="index.html" role="button">Tela de Login</a>
        </div>        
        <%
            out.println("Em caso de problemas contactar o administrador: " + mensagem + "</h1>");
        %>        
    </body>
</html>
