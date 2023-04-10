/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.otavio.bancovisualunesp.banco;

import javafx.event.ActionEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * <h1>Display Banco</h1>
 * <p>Classe que cordena toda a interface de usuario do banco, login e outras funcionalidades
 * @author Otavio Augusto Teixeira <otavio.a.teixeira@unesp.br>
 * @version 1.2
 * @since 1.0
 */
public class DisplayBanco {

    private Banco meuBanco;

    /**
     * Constructor para DisplayBanco
     */
    public DisplayBanco() {
        Scanner bancoFiles = null;
        Scanner agenciaFiles = null;
        Scanner contasFiles = null;
        String linha;
        String[] campos;
        //File banco = new File("C:\\Users\\tavexx\\Documents\\NetBeansProjects\\bancovisualunesp\\src\\main\\java\\com\\otavio\\bancovisualunesp\\banco.txt");
        //File agencia = new File("C:\\Users\\tavexx\\Documents\\NetBeansProjects\\bancovisualunesp\\src\\main\\java\\com\\otavio\\bancovisualunesp\\agencias.txt");
        //File conta = new File("C:\\Users\\tavexx\\Documents\\NetBeansProjects\\bancovisualunesp\\src\\main\\java\\com\\otavio\\bancovisualunesp\\contas.txt");
        File banco = new File("C:\\Users\\tavin\\OneDrive\\Desktop\\3semestre\\BancoUnesp-Visual-Java\\src\\main\\java\\com\\otavio\\bancovisualunesp\\banco.txt");
        File agencia = new File("C:\\Users\\tavin\\OneDrive\\Desktop\\3semestre\\BancoUnesp-Visual-Java\\src\\main\\java\\com\\otavio\\bancovisualunesp\\agencias.txt");
        File conta = new File("C:\\Users\\tavin\\OneDrive\\Desktop\\3semestre\\BancoUnesp-Visual-Java\\src\\main\\java\\com\\otavio\\bancovisualunesp\\contas.txt");

        try {
            bancoFiles = new Scanner(banco);
            agenciaFiles = new Scanner(agencia);
            contasFiles = new Scanner(conta);
        } catch (FileNotFoundException ex) {
            //
        }

        while(bancoFiles.hasNextLine()) {
            linha = bancoFiles.nextLine();
            campos = linha.split("#");
            Banco meuBanco = new Banco(campos[0],Integer.parseInt(campos[1]),campos[2],campos[3]);
            this.meuBanco = meuBanco;
        }

        while(agenciaFiles.hasNextLine()) {
            linha = agenciaFiles.nextLine();
            campos = linha.split("#");
            meuBanco.cadastrarAgencia(Integer.parseInt(campos[1]),campos[0],campos[2]);
        }

        while(contasFiles.hasNextLine()) {
            linha = contasFiles.nextLine();
            campos = linha.split("#");
            meuBanco.cadastrarConta(campos[0],campos[1],campos[2],campos[3],Double.parseDouble(campos[4]),Integer.parseInt(campos[6]),campos[7],Integer.parseInt(campos[5]));

        }

        bancoFiles.close();
        agenciaFiles.close();
        contasFiles.close();
        linha = null;
        campos = null;
    }

    /**
     * Método que verifica os dados de um usuario e se coincidir chama a nova tela de usuario
     * @param actE ActionEvent
     * @param numeroDaAgencia numero da agencia de um usuario String
     * @param numeroDaConta numero da conta de um usuario String
     * @param senha senha de um usuario String
     * @return true se o login tiver correto e false caso não esteja correto boolean
     */
    public boolean login(ActionEvent actE, String numeroDaAgencia, String numeroDaConta, String senha) {
        boolean isAccountLogged;
        isAccountLogged = meuBanco.logarCliente(Integer.parseInt(numeroDaAgencia), Integer.parseInt(numeroDaConta), senha);

        if(isAccountLogged) {
            telaUsuario(actE,meuBanco.getNomeUsuario(),numeroDaAgencia,numeroDaConta);
            return true;
        } else {
           return false;
        }
    }

    private void telaUsuario(ActionEvent actE,String nome,String agencia,String conta) {
        DBUtils.changeScene(actE,"user-view.fxml","Banco Unesp | Home page",nome,agencia,conta);
    }

    /**
     * Método que tem um seletro para qual operação executar
     * @param opt numero da operação desejada int
     * @param actE ActionEvent
     * @param valorRepass informações para ser repassadas para os métodos String
     * @return retorna double
     */
    public double operacaoSwitch(int opt, ActionEvent actE, String valorRepass) {
        double valor=0;

        switch (opt) {
            case 1:
                valor = operacaoSaldo();
                break;
            case 2:
                operacaoDeposito(valorRepass);
                valor = operacaoSaldo();
                break;
            case 3:
                valor = operacaoSaque(valorRepass);
                break;
            case 4:
                valor = operacaoTransferencia(valorRepass);
                break;
            case 5:
                valor = operacaoPIX(valorRepass);
                break;
            case 6:
                operacaoExtrato();
                valor = 0;
                break;
            case 7:
                operacaoSair();
                DBUtils.changeScene(actE,"login-view.fxml","Banco Unesp | Log-In page","","","");
                valor = 0;
                break;
            case 8:
                valor = operacaoNovaSenha(valorRepass);
                break;
        }
        return valor;
    }

    private void operacaoSair() {
        meuBanco.deslogarConta();
    }

    private void operacaoDeposito(String deposito) {
        meuBanco.realizarDeposito(Double.parseDouble(deposito));
    }

    private double operacaoSaque(String saque) {
        boolean result = meuBanco.realizarSaque(Double.parseDouble(saque));
        if(result) {
            return 0;
        } else {
            return 1;
        }
    }

    private double operacaoSaldo() {
        double meuSaldo;
        meuSaldo = meuBanco.saldo();
        return meuSaldo;
    }

    private double operacaoTransferencia(String dados) {
        String[] campos = dados.split("#");
        int result = meuBanco.tranferencia(Integer.parseInt(campos[0]), Integer.parseInt(campos[1]), Double.parseDouble(campos[2]));
        return Double.valueOf(String.valueOf(result));
    }

    private double operacaoPIX(String dados) {
        String[] campos = dados.split("#");
        int result = meuBanco.pix(campos[0], Double.parseDouble(campos[1]));
        return Double.parseDouble(String.valueOf(result));
    }

    private void operacaoExtrato() {
        System.out.println("test");
        Extrato.gerarExtrato(meuBanco.getNomeUsuario(), meuBanco.getEnderecoUsuario(), meuBanco.getUsuarioCpf(), meuBanco.getSaldoUsuario(), meuBanco.getNumeroUsuario(),meuBanco.getNome(),meuBanco.getAgenciaUsuario(),meuBanco.getTransacoesUsuario());
    }

    private double operacaoNovaSenha(String dados) {
        boolean isOperacaoValid;
        String[] campos = dados.split("#");
        isOperacaoValid = meuBanco.trocarSenha(campos[0],campos[1]);
        if(isOperacaoValid) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * Método para limpar a tela
     * Devido as diferenças de SOs a função deve saber de qual SO esta executando para poder limpar o terminal
     */
    public static void clrscr(){
        //Clears Screen in java
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {
            System.out.println(ex);
        }
    }
}
