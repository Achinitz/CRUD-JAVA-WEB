package beans;

import java.io.Serializable;
import java.sql.Date;

public class ClienteBean implements Serializable {

    /**
     * @return the cidade
     */
    public CidadeBean getCidade() {
        return cidade;
    }

    /**
     * @param cidade the cidade to set
     */
    public void setCidade(CidadeBean cidade) {
        this.cidade = cidade;
    }

    private int id_cliente;
    private String cpf_cliente;
    private String nome_cliente;
    private String email_cliente;
    private Date data_cliente;
    private String rua_cliente;
    private int nr_cliente;
    private String cep_cliente;
    private int cidade_cliente;
    private CidadeBean cidade;
    
    public ClienteBean(){ 
        
    }
    
    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getCpf_cliente() {
        return cpf_cliente;
    }

    public void setCpf_cliente(String cpf_cliente) {
        this.cpf_cliente = cpf_cliente;
    }

    public String getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    public String getEmail_cliente() {
        return email_cliente;
    }

    public void setEmail_cliente(String email_cliente) {
        this.email_cliente = email_cliente;
    }

    public Date getData_cliente() {
        return data_cliente;
    }

    public void setData_cliente(Date data_cliente) {
        this.data_cliente = data_cliente;
    }

    public String getRua_cliente() {
        return rua_cliente;
    }

    public void setRua_cliente(String rua_cliente) {
        this.rua_cliente = rua_cliente;
    }

    public int getNr_cliente() {
        return nr_cliente;
    }

    public void setNr_cliente(int nr_cliente) {
        this.nr_cliente = nr_cliente;
    }

    public String getCep_cliente() {
        return cep_cliente;
    }

    public void setCep_cliente(String cep_cliente) {
        this.cep_cliente = cep_cliente;
    }

    public int getCidade_cliente() {
        return cidade_cliente;
    }

    public void setCidade_cliente(int cidade_cliente) {
        this.cidade_cliente = cidade_cliente;
    }

}