/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import DAO.ClienteDAO;
import beans.ClienteBean;
import facade.CidadeFacade;
import facade.ClientesFacade;
import facade.EstadoFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;

/**
 *
 * @author Gustavo
 */
@WebServlet(name = "ClientesServlet", urlPatterns = {"/ClientesServlet"})
public class ClientesServlet extends HttpServlet {

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
            
            //Criando Objeto para acessar métodos do banco de dados
            ClientesFacade facadeCliente = new ClientesFacade();            
            CidadeFacade facadeCidade = new CidadeFacade();            
            EstadoFacade facadeEstado = new EstadoFacade();
            
            //Criando um objeto tipo ClienteBean
            ClienteBean cliente = new ClienteBean();
            
            //Lista de Clientes
            List<ClienteBean> listaClientes = new ArrayList<ClienteBean>();
            
            //Dispatcher para redirecionar ou passar valores
            RequestDispatcher rd;
            
            //Variaveis auxiliares
            int id = 0;
                        
            switch(request.getParameter("action")){
            
                case "show":
                    listaClientes = facadeCliente.buscarTodos();
                    
                    //Retorna uma lista com todos os clientes que tem no banco de dados
                    rd = request.getRequestDispatcher("/JSP/clientesListar.jsp");
                    request.setAttribute("Clientes", listaClientes);
                    request.setAttribute("Estados", facadeEstado.buscarTodos());
                    rd.forward(request, response);
                break;                
                case "formUpdate":
                    //Pegar o ID
                    id = Integer.parseInt(request.getParameter("id"));
                    //Buscando o cliente
                    cliente = facadeCliente.buscarPorId(id);
                    //Encaminhando para a JSP
                    rd = request.getRequestDispatcher("/JSP/clientesForm.jsp");
                    request.setAttribute("cliente", cliente);
                    rd.forward(request, response);                                   
                break;                
                case "remove": 
                    //Pegar o ID
                    id = Integer.parseInt(request.getParameter("id"));            
                    //Método remover
                    facadeCliente.remover(id);
                    //Redirecionando
                    rd = request.getRequestDispatcher("/JSP/clientesListar.jsp");
                    rd.forward(request, response);
                break;
                case "update":
                    //Atribuindo valores ao objeto
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
                    facadeCliente.inserir(cliente);
                    //Redirecionar para o cliente
                    rd = request.getRequestDispatcher("/ClientesServlet");
                    rd.forward(request, response);  
                    break;
                case "formNew": 
                    //Retorna uma lista com todos os clientes que tem no banco de dados
                    rd = request.getRequestDispatcher("/JSP/clientesForm.jsp");
                    request.setAttribute("listaCidade", facadeCidade.buscarTodos());
                    request.setAttribute("listaEstado", facadeEstado.buscarTodos());
                    rd.forward(request, response);
                    break;
                case "new": 

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
                    cliente.setCidade_cliente(Integer.parseInt(request.getParameter("uf")));

                    //Verificando se vai retornar algum erro quando foi adicionado            
                    facadeCliente.alterar(cliente);
                    rd = request.getRequestDispatcher("/ClientesServlet?action=show");
                    rd.forward(request, response);
                    
                    break;                    
            }
           
        }catch(Exception ex){
            
            mensagem = "Ocorreu um erro: "+ ex.getMessage() +" ";
            RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
            request.setAttribute("msg", mensagem);
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
                    //Variaveis para passar como parametro
            String mensagem = "Não foi possível realizar o login, por gentileza verifique os dados inseridos.";
            String pagina = "/index.jsp";
            
            //Verificando Sessão
            boolean verificaSessao = false;
                if(request.getSession(verificaSessao) == null){
                
                //Redirecionamento via forward
                RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
                request.setAttribute("msg", mensagem);
                request.setAttribute("page", pagina);
                rd.forward(request, response);
            }
            
            /* TODO output your page here. You may use following sample code. */
            ClienteDAO banco = new ClienteDAO();          
                        
            RequestDispatcher rd = request.getRequestDispatcher("/JSP/clientesListar.jsp");
            request.setAttribute("Clientes", banco.listarCliente());
            rd.forward(request, response);
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
