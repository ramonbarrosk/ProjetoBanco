package Conexão;
import Entidades.Conta;
import Telas.Login;
import Telas.TelaPrincipal;
import conexão.DBConnection;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.*;
//Conexão da entidade Conta com o banco de dados
public class ContaDAO {
    //Adiciona os atributos da conta no banco de dados
    public static void adicionarBanco(Conta conta,int clienteId){
        Connection conexao = null;
        PreparedStatement smt = null;
        String sql = "INSERT INTO conta(conta,senha,saldo,cliente_id) VALUES(?,?,?,?)";
        try {
            conexao = DBConnection.openConnection();
            smt = conexao.prepareStatement(sql);
            smt.setInt(1,conta.getNumeroConta());
            smt.setInt(2,conta.getSenha());
            smt.setInt(3, conta.getSaldo());
            smt.setInt(4, clienteId);
            smt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro ao cadastrar conta"+ex.getMessage());
        }finally{
            DBConnection.closeConnection(conexao, smt);
        }
    }
    //Confere se os dados estão no banco de dados
    public static Conta validarConta(Conta conta){
        Connection conexao = null;
        Statement stm  = null;
        Conta cc = null;
        try {
        conexao = DBConnection.openConnection();
        stm = conexao.createStatement();
        ResultSet rs = stm.executeQuery( "SELECT * FROM conta;" );
        while ( rs.next() ) {
                int Nconta = rs.getInt("conta");
                int senha = rs.getInt("senha");
                int saldo = rs.getInt("saldo");
                int clienteId = rs.getInt("cliente_id");
                if (conta.getNumeroConta()==Nconta && conta.getSenha()==senha){
                    cc = new Conta(Nconta,senha);
                    cc.setSaldo(saldo);
                    cc.setId(clienteId);
                }
        }
        stm.close();
        rs.close();
        } catch ( Exception e ) {
        System .err.println( e.getClass().getName() + ": " + e.getMessage() );
        System .exit(0);
        }finally{
            DBConnection.closeConnection(conexao);
            return cc;
        }
    }   
    //Altera o saldo com base nas funções Saque e Depósito
    public static void updateSaldo(Conta conta){
        Connection conexao = null;
        Statement smt = null;
        String sql  = "UPDATE conta set saldo = '"+conta.getSaldo()+"' WHERE conta = '"+conta.getNumeroConta()+"';";
        try {
            conexao = DBConnection.openConnection();
            smt = conexao.createStatement();
            smt.executeUpdate(sql);
            Login.lconta.setSaldo(conta.getSaldo());
        } catch (SQLException ex) {
            Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //Retorna o valor do saldo no banco de dados da conta
    public static void consultaSaldo(Conta conta){
        Connection conexao = null;
        Statement smt = null;
        try {
            conexao = DBConnection.openConnection();
            smt = conexao.createStatement();
            ResultSet rs = smt.executeQuery("SELECT saldo FROM conta WHERE conta = '"+conta.getNumeroConta()+"';");
            while (rs.next()){
                conta.setSaldo(rs.getInt("saldo"));
            }
            smt.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Erro");
        }finally{
            DBConnection.closeConnection(conexao);
        }
    } 
    public static boolean excluirConta(int senha,int conta){
        Connection conexao = null;
        Statement smt = null;
        try{
            conexao = DBConnection.openConnection();
            smt =  conexao.createStatement();
            String sql = "DELETE FROM conta WHERE conta =  '"+conta+"' and senha='"+senha+"'";
            smt.executeUpdate(sql);
            smt.close();
            return true;
        }catch(SQLException ex){
            return false;
        }finally{
            DBConnection.closeConnection(conexao);
        }
    }
    public static Conta retornaConta(int numero){
        Connection conexao = null;
        PreparedStatement stm  = null;
        Conta cc = null;
        
        try {
        conexao = DBConnection.openConnection();
        stm = conexao.prepareStatement("SELECT conta,senha,saldo FROM conta WHERE conta=?;" );
        stm.setInt(1, numero);
        ResultSet rs = stm.executeQuery();
        int cont = 0;
        while ( rs.next() ) {
                int Nconta = rs.getInt("conta");
                int senha = rs.getInt("senha");
                int saldo = rs.getInt("saldo");
                cc = new Conta(Nconta,senha);
                cc.setSaldo(saldo);
        }
        stm.close();
        rs.close();
        } catch ( Exception e ) {
        System .err.println( e.getClass().getName() + ": " + e.getMessage() );
        System .exit(0);
        }finally{
            DBConnection.closeConnection(conexao);
             return cc;
        }
    }  
}
    
