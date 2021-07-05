package servlets;

import DAO.UsuarioDAO;
import beans.LoginBean;
import facade.UsuarioFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Usuario;

/**
 *
 * @author Gustavo
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
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

        UsuarioDAO dao = new UsuarioDAO();
        UsuarioFacade facade = new UsuarioFacade();
        
        //Recebendo os dados passados pelo post
        LoginBean user = new LoginBean();
        user.setLogin((String)request.getParameter("Login"));
        user.setSenha((String)request.getParameter("Senha"));
        
        //Mensagem de erro
        String msg = "Usuário/Senha inválidos.";
        String page = "/index.html";
        
        try{
            //Verificando se o Login é valido
            if(facade.validarUsuario(user.getLogin(), user.getSenha())){

                //Iniciando sessão
                HttpSession session = request.getSession();
                session.setAttribute("login", user);            

                //RequestDispatcher rd = request.getRequestDispatcher("/portal.jsp");
                response.sendRedirect("JSP/portal.jsp");            

            }else{

                //Enviando a mensagem de erro para o arquivo erro.jsp
                RequestDispatcher rd = request.getRequestDispatcher("JSP/index.jsp");
                request.setAttribute("msg", msg);
                request.setAttribute("page", page);
                request.setAttribute("validarErro", true);
                rd.forward(request, response);
            }
        }catch(Exception ex){
            
            //Tratando as exceções SQL
            msg = "Ocorreu um erro: "+ ex.getMessage() +" ";
            RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
            request.setAttribute("msg", msg);
            rd.forward(request, response);
        }
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
