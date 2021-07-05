<%-- 
    Document   : inserir
    Created on : 03/06/2021, 17:02:38
    Author     : Gustavo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inserir</title>
    </head>
    <body>
        <c:if test="${empty sessionScope.login}">
            <c:set var="mensagem" value="O login não foi realizado, por favor faça o login!!" scope="request" />
                <jsp:forward page="/index.jsp" />
        </c:if>
        <div>
            <h1>Tela de Cadastro</h1>
            <form action="${pageContext.request.contextPath}/CadastrarUsuarioServlet" method="Post">
                 <div class="form-group">
                   <label for="exampleInputEmail1">Name</label>
                   <input type="text" class="form-control" placeholder="Name" name="Name" >
                 </div>
                 <div class="form-group">
                   <label for="exampleInputEmail1">Login</label>
                   <input type="text" class="form-control" placeholder="Login" name="Login">
                 </div>
                 <div class="form-group">
                   <label for="exampleInputPassword1">Senha</label>
                   <input type="text" class="form-control" placeholder="Senha" name="Senha">
                 </div>
                <button type="submit" class="btn btn-primary">
                    Cadastrar
                </button>
                 <a class="btn btn-primary" href="${pageContext.request.contextPath}/ClientesServlet" role="button">Voltar</a>
           </form>
        </div>
    </body>
</html>
