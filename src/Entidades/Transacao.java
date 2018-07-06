package Entidades;

import conexão.DBConnection;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Transacao {

    private String opcao;
    private int valor;
    private String descricao;
    private int conta;
    private String data;
    private String hora;

    public Transacao(String opcao, int valor, String descricao, int conta) {
        Date data = new Date();
        SimpleDateFormat dataAtual = new SimpleDateFormat("dd-MM-YYYY");
        SimpleDateFormat horaAtual = new SimpleDateFormat("HH:mm:ss");
        String d = dataAtual.format(data);
        String h = horaAtual.format(data);
        this.opcao = opcao;
        this.valor = valor;
        this.descricao = descricao;
        this.conta = conta;
        this.data = d;
        this.hora = h;
    }

    public String getOpcao() {
        return opcao;
    }

    public void setOpcao(String opcao) {
        this.opcao = opcao;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getConta() {
        return conta;
    }

    public void setConta(int conta) {
        this.conta = conta;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
    

   

    

}
