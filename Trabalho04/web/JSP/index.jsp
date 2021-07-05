<%-- 
    Document   : index.jsp
    Created on : 08/06/2021, 14:46:04
    Author     : Gustavo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pagina Inicial</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    </head>
    <body>
        <% 
            Boolean deslogado = (Boolean)request.getAttribute("verificaDeslogado");
            String mensagemDeslogado = (String)request.getAttribute("mensagemDeslogado");
            if(deslogado == null || deslogado == false)
               { 
               deslogado = false;
               }
            else
               { 
                out.println("<div class=alert alert-danger role=alert>"
                        + "<b>" 
                        + mensagemDeslogado 
                        + "</b>"
                        + "</div>"
                        + "<br>");
               }
                        
            String email = "gustavoachinitz@gmail.com";
            Boolean validarErro = (Boolean)request.getAttribute("validarErro");
            if(validarErro == null)
               { 
                validarErro = false;
               }
            String mensagem =(String)request.getAttribute("msg");
            String pagina =(String)request.getAttribute("page");
        %>
        <div>
            <h1><b>Trabalho 2</b></h1>
            <form action="${pageContext.request.contextPath}/LoginServlet" method="Post">
                 <div class="form-group">
                   <label for="exampleInputEmail1">Login</label>
                   <input type="text" class="form-control" placeholder="Login" name="Login">
                 </div>
                 <div class="form-group">
                   <label for="exampleInputPassword1">Senha</label>
                   <input type="text" class="form-control" placeholder="Senha" name="Senha">
                 </div>
                 <button type="submit" class="btn btn-primary">Enviar</button>
           </form>
        </div>
        
        <div> 
            <% if(validarErro){ out.println( "<font color=red>" + mensagem + "</font>"); } %>
        </div>
        
        <div>
            <%
                out.println("<br>");
                out.println("<b>Em caso de problemas contactar o administrador: " + email + "</b>"); 
            %>
        </div>
    </body>
</html>
