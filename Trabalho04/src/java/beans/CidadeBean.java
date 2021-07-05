
package beans;

import java.io.Serializable;
import java.util.List;

public class CidadeBean implements Serializable{
    
    private int id_cidade;
    private String nome_cidade;
    private int id_estado;
    private List<CidadeBean> listaDeCidades;
    
    public CidadeBean(){
    
    }
    
    public int getId_cidade() {
        return id_cidade;
    }

    public void setId_cidade(int id_cidade) {
        this.id_cidade = id_cidade;
    }

    public String getNome_cidade() {
        return nome_cidade;
    }

    public void setNome_cidade(String nome_cidade) {
        this.nome_cidade = nome_cidade;
    }

    public int getId_estado() {
        return id_estado;
    }

    public void setId_estado(int id_estado) {
        this.id_estado = id_estado;
    }
    
    public List<CidadeBean> getListaDeCidades() {
        return listaDeCidades;
    }

    public void addCidade(CidadeBean cidade) {
        listaDeCidades.add(cidade);
    }
    
}
