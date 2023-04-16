/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.otavio.bancovisualunesp.banco;

import java.util.ArrayList;
import java.util.Date;

/**
 * Conta
 * Classe que armazena todos os dados de uma conta
 * @author Otavio Augusto Teixeira otavio.a.teixeira@unesp.br
 * @version 1.2
 * @since 1.0
 */
public class Conta {
    private int numero;
    private double saldo;
    private String nome;
    private String endereco;
    private String cpf;
    private String dataNascimento;
    private String senha;
    private int agencia;
    private ArrayList<Transacoes> transacoes = new ArrayList<Transacoes>();

    /**
     * Contructor de conta
     * @param nome nome de uma conta String
     * @param dataNascimento data nascimento de uma conta String
     * @param endereco endereço de uma conta String
     * @param cpf cpf de uma conta String
     * @param saldo saldo de uma conta Double
     * @param numero numero de registro de uma conta Int
     * @param senha senha de uma conta String
     * @param agencia numero da agencia da conta Int
     */
    public Conta(String nome, String dataNascimento, String endereco, String cpf, double saldo, int numero, String senha, int agencia) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.cpf = cpf;
        this.saldo = saldo;
        this.senha = senha;
        this.numero = numero;
        this.agencia = agencia;
    }

    public String getNome() {
        return nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public int getAgencia() {
        return agencia;
    }

    public String getEndereco() {
        return endereco;
    }

    public int getNumeroDaConta() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getCpf() {
        return cpf;
    }

    public ArrayList<Transacoes> getTransacoes() {
        return transacoes;
    }

    /**
     * Método que deposita dinheiro na conta de um usuario
     * @param valor valor de deposito Double
     */
    public void depositar(double valor) {
        Transacoes transacao = new Transacoes("Deposito",valor,new Date());
        transacoes.add(transacao);
        saldo += valor;
    }

    /**
     * Método que recebe um valor e faz um saque de uma conta
     * @param valor valor para retirar da conta Double
     * @return retorna true caso a operação der certo e false caso nao tiver saldo
     */
    public boolean sacar(double valor) {
        if(saldo - valor < 0) {
            return false;
        } else {
            Transacoes transacao = new Transacoes("Saque",valor,new Date());
            transacoes.add(transacao);
            saldo -= valor;
            return true;
        }
    }

    /**
     * Método que recebe uma senha e valida se é a senha correta da conta
     * @param senha senha de uma conta String
     * @return retorna true case a senha tiver correta e false se a senha nao coincidir
     */
    public boolean validarSenha(String senha) {
        if(this.senha.equalsIgnoreCase(senha)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método que verifica se antiga senha é valida e caso for seta a antiga com a nova senha
     * @param senhaAntiga Senha antiga de uma conta String
     * @param novaSenha Senha nova para uma conta String
     * @return Retorna true se a senha for trocada com sucesso e false caso der erro
     */
    public boolean setSenha(String senhaAntiga, String novaSenha) {
        if(validarSenha(senhaAntiga)) {
            senha = novaSenha;
            return true;
        } else {
            return false;
        }
    }

}
