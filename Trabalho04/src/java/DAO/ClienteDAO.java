package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static jdk.nashorn.internal.objects.NativeString.toUpperCase;
import beans.ClienteBean;

public class ClienteDAO {

    private Connection con = null;
    
    public ClienteDAO() {
        con = ConnectionFactory.getConnection();
    }
    
    public void adicionarCliente(ClienteBean cliente) throws Exception{
        
        String sql = "INSERT INTO tb_cliente(id_cliente,cpf_cliente,nome_cliente,email_cliente,data_cliente,rua_cliente,nr_cliente,cep_cliente,cidade_cliente) VALUES (?,?,?,?,?,?,?,?,?)";
        int id = verificarUltimoId() + 1;
        con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try{
            //Validando se o EMAIL já existe
            if(verificaEmailExistente(cliente.getEmail_cliente()) == true){
                throw new Exception("Já existe um e-mail igual ao: (" + cliente.getEmail_cliente()+ ") cadastrado em nosso sistema. Por favor informe outro.");
            }
            //Validando se o CPF já existe
            if(verificaCPFExistente(cliente.getCpf_cliente()) == true){
                throw new Exception("Já existe um CPF igual ao: (" + cliente.getCpf_cliente()+ ") cadastrado em nosso sistema. Por favor informe outro.");
            }
            
            //Inserindo os dados
            stmt = con.prepareStatement(sql);
            stmt.setInt(1,id);
            stmt.setString(2,cliente.getCpf_cliente());
            stmt.setString(3,cliente.getNome_cliente());
            stmt.setString(4,cliente.getEmail_cliente());
            stmt.setDate(5,(Date)cliente.getData_cliente());
            stmt.setString(6,cliente.getRua_cliente());
            stmt.setInt(7,cliente.getNr_cliente());
            stmt.setString(8,cliente.getCep_cliente());
            stmt.setInt(9,cliente.getCidade_cliente());
            //Executando a query
            stmt.executeUpdate();

        }catch(SQLException ex){
            System.err.println("Erro na inserção, pois: " + ex);

        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void removerCliente(int id ){
    
        String sql = "DELETE FROM tb_cliente WHERE id_cliente = ?";
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
    
    public void atualizarCliente(ClienteBean cliente ) throws Exception{
        String sql = "UPDATE tb_cliente SET "
                + "cpf_cliente=?, nome_cliente=?, email_cliente=?, data_cliente=?, rua_cliente=?, nr_cliente=?"
                + ", cep_cliente=?, cidade_cliente=?"
                + " WHERE id_cliente=?";
        
               PreparedStatement stmt = null;
        try{
            
            //Validando se o EMAIL já existe
            if(verificaEmailExistente(cliente.getEmail_cliente()) == true){
                throw new Exception("Já existe um e-mail igual ao: (" + cliente.getEmail_cliente()+ ") cadastrado em nosso sistema. Por favor informe outro.");
            }
            //Validando se o CPF já existe
            if(verificaCPFExistente(cliente.getCpf_cliente()) == true){
                throw new Exception("Já existe um CPF igual ao: (" + cliente.getCpf_cliente()+ ") cadastrado em nosso sistema. Por favor informe outro.");
            }
            
            //Inserindo os dados
            stmt = con.prepareStatement(sql);
            stmt.setString(1,cliente.getCpf_cliente());
            stmt.setString(2,cliente.getNome_cliente());
            stmt.setString(3,cliente.getEmail_cliente());
            stmt.setDate(4,(Date)cliente.getData_cliente());
            stmt.setString(5,cliente.getRua_cliente());
            stmt.setInt(6,cliente.getNr_cliente());
            stmt.setString(7,cliente.getCep_cliente());
            stmt.setInt(8,cliente.getCidade_cliente());
            stmt.setInt(9,cliente.getId_cliente());
            stmt.execute();

        }catch(SQLException ex){
            System.err.println("Erro na inserção, pois: " + ex);

        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public ClienteBean buscarClienteId(int id){
    
        ClienteBean cliente = new ClienteBean();
        String sql = "SELECT "
                + "id_cliente, cpf_cliente, nome_cliente, email_cliente, data_cliente, rua_cliente, nr_cliente"
                + ", cep_cliente, cep_cliente, cidade_cliente"
                + " FROM tb_cliente WHERE id_cliente=?";
        PreparedStatement stmt = null;
        try{
            //Inserindo os dados
            stmt = con.prepareStatement(sql);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            //Percorrendo os dados no banco e preenchendo a lista.
            while(rs.next()){                
                cliente.setId_cliente(Integer.parseInt(rs.getString("id_cliente")));
                cliente.setCpf_cliente(rs.getString("cpf_cliente"));
                cliente.setNome_cliente(rs.getString("nome_cliente"));
                cliente.setEmail_cliente(rs.getString("email_cliente"));
                cliente.setData_cliente(rs.getDate("data_cliente"));
                cliente.setRua_cliente(rs.getString("rua_cliente"));
                cliente.setNr_cliente(rs.getInt("nr_cliente"));
                cliente.setCep_cliente(rs.getNString("cep_cliente"));
                cliente.setCidade_cliente(rs.getInt("cidade_cliente"));
            }
            return cliente;
        }catch(SQLException ex){
            System.err.println("Erro na inserção: " + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
        
        return cliente;
    }
    
    public List<ClienteBean> listarCliente(){
        
        String sql = "SELECT * FROM tb_cliente";
        PreparedStatement stmt = null;
        //Objeto tipo lista
        List<ClienteBean> cliente = new ArrayList();
        try{
            
            //Inserindo os dados
            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            //Percorrendo os dados no banco e preenchendo a lista.
            while(rs.next()){                
                ClienteBean client = new ClienteBean();                
                client.setId_cliente(rs.getInt("id_cliente"));
                client.setCpf_cliente(rs.getString("cpf_cliente"));
                client.setNome_cliente(rs.getString("nome_cliente"));
                client.setEmail_cliente(rs.getString("email_cliente"));
                client.setData_cliente(rs.getDate("data_cliente"));
                client.setRua_cliente(rs.getString("rua_cliente"));
                client.setNr_cliente(rs.getInt("nr_cliente"));
                client.setCep_cliente(rs.getString("cep_cliente"));
                client.setCidade_cliente(rs.getInt("id_cliente"));
                cliente.add(client);
            }
            return cliente;
        }catch(SQLException ex){
            System.err.println("Erro na inserção: " + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        return cliente;
    }
    
    //Métodos de validação da DAO
    
    private int verificarUltimoId(){
        String sql = "SELECT * FROM tb_cliente";
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
    
    private boolean verificaEmailExistente(String email){
        String sql = "SELECT * FROM tb_cliente WHERE email_cliente=?";
        boolean verificado = false;
        PreparedStatement stmt = null;
        
        try{
        //Inserindo os dados
        stmt = con.prepareStatement(sql);
        stmt.setString(1,email);
        ResultSet rs = stmt.executeQuery();
            
        //Percorrendo os dados no banco e preenchendo a lista.
           while(rs.next()){                
                verificado = true;
            }
        return verificado;     
        }catch(SQLException ex){
            System.err.println("Erro na validação de email existente, pois: " + ex);

        }finally{
            ConnectionFactory.closeConnection(con, stmt);
            return verificado;
        }
    }
    
    private boolean verificaCPFExistente(String cpf){
        String sql = "SELECT * FROM tb_cliente WHERE cpf_cliente=?";
        boolean verificado = false;
        PreparedStatement stmt = null;
        
        try{
        //Inserindo os dados
        stmt = con.prepareStatement(sql);
        stmt.setString(1,cpf);
        ResultSet rs = stmt.executeQuery();
            
        //Percorrendo os dados no banco e preenchendo a lista.
        while(rs.next()){                
                verificado = true;
            }
        return verificado;     
        }catch(SQLException ex){
            System.err.println("Erro na validação de CPF existente, pois: " + ex);

        }finally{
            ConnectionFactory.closeConnection(con, stmt);
            return verificado;
        }
    }
    
}
