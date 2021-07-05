<%-- 
    Document   : clientesVisualizar
    Created on : 15/06/2021, 19:49:51
    Author     : Gustavo
--%>

<%@page import="java.sql.Date"%>
<%@page import="model.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Visualizar</title>
    </head>
    <body>

        <c:if test="${empty sessionScope.login}">
            <c:set var="mensagem" value="O login não foi realizado, por favor faça o login!!" scope="request" />
                <jsp:forward page="/index.jsp" />
        </c:if>
         
        <c:set var="cliente" value="${requestScope.Clientes}"/>
    
        <div>
            <h1>Visualizar dados do(a) usuario(a) ${cliente.nome_cliente}</h1>
            <form>
                 <div class="form-group">
                   <label for="exampleInputEmail1">Nome</label>
                   <input type="text" class="form-control" name="nome" value="${cliente.nome_cliente}" readonly="true">
                 </div>
                 <div class="form-group">
                   <label for="exampleInputEmail1">Email</label>
                  <input type="text" class="form-control" name="email" value="${cliente.email_cliente}" readonly="true">
                 </div>
                 <div class="form-group">
                   <label for="exampleInputEmail1">CPF</label>
                  <input type="text" class="form-control" name="cpf" value="${cliente.cpf_cliente}" readonly="true">
                 </div>
                 <div class="form-group">
                   <label for="exampleInputEmail1">Data</label>
                   <input id="date" class="form-control" type="date" name="data" value="${cliente.data_cliente}" readonly="true">
                 </div>
                 <div class="form-group">
                   <label for="exampleInputEmail1">Rua</label>
                   <input type="text" class="form-control" name="rua" value="${cliente.rua_cliente}" readonly="true">
                 </div>
                 <div class="form-group">
                   <label for="exampleInputEmail1">Número Telefone</label>
                   <input type="text" class="form-control" name="numero" value="${cliente.nr_cliente}" readonly="true">
                 </div>
                 <div class="form-group">
                   <label for="exampleInputEmail1">CEP</label>
                   <input type="text" class="form-control" name="cep" value="${cliente.cep_cliente}" readonly="true">
                 </div>
                 <div class="form-group">
                   <label for="exampleInputEmail1">Cidade</label>
                   <input type="text" class="form-control" name="cidade" value="${cliente.cidade_cliente}" readonly="true">
                 </div>
                 <div class="form-group">
                   <label for="exampleInputEmail1">UF</label>
                   <input type="text" class="form-control" name="uf" value="${cliente.uf_cliente}" readonly="true">
                 </div>
                  <a class="btn btn-primary" href="${pageContext.request.contextPath}/ClientesServlet?action=list" role="button">Voltar</a>
           </form>
        </div>
    </body>
</html>
