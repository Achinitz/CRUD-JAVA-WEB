<%-- 
    Document   : portal
    Created on : 01/06/2021, 22:52:34
    Author     : Gustavo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Portal</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    </head>
    <body>        
        <c:if test="${empty sessionScope.login}">
            <c:set var="mensagem" value="O login não foi realizado, por favor faça o login!!" scope="request" />
                <jsp:forward page="/index.jsp" />
        </c:if>
        
        <jsp:useBean id="login" class="beans.LoginBean" scope="session"/>
          
          <h1> 
              <c:out value="Bem vindo ${login.login}"/>!!
          </h1>
        
        <div>
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/ClientesServlet?action=show" role="button">Cadastro de Clientes</a>
            <a class="btn btn-primary" href="index.html" role="button">Sair</a>
        </div>
    </body>
</html>
