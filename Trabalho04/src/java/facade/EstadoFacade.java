
package facade;

import DAO.EstadoDAO;
import beans.EstadoBean;
import interfaces.IBaseInterface;
import java.util.List;

public class EstadoFacade implements IBaseInterface<EstadoBean>{

    EstadoDAO dao = new EstadoDAO();
    
    @Override
    public void inserir(EstadoBean c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void alterar(EstadoBean c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remover(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EstadoBean> buscarTodos() {
        return dao.listarEstados();
    }
    
}
