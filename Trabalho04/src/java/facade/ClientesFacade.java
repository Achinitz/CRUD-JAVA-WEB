package facade;

import DAO.ClienteDAO;
import java.util.List;
import beans.ClienteBean;
import interfaces.IBaseInterface;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientesFacade implements IBaseInterface<ClienteBean>{
    
    //Acessando a DAO
    ClienteDAO dao = new ClienteDAO();
        
    @Override
    public void inserir(ClienteBean c) {
        try {
            dao.adicionarCliente(c);
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void alterar(ClienteBean c) {
        try {
            dao.atualizarCliente(c);
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public ClienteBean buscarPorId(int id) {
        return dao.buscarClienteId(id);
    }

    @Override
    public List<ClienteBean> buscarTodos() {
        return dao.listarCliente();
    }

    @Override
    public void remover(int id) {
        dao.removerCliente(id);
    }
    
}
