package com.otavio.bancovisualunesp.banco;

import java.util.Date;

/**
 * <h1>Transações</h1>
 * Classe que armazena todos os dados de uma transação
 * @author Otavio Augusto Teixeira <otavio.a.teixeira@unesp.br>
 * @version 1.2
 * @since 1.2
 */
public class Transacoes {
    private String tipo;
    private double valor;
    private Date data;

    /**
     * Contructor de transação
     * @param tipo tipo de uma determinada transações (Deposito ou Saque) String
     * @param valor valor da transação double
     * @param data data da transação Date
     */
    public Transacoes(String tipo, double valor, Date data) {
        this.tipo = tipo;
        this.valor = valor;
        this.data = data;
    }

    public String getTipo() {
        return tipo;
    }

    public double getValor() {
        return valor;
    }

    public Date getData() {
        return data;
    }
}
