/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import DAO.UsuarioDAO;
import facade.UsuarioFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import beans.UsuarioBean;

/**
 *
 * @author Gustavo
 */
@WebServlet(name = "CadastrarUsuarioServlet", urlPatterns = {"/CadastrarUsuarioServlet"})
public class CadastrarUsuarioServlet extends HttpServlet {

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
        
        String mensagem =(String)request.getAttribute("msg");
        
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Situação</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> Aviso: " + mensagem + " </h1>");
            out.println("</body>");
            out.println("</html>");
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
        
        //Verificando o LOGIN
        
            //Variaveis
            String msg;
            
            //Variaveis para passar como parametro
            String mensagem = "Não foi possível realizar o cadastro, usuario não identificado na sessão!";
            String pagina = "/index.html";
            
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
                                
                try {
                //Acessando a Facade
                UsuarioFacade facade = new UsuarioFacade();
                
                //Criando um objeto do tipo Usuario
                UsuarioBean user = new UsuarioBean();
                user.setNome((String)request.getParameter("Name"));
                user.setLogin((String)request.getParameter("Login"));
                user.setSenha((String)request.getParameter("Senha")); 
                
                //Adicionando no banco
                facade.inserir(user);
                msg = URLEncoder.encode("Usuário foi cadastrado com sucesso!", "UTF-8");
                               
                } catch (Exception ex) {
                    System.err.println("Erro na inserção: " + ex);
                    msg = URLEncoder.encode("Não foi possível cadastrar o usuário!", "UTF-8");
                }
                
                //Redirecionando para CadastrarUsuarioServlet
                request.setAttribute("mensagem", msg);
                RequestDispatcher rd = request.getRequestDispatcher("/CadastrarUsuarioServlet/processRequest");
                rd.include(request, response);
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
