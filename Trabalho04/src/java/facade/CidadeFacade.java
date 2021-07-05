package facade;

import DAO.CidadeDAO;
import beans.CidadeBean;
import java.util.List;

public class CidadeFacade{

    //DAO
    CidadeDAO dao = new CidadeDAO();
    
    public List<CidadeBean> buscarTodos() {
        return dao.listarCidades();
    }
    
}
