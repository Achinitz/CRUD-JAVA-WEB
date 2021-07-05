package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionFactory {
    
    private static final String Driver = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost/exercicio3_JavaWeb?useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false";
    private static final String User = "root";
    private static final String Password = "1234";
    
    public static Connection getConnection(){        
        try
        {
            Class.forName(Driver);            
            return DriverManager.getConnection(URL, User, Password);
        }catch(ClassNotFoundException | SQLException ex)
        {
            throw new RuntimeException("Erro na conexão com o Banco de dados ",ex);
        }
    }
    
    public static void closeConnection(Connection con)
    {
        try{
        if(con != null){
            con.close();
        }}catch(SQLException ex)
        {
            System.err.println("Erro: " + ex);
        }
    }
    
    public static void closeConnection(Connection con, PreparedStatement stmt)
    {
        try{
        if(stmt != null){
            stmt.close();
        }}catch(SQLException ex){
            System.err.println("Erro: " + ex);
        }
        closeConnection(con);
    }
    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs)
    {
        try{
        if(stmt != null){
            rs.close();
        }}catch(SQLException ex){
            System.err.println("Erro: " + ex);
        }
        closeConnection(con,stmt);
    }
}
