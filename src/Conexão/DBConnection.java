package conexão;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
//Classe que faz conexão com o banco de dados
public class DBConnection {
	
    private static final String DRIVER = "org.sqlite.JDBC";
    private static final String URL = "jdbc:sqlite:projeto.db";
    //Método para abrir conexão com o banco
    public static Connection openConnection(){
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL);
           
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro na conexão!", ex);
        } 
    }
    //Método para fechar a conexão com o banco
    public static void closeConnection(Connection conn){
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    //Método que fecha conexão, preparedStatement
    public static void closeConnection(Connection conn, PreparedStatement stat){
        closeConnection(conn);
        if(stat != null){
            try {
                stat.close();
            } catch (SQLException ex) {
                Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    //Método que fecha conexão, preparedStatement e o ResultSet
    public static void closeConnection(Connection conn, PreparedStatement stat, ResultSet rs){
        closeConnection(conn,stat);
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
