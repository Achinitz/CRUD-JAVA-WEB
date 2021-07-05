package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import beans.UsuarioBean;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UsuarioDAO {

    private Connection con = null;
    
    public UsuarioDAO() {
        con = ConnectionFactory.getConnection();
    }
    
    public void adicionarUsuario(UsuarioBean usuario) throws Exception{
        
        String sql = "INSERT INTO tb_usuario(id_Usuario,login_Usuario,senha_Usuario,nome_Usuario) VALUES (?,?,?,?)";
        int id = verificarUltimoId() + 1;
        String senha = usuario.getSenha();
        con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try{
            //Validando se o login do usuário existe
            if(verificaUsuarioExiste(usuario.getLogin()) == true){
                throw new Exception("Já existe um usuário cadastrado com o nome ("+ usuario.getLogin() +") por favor informe um login diferente.");
            }
            //Chamando a função criptografada
            senha = criptografandoSenha(usuario.getSenha());
            
            //Inserindo os dados
            stmt = con.prepareStatement(sql);
            stmt.setInt(1,id);
            stmt.setString(2, usuario.getLogin());
            stmt.setString(3, senha);
            stmt.setString(4, usuario.getNome());
            
            //Executando a query
            stmt.executeUpdate();

        }catch(SQLException ex){
            System.err.println("Erro na inserção: " + ex);
            
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
        public void removerUsuario(int id ){
    
        String sql = "DELETE FROM tb_usuario WHERE id_cliente = ?";
                PreparedStatement stmt = null;
        try{
            //Inserindo os dados
            stmt = con.prepareStatement(sql);
            stmt.setInt(1,id);
            stmt.executeUpdate();
           
        }catch(SQLException ex){
            System.err.println("Erro na inserção: " + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
        
    }
    
    public void atualizarUsuario(UsuarioBean usuario ){
        String sql = "UPDATE tb_usuario SET "
                + "login_Usuario=?,senha_Usuario=?,nome_Usuario=?"
                + " WHERE id_Usuario=?";
        
               PreparedStatement stmt = null;
        try{
            //Inserindo os dados
            stmt = con.prepareStatement(sql);
            stmt.setString(1, usuario.getLogin());
            stmt.setString(2, usuario.getSenha());
            stmt.setString(3, usuario.getNome());
            stmt.setInt(4,usuario.getId());
            stmt.execute();

        }catch(SQLException ex){
            System.err.println("Erro na inserção: " + ex);

        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public List listarUsuario(){
        
        String sql = "SELECT * FROM tb_usuario";
        
        List<UsuarioBean> usuario = new ArrayList();
        
        PreparedStatement stmt = null;
        try{
            //Inserindo os dados
            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            //Percorrendo os dados no banco e preenchendo a lista.
            while(rs.next()){                
                UsuarioBean user = new UsuarioBean();
                user.setId(Integer.parseInt(rs.getString("id_Usuario")));
                user.setLogin(rs.getString("login_Usuario"));
                user.setNome(rs.getString("nome_Usuario"));
                user.setSenha(rs.getString("senha_Usuario"));
                
                usuario.add(user);
            }
            
            return usuario;
            
        }catch(SQLException ex){
            System.err.println("Erro na inserção: " + ex);

        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
        return usuario;
    }
    
    public boolean validarUsuario(String login, String senha){
        
        String sql = "SELECT * FROM tb_usuario where login_Usuario=? and senha_Usuario=?";
        boolean resultado = false;
        PreparedStatement stmt = null;
        try{
            //Inserindo os dados
            stmt = con.prepareStatement(sql);
            stmt.setString(1, login);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            
            //Verificando se existe algum valor
            resultado = rs.next() ? true: false;
            return resultado;
        }catch(SQLException ex){
            System.err.println("Erro na inserção: " + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
            return resultado;
        }
    }
    
    private int verificarUltimoId(){
        String sql = "SELECT * FROM tb_usuario";
        int contador = 0;
        PreparedStatement stmt = null;
        
        try{
            //Inserindo os dados
            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            //Percorrendo os dados no banco e preenchendo a lista.
            while(rs.next()){                
                contador++;
            }
        return contador;     
        }catch(SQLException ex){
            System.err.println("Erro na contagem: " + ex);

        }finally{
            ConnectionFactory.closeConnection(con, stmt);
            return contador;
        }
    }
    
    private boolean verificaUsuarioExiste(String login){
        
        String sql = "SELECT * FROM tb_usuario where login_Usuario=?";
        boolean resultado = false;
        PreparedStatement stmt = null;
        try{
            //Inserindo os dados
            stmt = con.prepareStatement(sql);
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();
            
            //Verificando se existe algum valor
            resultado = rs.next() ? true: false;
            return resultado;
        }catch(SQLException ex){
            System.err.println("Erro na inserção: " + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
            return resultado;
        }
    }
    
    private String criptografandoSenha(String senha) throws Exception{
            String senhaHex = "";
        
            try{
            //Criptografando a senha
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte messageDigest[] = md.digest(senha.getBytes("UTF-8"));
            
            //Usado para converter o array de bites para String
            StringBuilder sb = new StringBuilder();
            
            //"%02X" é uma mascara de formatação para que não tenha números negativos.
            for(byte b : messageDigest){
                sb.append(String.format("%02X", 0xFF & b));
            }
            
            //
            senhaHex = sb.toString();
            
            return senhaHex;
            }catch(Exception ex){
                System.out.println("Ocorreu um erro durante a criptografia: " + ex.getMessage());
            }
            return senhaHex;
    }
    
}
