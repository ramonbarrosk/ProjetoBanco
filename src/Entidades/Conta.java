package Entidades;


import Conexão.ContaDAO;
import Telas.Login;

import java.util.*;
import javax.swing.JOptionPane;

public class Conta {

    private int saldo;
    private int numeroConta;
    private int senha;
    private long id;
    public ArrayList<Object> extrato;

    public Conta(int numeroConta, int senha) {
        this.numeroConta = numeroConta;
        this.senha = senha;
        this.extrato = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public boolean sacar(int valor) {
        if (valor > 0 && valor <= this.getSaldo()) {
            this.saldo-=valor;
           
            return true;
        } else {
             return false;
        }
    }

    public boolean depositar(int valor,Conta conta) {
        if( valor>0 && valor<1000){
            int atual = conta.getSaldo();
            int total = atual+=valor;
            
            conta.setSaldo(total);
            return true;
        }else{
            return false;
        }
        
    }
    public int transferir (int valor, Conta conta){
        int transfer = 0;
       if(conta.getNumeroConta()==Login.lconta.getNumeroConta()){
                     transfer = 2;
        
                    }
       else if(valor<=this.saldo && valor>0){
            this.saldo-=valor;
            int atual = conta.getSaldo();
            int total = atual+=valor;
            conta.setSaldo(total);
            
            transfer = 1;
            
        }
        
        else{
            transfer = 0;
        }
        return transfer;
        
       
    }
   
    public int getSaldo() {
        return saldo;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public int getSenha() {
        return senha;
    }
    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }
}
