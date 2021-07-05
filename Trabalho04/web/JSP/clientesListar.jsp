<%-- 
    Document   : clientesListar
    Created on : 08/06/2021, 21:03:40
    Author     : Gustavo
--%>

<%@page import="model.Cliente"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <%@include file="/JAVASCRIPT/ClienteJavaScript.js"%> --%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listar Clientes</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    </head>
    <body>
                
        <c:if test="${empty sessionScope.login}">
            <c:set var="mensagem" value="O login não foi realizado, por favor faça o login!!" scope="request" />
                <jsp:forward page="/index.jsp" />
        </c:if>
        
    <h1>Lista de Clientes</h1>
        <br>   
        <table class="table">
                <thead class="thead-dark">
                  <tr>
                    <th scope="col">CPF</th>
                    <th scope="col">Nome</th>
                    <th scope="col">Email</th>
                    <th scope="col">Ações</th>
                  </tr>
                </thead>    
            <c:forEach var="cliente" items="${Clientes}">
                <tr>
                    <td>${cliente.cpf_cliente}</td>
                    <td>${cliente.nome_cliente}</td>
                    <td>${cliente.email_cliente}</td>
                    <td>
                        <a 
                            class="btn btn-primary btn-custom" 
                            href="${pageContext.request.contextPath}/ClientesServlet?action=formUpdate&id=${cliente.id_cliente}" 
                            role="button"
                        >
                        Edit
                        </a>
                        <button 
                            class="btn btn-primary btn-custom" 
                            value="Delete"
                            onclick="confirmacao(${cliente.id_cliente})"
                        >
                        Del
                        </button>
                        <a 
                            class="btn btn-primary btn-custom" 
                            href="${pageContext.request.contextPath}/ClientesServlet?action=show&id=${cliente.id_cliente}" 
                            role="button"
                        >
                        Ver
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <a class="btn btn-primary" href="ClientesServlet?action=formNew" role="button">Cadastrar Cliente</a>
    
    <%-- JAVA SCRIPT --%>
    <script language="Javascript">
        function confirmacao(idExcluir){
            var ajax = new XMLHttpRequest();
            var resposta = confirm("Deseja remover esse registro?");
            if (resposta === true){            
                ajax.open(
                    "GET",
                    "${pageContext.request.contextPath}/ClientesServlet?action=remove&id="+idExcluir
                        );
                    ajax.send();            
                    ajax.addEventListener("readystatechange", function(){
                    if(ajax.readyState === 4 && ajax.status === 200){
                    Location.reload();
                    }
                });                     
             }
        };             
        
    </script>
    <%-- END JAVA SCRIPT --%>
        
    </body>
</html>
