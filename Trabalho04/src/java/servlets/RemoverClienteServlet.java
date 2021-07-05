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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gustavo
 */
@WebServlet(name = "RemoverClienteServlet", urlPatterns = {"/RemoverClienteServlet"})
public class RemoverClienteServlet extends HttpServlet {

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
        
        try (PrintWriter out = response.getWriter()) {
                        
            //Verificando Sessão
            boolean verificaSessao = false;
                if(request.getSession(verificaSessao) == null){
                
                //Redirecionamento via forward
                RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
                request.setAttribute("msg", mensagem);
                request.setAttribute("page", pagina);
                rd.forward(request, response);
            }
            
            //Pegar o ID
            int id = Integer.parseInt(request.getParameter("id"));            
            //Facade
            ClientesFacade facade = new ClientesFacade();            
            facade.remover(id);
            //Redirecionando
            RequestDispatcher rd = request.getRequestDispatcher("/ClientesServlet");
            rd.forward(request, response);
        }catch(Exception ex){
            //Tratando as exceções
            mensagem = URLEncoder.encode("Ocorreu um erro: "+ ex.getMessage() +" ", "UTF-8");
            RequestDispatcher rd = request.getRequestDispatcher("/ClientesServlet");
            request.setAttribute("mensagem",mensagem);
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
