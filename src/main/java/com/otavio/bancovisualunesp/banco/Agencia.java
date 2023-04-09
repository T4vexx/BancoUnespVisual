/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.otavio.bancovisualunesp.banco;

import java.util.ArrayList;

/**
 * <h1>Agencia</h1>
 * <p>Classe que cordena toda as funcionalidades das agencias e suas contas
 *
 * @author Otavio Augusto Teixeira <otavio.a.teixeira@unesp.br>
 * @version 1.2
 * @since 1.0
 */
public class Agencia {
    private int codigo;
    private String nome;
    private String endereco;
    private ArrayList<Conta> contas;

    /**
     * Contructor de Agencia
     * @param codigo Numero da agencia Int
     * @param nome Nome da agencia String
     * @param endereco endereco da String
     */
    public Agencia(int codigo, String nome, String endereco) {
        this.codigo = codigo;
        this.nome = nome;
        this.endereco = endereco;
        contas = new ArrayList<Conta>();
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    /**
     * Método que cria uma conta na agencia
     * @param conta conta de um usuario do tipo Conta
     */
    public void cadastrarConta(Conta conta) {
        contas.add(conta);
    }

    /**
     * Método que recebe uma numero de conta e a senha e retorna uma conta
     * @param numeroConta numero da conta de um usuario Int
     * @param senha senha de um usuario String
     * @return retorna uma conta do tipo Conta
     */
    public Conta buscarConta(int numeroConta, String senha) {
        Conta minhaConta = null;
        for(Conta conta: contas) {
            if(conta.getNumeroDaConta() == numeroConta && conta.validarSenha(senha)) {
                minhaConta = conta;
            }
        }
        return minhaConta;
    }

    /**
     * Método que recebe um numero de conta e devolve uma conta
     * @param numeroConta numero da conta de um usuario Int
     * @return retorna uma conta do tipo Conta
     */
    public Conta buscarConta(int numeroConta) {
        Conta minhaConta = null;
        for(Conta conta: contas) {
            if(conta.getNumeroDaConta() == numeroConta) {
                minhaConta = conta;
            }
        }
        return minhaConta;
    }

    /**
     * Método que recebe um cpf e devolve uma conta
     * @param cpf Cpf de um usuario String
     * @return retorna uma conta do tipo Conta
     */
    public Conta buscarConta(String cpf) {
        Conta minhaConta = null;
        for(int i=0; i<contas.size(); i++) {
            if(contas.get(i).getCpf().equalsIgnoreCase(cpf)) {
                minhaConta = contas.get(i);
                break;
            }
        }

        return minhaConta;
    }
}
