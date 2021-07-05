/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import DAO.ClienteDAO;
import facade.ClientesFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Date;
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
@WebServlet(name = "AlterarClienteServlet", urlPatterns = {"/AlterarClienteServlet"})
public class AlterarClienteServlet extends HttpServlet {

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
        
                   
        //Variaveis
        String msg;
            
        //Variaveis para passar como parametro
        String mensagem = "Não foi possível realizar o cadastro, usuario não identificado na sessão!";
        String pagina = "/index.html";
        
        try{
             
            //Verificando Sessão
            boolean verificaSessao = false;
                if(request.getSession(verificaSessao) == null)
                {                
                    //Redirecionamento via forward
                    RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
                    request.setAttribute("msg", mensagem);
                    request.setAttribute("page", pagina);
                    rd.forward(request, response);
                }

                //Acessando a FACADE
                ClientesFacade facade = new ClientesFacade();
                                
                //Criando um objeto do tipo Usuario
                ClienteBean cliente = new ClienteBean();
                cliente.setId_cliente(Integer.parseInt(request.getParameter("id")));
                cliente.setCpf_cliente(request.getParameter("cpf"));
                cliente.setNome_cliente(request.getParameter("nome"));
                cliente.setEmail_cliente(request.getParameter("email"));
                cliente.setData_cliente(Date.valueOf(request.getParameter("data")));
                cliente.setRua_cliente(request.getParameter("rua"));
                cliente.setNr_cliente(Integer.parseInt(request.getParameter("numero")));
                cliente.setCep_cliente(request.getParameter("cep"));
                cliente.setCidade_cliente(Integer.parseInt(request.getParameter("cidade")));
                                
                //Adicionando no banco
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
