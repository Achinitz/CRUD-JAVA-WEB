
package DAO;

import beans.EstadoBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstadoDAO {
    private Connection con = null;
    
    public EstadoDAO() {
        con = ConnectionFactory.getConnection();
    }
    
    public List<EstadoBean> listarEstados(){
        
        String sql = "SELECT * FROM tb_estado";
        PreparedStatement stmt = null;
        //Objeto tipo lista
        List<EstadoBean> estados = new ArrayList();
        try{
            
        //Inserindo os dados
        stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
            
        //Percorrendo os dados no banco e preenchendo a lista.
        while(rs.next())
        {                
            EstadoBean estado = new EstadoBean();
            estado.setId_estado(rs.getInt("id_estado"));
            estado.setNome_estado(rs.getString("nome_estado"));
            estado.setSigla_estado(rs.getString("sigla_estado"));
            estados.add(estado);
        }
        return estados;
        }catch(SQLException ex){
            System.err.println("Erro na inserção: " + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        return estados;
    }
    
}
