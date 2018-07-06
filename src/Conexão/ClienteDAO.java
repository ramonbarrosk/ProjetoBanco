package Conexão;
import java.sql.*;
import Entidades.Cliente;
import Entidades.Conta;
import conexão.DBConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

//Conexão da entidade Cliente com o banco de dados
public class ClienteDAO{
    //Metodo para adicionar o objeto cliente no banco de dados
    public static void adicionarBanco(Cliente cliente) {
        Connection conexao = null;
        PreparedStatement smt = null;
        String sql = "INSERT INTO cliente(nome,idade,cpf,sexo) VALUES(?,?,?,?)";
        try {
            conexao = DBConnection.openConnection();
            smt = conexao.prepareStatement(sql);
            smt.setString(1, cliente.getNome());
            smt.setInt(2,cliente.getIdade());
            smt.setString(3,cliente.getCpf());
            smt.setString(4,cliente.getSexo());
            smt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro ao cadastrar cliente"+ex.getMessage());
        }finally{
            DBConnection.closeConnection(conexao, smt);
        }
      }
    //Retorna o id do cliente com base na conta
    public static int retornaID(Cliente cliente){
        Connection conexao = null;
        Statement stm  = null;
        int id = 0;
        try {
        conexao = DBConnection.openConnection();
        stm = conexao.createStatement();
        ResultSet rs = stm.executeQuery( "SELECT id FROM cliente WHERE nome = '"+cliente.getNome()+"' ;" );
        while ( rs.next() ) {
                 int ide = rs.getInt("id");
                id = ide;
        }
        stm.close();
        rs.close();
        } catch ( Exception e ) {
        System .err.println( e.getClass().getName() + ": " + e.getMessage() );
        System .exit(0);
        }finally{
            DBConnection.closeConnection(conexao);
            return id;
        }
    }
    public static void excluirCliente(Conta conta){
        Connection conexao = null;
        Statement smt = null;
        try{
            conexao = DBConnection.openConnection();
            smt =  conexao.createStatement();
            String sql = "DELETE FROM cliente WHERE id =  '"+conta.getId()+"'";
            smt.executeUpdate(sql);
            smt.close();
            
        }catch(SQLException ex){
            System.out.println("Erro ao excluir cliente"+ex.getMessage());
        }finally{
            DBConnection.closeConnection(conexao);
        }
    }
}
