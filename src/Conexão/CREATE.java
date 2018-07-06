package Conexão;
import conexão.DBConnection;
import java.sql.*;
//Classe que cria as tabelas do banco de dados
public class CREATE {
    public static void main(String[] args) {
        Connection conexao = DBConnection.openConnection();
    try {
    Statement smt = conexao.createStatement();
    //Tabela Cliente
    String sqlCliente = "CREATE TABLE cliente " +
    "(id  INTEGER PRIMARY KEY AUTOINCREMENT," +
    " nome VARCHAR(30) NOT NULL UNIQUE, " +
    " idade INTEGER NOT NULL, " +
    " cpf VARCHAR(11)NOT NULL UNIQUE, " +
    " sexo VARCHAR(10)NOT NULL)";
    //TabelaConta
    String sqlConta = "CREATE TABLE conta "+
            "(id INTEGER PRIMARY KEY AUTOINCREMENT,"+
             "conta INTEGER NOT NULL, "+
            "senha INTEGER NOT NULL, "+
            "saldo INTEGER, "+
            "cliente_id INTEGER NOT NULL, "+
            "FOREIGN KEY (cliente_id)REFERENCES cliente(id)ON DELETE CASCADE )";
    String sqlTransferencia = "CREATE TABLE transacao "+
            "(id INTEGER PRIMARY KEY AUTOINCREMENT,"+
             "opcao VARCHAR(01) NOT NULL, "+
            "valor INTEGER  NOT NULL, "+
            "data TEXT NOT NULL, "+
            "hora TEXT NOT NULL, "+
            "descricao VARCHAR(15), "+
            "conta_principal INTEGER NOT NULL, "+
            "conta INTEGER NOT NULL)";
    smt.executeUpdate(sqlCliente);
    smt.executeUpdate(sqlConta);
    smt.executeUpdate(sqlTransferencia);
   } catch ( Exception e ) {
        System.out.println("Erro ao criar tabela"+e.getMessage());
    
    }finally{
        DBConnection.closeConnection(conexao);
    }
   }
}

