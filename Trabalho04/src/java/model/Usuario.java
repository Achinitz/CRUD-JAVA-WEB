/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

public class Usuario {
     
        private Integer id;
        private String nome;
        private String login;
        private String senha;
        
        public Usuario()
        {
          
        }
        
        public Usuario(String nome, String login, String senha)
        {
            this.nome= nome;
            this.login= login;
            this.senha= senha;
            
        }
        
        public Usuario(Integer id, String nome, String login, String senha)
        {
            this.id = id;
            this.nome= nome;
            this.login= login;
            this.senha= senha;
        }
    
    public String getId() {
        return nome;
    }

    public void setId(String nome) {
        this.nome = nome;
    }
        
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
