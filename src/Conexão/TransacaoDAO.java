
package Conexão;
import Entidades.Conta;
import java.sql.*;
import Entidades.Transacao;
import conexão.DBConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransacaoDAO {
    //Insere a transação no banco de dados da conta
    public static void inserirBanco(Transacao t,Conta c){
        Connection conexao = null;
        PreparedStatement smt = null;
        conexao = DBConnection.openConnection();
        String sql = "INSERT INTO transacao (opcao,valor,data,hora,descricao,conta_principal,conta) VALUES(?,?,?,?,?,?,?)";
        try {
            smt = conexao.prepareStatement(sql);
            smt.setString(1, t.getOpcao());
            smt.setInt(2,t.getValor());
            smt.setString(3, t.getData());
            smt.setString(4, t.getHora());
            smt.setString(5, t.getDescricao());
            smt.setInt(6, t.getConta());
            smt.setInt(7, c.getNumeroConta());
            smt.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("Erro"+ex.getMessage());
        }finally{
            DBConnection.closeConnection(conexao, smt);
        }
    }
    //Retorna o extrato
    public static void retornaTransacao(Conta conta,String inicio,String fim){
        Connection conexao = null;
        Statement stm  = null;
        
        try {
        conexao = DBConnection.openConnection();
        stm = conexao.createStatement();
        ResultSet rs = stm.executeQuery( "SELECT * FROM transacao WHERE conta_principal = '"+conta.getNumeroConta()+"' and  data BETWEEN '"+inicio+"' AND '"+fim+"';" );
        
        while ( rs.next() ) {
            String opcao = rs.getString("opcao");
            int valor = rs.getInt("valor");
            String data = rs.getString("data");
            String hora = rs.getString("hora");
            String descricao = rs.getString("descricao");
            
            int c = rs.getInt("conta");
            String extrato = opcao +"  Valor: R$"+ valor +"  Data:"+ data +"  Hora:"+ hora +"  "+ descricao+" Conta:"+c;
            conta.extrato.add(extrato);
                
        }
        stm.close();
        rs.close();
        } catch ( Exception e ) {
        System .err.println( e.getClass().getName() + ": " + e.getMessage() );
        System .exit(0);
        }finally{
            DBConnection.closeConnection(conexao);
            
        }
    }
    public static void excluirTrasacao(Conta conta){
        Connection conexao = null;
        Statement smt = null;
        try{
            conexao = DBConnection.openConnection();
            smt =  conexao.createStatement();
            String sql = "DELETE FROM transacao WHERE conta_principal =  '"+conta.getNumeroConta()+"'";
            smt.executeUpdate(sql);
            smt.close();
            
        }catch(SQLException ex){
            System.out.println("Erro ao excluir transação"+ex.getMessage());
        }finally{
            DBConnection.closeConnection(conexao);
        }
    }
}
