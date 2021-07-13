package DAO;

import beans.CidadeBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CidadeDAO {
    private Connection con = null;
    
    public CidadeDAO() {
        con = ConnectionFactory.getConnection();
    }
    
    public List<CidadeBean> buscarCidadesPorEstado(int idEstado){
        String sql = "SELECT * FROM tb_cidade WHERE id_estado=?";
        PreparedStatement stmt = null;
        //Objeto tipo lista
        List<CidadeBean> cidades = new ArrayList();
        try{
            
            //Inserindo os dados
            stmt = con.prepareStatement(sql);
            stmt.setInt(1,idEstado);
            ResultSet rs = stmt.executeQuery();
            
            //Percorrendo os dados no banco e preenchendo a lista.
            while(rs.next()){                
                CidadeBean cidade = new CidadeBean();                
                cidade.setId_cidade(rs.getInt("id_cidade"));
                cidade.setNome_cidade(rs.getString("nome_cidade"));
                cidade.setId_cidade(rs.getInt("id_estado"));
                cidades.add(cidade);
            }
        
        return cidades;
        }catch(Exception ex){
        
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
        return cidades;
    }
    
    public List<CidadeBean> listarCidades(){
        
        String sql = "SELECT * FROM tb_cidade";
        PreparedStatement stmt = null;
        //Objeto tipo lista
        List<CidadeBean> cidades = new ArrayList();
        try{
            
            //Inserindo os dados
            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            //Percorrendo os dados no banco e preenchendo a lista.
            while(rs.next()){                
                CidadeBean cidade = new CidadeBean();                
                cidade.setId_cidade(rs.getInt("id_cidade"));
                cidade.setNome_cidade(rs.getString("nome_cidade"));
                cidade.setId_cidade(rs.getInt("id_estado"));
                cidades.add(cidade);
            }
            return cidades;
        }catch(SQLException ex){
            System.err.println("Erro na inserção: " + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        return cidades;
    }
}
