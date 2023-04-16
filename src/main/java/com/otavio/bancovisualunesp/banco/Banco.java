/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.otavio.bancovisualunesp.banco;

import java.util.ArrayList;

/**
 * Banco
 * <p>Classe que cordena toda as funcionalidades dos bancos e agencia
 * @author Otavio Augusto Teixeira otavio.a.teixeira@unesp.br
 * @version 1.2
 * @since 1.0
 */
public class Banco {
    private int numero;
    private String nome;
    private String cnpj;
    private String endereco;
    private Conta contaLogada;
    private ArrayList<Agencia> agencias;


    /**
     * Constructor da clasee banco
     *
     * @param nome nome do banco String
     * @param numero numero do banco Int
     * @param cnpj cnpj do banco String
     * @param endereco enderecço do banco String
     */
    public Banco(String nome, int numero, String cnpj, String endereco) {
        this.nome = nome;
        this.numero = numero;
        this.cnpj = cnpj;
        this.endereco = endereco;
        agencias = new ArrayList<Agencia>();
    }

    public int getNumero() {
        return numero;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public int getNumeroUsuario() {
        return contaLogada.getNumeroDaConta();
    }

    public String getUsuarioCpf() {
        return contaLogada.getCpf();
    }

    public double getSaldoUsuario() {
        return contaLogada.getSaldo();
    }

    public String getNomeUsuario() {
        return contaLogada.getNome();
    }

    public String getEnderecoUsuario() {
        return contaLogada.getEndereco();
    }

    public int getAgenciaUsuario() {
        return contaLogada.getAgencia();
    }

    public ArrayList<Transacoes> getTransacoesUsuario() {
        return contaLogada.getTransacoes();
    }

    /**
     * Método que cadastra uma nova agencia no banco
     *
     * @param codigo Numero da agencia Int
     * @param nome Nome da agencia String
     * @param endereco Endereço da agencia String
     */
    public void cadastrarAgencia(int codigo, String nome, String endereco) {
        Agencia agencia = new Agencia(codigo,nome,endereco);
        agencias.add(agencia);
    }

    /**
     * Método que cadastra uma nova agencia no banco
     *
     * @param agencia agencia do tipo Agencia
     */
    public void cadastrarAgencia(Agencia agencia) {
        agencias.add(agencia);
    }

    /**
     * Método que recebe uma agencia e insere ela nas agencias do banco
     * @param numeroDaAgencia numero da agencia In
     * @return Retorna um objeto do tipo Agencia
     */
    public Agencia buscarAgencia(int numeroDaAgencia) {
        return agencias.get(numeroDaAgencia);
    }

    /**
     * Método que cadastra uma nova conta dentro de uma determinada agencia do banco
     * @param nome String
     * @param dataNascimento String
     * @param endereco String
     * @param cpf String
     * @param saldo Double
     * @param numero Int
     * @param senha String
     * @param numeroDaAgencia Int
     */
    public void cadastrarConta(String nome, String dataNascimento, String endereco, String cpf, double saldo, int numero, String senha, int numeroDaAgencia) {
        Conta novaConta;
        Agencia agencia;

        novaConta = new Conta(nome, dataNascimento, endereco, cpf, saldo, numero, senha,numeroDaAgencia);
        agencia = buscarAgencia(numeroDaAgencia);
        agencia.cadastrarConta(novaConta);

    }

    /**
     * Método que recebe os dados de um cliente e retorna se as credencias estao corretas para logar ou não
     * @param numAgencia Numaro da agencia do cliente Int
     * @param numConta Numero da conta do cliente Int
     * @param senha Senha do cliente String
     * @return Retorna um valor booleano true se o a conta e a senha estiver correta e false se tiver com as credencias erradas
     */
    public boolean logarCliente(int numAgencia, int numConta, String senha) {
        Agencia minhaAgencia;
        Conta minhaConta=null;
        minhaAgencia = agencias.get(numAgencia);
        if(minhaAgencia != null) {
            minhaConta = minhaAgencia.buscarConta(numConta, senha);

            if(minhaConta != null) {
                contaLogada = minhaConta;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }

    /**
     * Método para relizar um deposito na conta do cliente
     * @param deposito valor do deposito Double
     */
    public void realizarDeposito(double deposito) {
        contaLogada.depositar(deposito);
    }

    /**
     * Método que reliza um saque
     * @param saque valor para o saque Double
     * @return Retorna true se a operação de saque foi realizada e false caso o cliente não tiver saldo
     */
    public boolean realizarSaque(double saque) {
        return contaLogada.sacar(saque);
    }

    /**
     * Método que retorna saldo do cliente
     * @return Retona o valor do saldo do cliente Double
     */
    public double saldo() {
        return contaLogada.getSaldo();
    }

    /**
     * Método que recebe as informações da conta e agencia de um cliente e retorna o status da transferencia
     * @param numAgencia Numero da agencia do cliente Int
     * @param numConta Numero da conta do cliente Int
     * @param valor valor da tranferencia Double
     * @return Retorna um int que relacionado ao status da transferencia (0 - funcionou / 1 - agencia ou conta nao encontrada / 2 - Senha incorreta)
     */
    public int tranferencia(int numAgencia, int numConta, double valor) {
        Agencia minhaAgencia=null;
        Conta contaParaTransferencia=null;
        boolean isSaqueAvaiable=false;
        minhaAgencia = buscarAgencia(numAgencia);
        contaParaTransferencia = minhaAgencia.buscarConta(numConta);

        if(minhaAgencia != null && contaParaTransferencia != null) {
            isSaqueAvaiable = realizarSaque(valor);
            if(isSaqueAvaiable) {
                contaParaTransferencia.depositar(valor);
                return 0;
            } else {
                return 2;
            }
        } else {
            return 1;
        }

    }

    /**
     * Método que recebe um cpf de um destinatario e manda o valor para o destinario via pix
     * @param chaveCpf Cpf do destinatario do pix String
     * @param valor valor da tranferencia Double
     * @return Retorna um int que relacionado ao status do pix (0 - funcionou / 1 - Conta nao encontrada / 2 - Senha incorreta)
     */
    public int pix(String chaveCpf, double valor) {
        Conta contaPix=null;
        boolean isSaqueAvaiable;
        contaPix = getContaByCpf(chaveCpf);

        if(contaPix != null) {
            isSaqueAvaiable = realizarSaque(valor);
            if(isSaqueAvaiable) {
                contaPix.depositar(valor);
                return 0;
            } else {
                return 2;
            }
        } else {
            return 1;
        }
    }

    /**
     * Método que recebe um cpf e procura em todas as agencias e retorna uma conta caso acha
     * @param cpf cpf de uma conta String
     * @return retona uma conta do tipo Conta
     */
    public Conta getContaByCpf(String cpf) {
        Agencia agenciaCpf=null;
        Conta contaCpf=null;

        for(int i=0; i<agencias.size(); i++) {
            agenciaCpf = agencias.get(i);
            contaCpf = agenciaCpf.buscarConta(cpf);
            if(contaCpf != null) {
                break;
            }
        }

        return contaCpf;
    }

    /**
     * Método que recebe uma senha antiga e uma nova verifica a antiga e se for valida muda a antiga para a nova
     * @param senhaAntiga senha antiga do cliente String
     * @param novaSenha nova senha para ser alterada
     * @return retorna true se a mudança foi efetivada ou false caso der erro
     */
    public boolean trocarSenha(String senhaAntiga, String novaSenha) {
        return contaLogada.setSenha(senhaAntiga,novaSenha);
    }

    /**
     * Método que retira a referencia da conta logada
     */
    public void deslogarConta() {
        contaLogada = null;
    }

}
