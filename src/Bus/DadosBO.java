package Bus;
import Conexão.ClienteDAO;
import Conexão.ContaDAO;
import Entidades.Cliente;
import Entidades.Conta;
import conexão.DBConnection;
import java.sql.SQLException;
import java.sql.*;
//Classe que valida os dados 
public class DadosBO {
    //Verifica se algum dado está no banco de dados
    public static boolean verifica(String info,String coluna,String tabela) throws SQLException{
        Connection conexao = null;
        Statement smt = null;
        boolean verifica = false;
        conexao = DBConnection.openConnection();
        smt = conexao.createStatement();
        ResultSet rs = smt.executeQuery("SELECT * FROM "+tabela+" WHERE "+coluna+" ='"+info+"' ");
        while (rs.next()){
            verifica = true;
        }
        return verifica;
    }
    //Apresenta um erro caso os campos de números int estejam vázios
    public static void validandoNumerosCadastro(String idade, String conta,String senha)throws RuntimeException{
        if (idade.isEmpty()){
            throw new RuntimeException ("Idade é obrigatória");
        }
        else if (conta.isEmpty()){
            throw new RuntimeException ("Conta é obrigatória");
        }
        else if(senha.isEmpty()){
            throw new RuntimeException ("Senha é obrigatória");
        }
    }
    //Apresenta um erro caso o campo do saque esteja vázio
    public static void validandoSaque(String valor){
        if (valor.isEmpty()){
            throw new RuntimeException ("Insira um valor");
        }
    }
    //Apresenta um erro caso o campo da conta e o valor esteja vázio
    public static void validandoDeposito(String conta,String valor){
        if (conta.isEmpty()){
            throw new RuntimeException ("Conta é obrigatória");
        }
        else if(valor.isEmpty()){
            throw new RuntimeException ("Insira um valor");
        }
    }
    //Apresenta um erro caso a conta e/ou senha esteja vázia
    
    public static void validandoNumerosLogin(String conta, String senha)throws RuntimeException{
        
       if (conta.isEmpty()){
            throw new RuntimeException ("Conta é obrigatória");
        }
        else if(senha.isEmpty()){
            throw new RuntimeException ("Senha é obrigatória");
        }
    }
    //Valida os dados do cliente
    public static void validarCliente(Cliente cliente)throws RuntimeException, SQLException{
        boolean verificaCPF = verifica(cliente.getCpf(),"cpf","cliente");
        boolean verificaNome = verifica(cliente.getNome(),"nome","cliente");
        if (cliente.getIdade()<16 || cliente.getIdade()>100){
            throw new RuntimeException("Idade inválida");
        }
        else if (cliente.getCpf().length()!=11){
            throw new RuntimeException ("CPF inválido");
        }
        else if (cliente.getCpf().isEmpty()){
            throw new RuntimeException ("CPF obrigatório");
        }
        else if (cliente.getSexo()=="Selecione"){
            throw new RuntimeException ("Por favor selecione um sexo");
        }
        else if (verificaCPF==true){
           throw new RuntimeException ("CPF já cadastrado");
       }
        else if (verificaNome==true){
            throw new RuntimeException ("Nome já cadastrado");
       }
   }
    //Valida os dados  da conta
    public static void validarConta(Conta conta)throws RuntimeException, SQLException{
        String nConta = Integer.toString(conta.getNumeroConta());
        String nSenha = Integer.toString(conta.getSenha());
        boolean verificaConta = verifica(nConta,"conta","conta");
        
        if (nConta.isEmpty()){
            throw new RuntimeException("Conta obrigatória");
        }
         else if (nConta.length()!=4){
            throw new RuntimeException ("Conta inválida (4 Dígitos obrigatório) ");
        } 
         else if (nSenha.length()!=4){
            throw new RuntimeException("Senha inválida (4 Dígitos obrigatório)");
        }
         else if(nSenha.isEmpty()){
            throw new RuntimeException("Senha obrigatória");
        }
         else if(nConta.equals(nSenha) || nSenha.equals(nConta)){
            throw new RuntimeException("Conta e senha não pode ser igual");
            
        }
        else if (verificaConta==true){
            throw new RuntimeException ("Conta já cadastrada");
       }
    }
    //Aprsenta um erro caso o campo do login esteja vázio
    public static void validarLogin(Conta conta)throws RuntimeException{
        String nConta = Integer.toString(conta.getNumeroConta());
        String nSenha = Integer.toString(conta.getSenha());
        if (nConta.length()!=4){
            throw new RuntimeException ("Conta inválida (4 Dígitos obrigatório) ");
        } 
        else if (nSenha.length()!=4){
            throw new RuntimeException("Senha inválida (4 Dígitos obrigatório)");
        }
        else if (nConta.equals(nSenha) || nSenha.equals(nConta)){
            throw new RuntimeException("Conta e senha não pode ser igual");
            
        }
    }
    //Se tudo estiver correto cadastro Cliente e Conta no Banco de Dados
    public static void inserir(Cliente cliente,Conta conta) throws RuntimeException, SQLException{
        validarCliente(cliente);
        validarConta(conta);
        ClienteDAO.adicionarBanco(cliente);
        int clienteID = ClienteDAO.retornaID(cliente);
        ContaDAO.adicionarBanco(conta,clienteID);
       
        
    }
    //Valida o Login para ter acesso a Aplicação
    public static Conta login(Conta conta) throws RuntimeException, SQLException{
        validarLogin(conta);
        conta =  ContaDAO.validarConta(conta);
        return conta;
    }
    
    //Apresenta um erro caso a conta possua algum valor no saldo na hora de excluir
    public static void excluirConta(Conta conta)throws RuntimeException{
        if (conta.getSaldo()!=0){
            throw new RuntimeException("Por favor, retire todo o dinheiro da conta antes de excluir");
        }
    }
    
}
