<%-- 
    Document   : clientesAlterar
    Created on : 21/06/2021, 13:23:22
    Author     : Gustavo
--%>

<%@page import="java.sql.Date"%>
<%@page import="model.Cliente"%>
<%@page isELIgnored="false"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <title>${(empty cliente) ? "Novo Cliente": "Editar Cliente"} </title>
    </head>
    <body>
        
        <c:if test="${empty sessionScope.login}">
            <c:set var="mensagem" value="O login não foi realizado, por favor faça o login!!" scope="request" />
                <jsp:forward page="/index.jsp" />
        </c:if>
        
        <div>
            <h1>${(empty cliente) ? "Novo": "Editar"} 
                cadastro do(a) usuario(a) ${(empty cliente) ? "" : cliente.nome_cliente}
            </h1>
            <form action="${pageContext.request.contextPath}/${(empty cliente) ?"ClientesServlet?action=new":"ClientesServlet?action=update"} method="Post">
                <div class="form-group">
                   <label for="exampleInputEmail1">Nome</label>
                  <input 
                      type="text" 
                      class="form-control" 
                      placeholder="Name" 
                      name="nome" 
                      value="<c:out value="${cliente.nome_cliente}"/>" required="true">
                </div>
                <div class="form-group">
                   <label for="exampleInputEmail1">Email</label>
                  <input 
                      type="text"
                      class="form-control"
                      placeholder="Email"
                      name="email"
                      value="<c:out value="${cliente.email_cliente}"/>" required="true">
                </div>
                <div class="form-group">
                   <label for="exampleInputEmail1">CPF</label>
                  <input 
                      type="text" 
                      class="form-control" 
                      placeholder="CPF" 
                      name="cpf" 
                      value="<c:out value="${cliente.cpf_cliente}"/>" required="true">
                </div>
                <div class="form-group">
                   <label for="exampleInputEmail1" >Data</label>
                   <input 
                       id="date" 
                       type="date" 
                       class="form-control" 
                       name="data" 
                       value=" <c:out value="${cliente.data_cliente}"/>" required="true">
                </div>
                <div class="form-group">
                   <label for="exampleInputEmail1">Rua</label>
                   <input 
                       type="text" 
                       class="form-control" 
                       placeholder="Rua" 
                       name="rua" 
                       value="<c:out value="${cliente.rua_cliente}"/>" required="true">
                </div>
                <div class="form-group">
                   <label for="exampleInputEmail1">Número Telefone</label>
                   <input 
                       type="text" 
                       class="form-control" 
                       placeholder="Número" 
                       name="numero" 
                       value="<c:out value="${cliente.nr_cliente}"/>" required="true">
                </div>
                <div class="form-group">
                   <label for="exampleInputEmail1">CEP</label>
                   <input 
                       type="text" 
                       class="form-control" 
                       placeholder="CEP" 
                       name="cep" 
                       value="<c:out value="${cliente.cep_cliente}"/>" required="true">
                </div>
                <div class="form-group">
                   <label>Estado</label>
                   <select name="cidade" id="inputEstado" class="form-control">
                     <option selected="true">${(empty cliente) ? "Selecione..." : ""}</option>
                       <c:forEach var="cidade" items="${listaCidade}">
                           <option value="${cidade.id_cidade}">
                               <c:out value="${cidade.nome_cidade}" />
                           </option>
                       </c:forEach>    
                   </select>
                </div>
                <div class="form-group">
                    <label>Cidade</label>
                    <select name="cidade" id="inputCidade" class="form-control" readonly="">
                      <option selected="true">${(empty cliente) ? "Selecione..." : ""}</option>
                        <c:forEach var="cidade" items="${listaCidade}">
                            <option value="${cidade.id_cidade}">
                                <c:out value="${cidade.nome_cidade}" />
                            </option>
                        </c:forEach>    
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">
                    ${(empty cliente) ? "Salvar Cliente": "Finalizar Alteração"}
                </button>
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/ClientesServlet?action=show" role="button">
                    ${(empty cliente) ? "Voltar": "Cancelar Alteração"}
                </a>
           </form>
        </div>
                
    <%-- JAVA SCRIPT --%>
    <script language="Javascript" src="jquery-1.7.1.min.js">
        $('#inputEstado').change(function(){
            $("#inputEstado").attr('disabled', 'disabled');
        });         
    </script>
    <%-- END JAVA SCRIPT --%>
                
    </body>
</html>
