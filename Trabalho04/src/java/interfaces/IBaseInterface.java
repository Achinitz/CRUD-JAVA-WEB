package interfaces;

import java.util.List;
import model.Cliente;

public interface IBaseInterface<T>{
    
    void inserir(T c);
    void alterar(T c);
    void remover(int id);
    List<T> buscarTodos();
    
}
