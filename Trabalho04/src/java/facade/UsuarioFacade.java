
package facade;

import DAO.UsuarioDAO;
import interfaces.IBaseInterface;
import java.util.List;
import beans.UsuarioBean;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioFacade implements IBaseInterface<UsuarioBean> {
    
    UsuarioDAO dao = new UsuarioDAO();

    @Override
    public void inserir(UsuarioBean c) {
        try {
            dao.adicionarUsuario(c);
        } catch (Exception ex) {
            System.out.println("Ocorreu uma exceção aqui");
        }
    }

    @Override
    public void alterar(UsuarioBean c) {
        dao.atualizarUsuario(c);
    }

    @Override
    public void remover(int id) {
        dao.removerUsuario(id);
    }

    @Override
    public List<UsuarioBean> buscarTodos() {
        return dao.listarUsuario();
    }
    
    public boolean validarUsuario(String login, String senha){
        return dao.validarUsuario(login, senha);
    }
}
