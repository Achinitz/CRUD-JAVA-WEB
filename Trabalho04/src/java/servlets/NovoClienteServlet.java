/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import facade.ClientesFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import beans.ClienteBean;

/**
 *
 * @author Gustavo
 */
@WebServlet(name = "NovoCliente", urlPatterns = {"/NovoCliente"})
public class NovoClienteServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");

            //Variaveis para passar como parametro
            String mensagem = "Não foi possível realizar o login, por gentileza verifique os dados inseridos.";
            String pagina = "/index.jsp";
            String msg;

        try{
            
            //Verificando Sessão
            boolean verificaSessao = false;
                if(request.getSession(verificaSessao) == null){
                
                //Redirecionamento via forward
                RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
                request.setAttribute("msg", mensagem);
                request.setAttribute("page", pagina);
                rd.forward(request, response);
            }
                
            //Facade
            ClientesFacade facade = new ClientesFacade();
            //Instanciando o objeto para adicionar no banco de dados
            ClienteBean cliente = new ClienteBean();
            //Convertendo do tipo String para o tipo Date
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            //Adicionando os valores das variaveis nos parametros                
            cliente.setData_cliente((Date)formatter.parse(request.getParameter("data")));

            
            cliente.setNome_cliente((String)request.getParameter("nome"));
            cliente.setEmail_cliente((String)request.getParameter("email"));
            cliente.setCpf_cliente((String)request.getParameter("cpf"));
            cliente.setCep_cliente((String)request.getParameter("cep"));
            cliente.setNr_cliente(Integer.parseInt(request.getParameter("numero")));
            cliente.setRua_cliente((String)request.getParameter("rua"));
            cliente.setCidade_cliente(Integer.parseInt(request.getParameter("cidade")));
                
            //Verificando se vai retornar algum erro quando foi adicionado            
            facade.inserir(cliente);
            RequestDispatcher rd = request.getRequestDispatcher("/ClientesServlet");
            rd.forward(request, response);
                
        }catch(Exception ex){
            
            //Tratando as exceções
            msg = URLEncoder.encode("Ocorreu um erro: "+ ex.getMessage() +" ", "UTF-8");
            RequestDispatcher rd = request.getRequestDispatcher("/ClientesServlet");
            request.setAttribute("mensagem",msg);
            rd.forward(request, response);
       }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
