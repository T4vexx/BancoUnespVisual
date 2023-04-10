/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.otavio.bancovisualunesp.banco;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * <h1>Extrato</h1>
 * <p> Classe que cria um arquivo com as informações da conta do usuário (Extrato)
 * @author Otavio Augusto Teixeira <otavio.a.teixeira@unesp.br>
 * @version 1.2
 * @since 1.1
 */
public class Extrato {
    private static int extratosTirados=0;

    /**
     * Método que gera um arquivo de extrato
     * @param nome nome de uma conta String
     * @param endereco endereço de um conta String
     * @param cpf cpf de uma conta Strig
     * @param saldo saldo de uma conta Double
     * @param numero numero de uma conta Int
     * @param nomeBanco nome do banco de uma conta String
     * @param Agencia numero de uma agencia Int
     */
    public static void gerarExtrato(String nome, String endereco, String cpf, double saldo, int numero, String nomeBanco, int Agencia, ArrayList<Transacoes> transacoes) {
        //File diretorio = new File("C:\\Users\\tavexx\\Documents\\NetBeansProjects\\bancovisualunesp\\src\\main\\java\\com\\otavio\\bancovisualunesp\\extratos");
        File diretorio = new File("C:\\Users\\tavin\\OneDrive\\Desktop\\3semestre\\BancoUnesp-Visual-Java\\src\\main\\java\\com\\otavio\\bancovisualunesp\\extratos");
        String message = String.format("%d_%d_extrato.txt",numero,extratosTirados);
        File arquivo = new File(diretorio, message);
        SimpleDateFormat formatter = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy '|' HH:mm:ss");

        try {

            arquivo.createNewFile();
            FileWriter fileWriter = new FileWriter(arquivo, StandardCharsets.UTF_8);
            PrintWriter printWriter = new PrintWriter(fileWriter);


            printWriter.println("**********************************************************************************************");
            printWriter.println("***                                        EXTRATO                                         ***");
            printWriter.println("**********************************************************************************************");
            printWriter.println("Banco: "+nomeBanco+"  Agencia: "+Agencia);
            printWriter.println("**********************************************************************************************");
            printWriter.println("Conta: "+nome+"  CPF: "+cpf+"  Numero: "+numero);
            printWriter.println("Endereço: "+endereco+"  Data: "+formatter.format(new Date()));
            printWriter.println("");
            printWriter.println("**********************************************************************************************");
            printWriter.println("***                                       TRANSAÇÔES                                       ***");
            printWriter.println("**********************************************************************************************");
            for(Transacoes t: transacoes) {
                printWriter.println("Tipo: "+t.getTipo()+"  Valor: "+t.getValor()+"  Data: "+formatter.format(t.getData()));
            }
            printWriter.println("");
            printWriter.println("Saldo Final: "+saldo);


            printWriter.flush();
            printWriter.close();

        } catch (IOException e) {
            System.out.println(e);
        }

        extratosTirados++;
    }
}
