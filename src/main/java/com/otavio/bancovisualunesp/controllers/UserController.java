package com.otavio.bancovisualunesp.controllers;

import com.otavio.bancovisualunesp.HelloApplication;
import com.otavio.bancovisualunesp.banco.DBUtils;
import com.otavio.bancovisualunesp.banco.DisplayBanco;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class UserController implements Initializable {

    @FXML
    private Label label_nome;
    @FXML
    private Label label_conta;
    @FXML
    private Label label_agencia;
    @FXML
    private BorderPane bp;
    @FXML
    private Button saldo;
    @FXML
    private Button deposito;
    @FXML
    private Button saque;
    @FXML
    private Button transferencia;
    @FXML
    private Button pix;
    @FXML
    private Button gerarExtrato;
    @FXML
    private Button mudarSenha;
    @FXML
    private Button sair;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DisplayBanco displayBanco = DBUtils.getDisplayBanco();
        FXMLLoader filho;
        filho = loadPage("saldo");
        ConsultarSaldoController consultarSaldoController = filho.getController();
        consultarSaldoController.setConsultarSaldosInfos(displayBanco.operacaoSwitch(1,new ActionEvent(),""));

        saldo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FXMLLoader root;
                double valor = displayBanco.operacaoSwitch(1,actionEvent,"");
                try {
                    root = loadPage("saldo");
                    ConsultarSaldoController consultarSaldoController = root.getController();
                    consultarSaldoController.setConsultarSaldosInfos(valor);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        deposito.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FXMLLoader root;
                try {
                    root = loadPage("deposito");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        saque.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FXMLLoader root;
                try {
                    root = loadPage("saque");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        transferencia.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FXMLLoader root;
                try {
                    root = loadPage("transferencia");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        pix.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FXMLLoader root;
                try {
                    root = loadPage("pix");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        gerarExtrato.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                displayBanco.operacaoSwitch(6,actionEvent,"");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Extrato gerado na pasta extratos");
                alert.setResizable(false);
                alert.setTitle("Extrato");
                alert.show();
            }
        });

        mudarSenha.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FXMLLoader root;
                try {
                    root = loadPage("mudarsenha");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        sair.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                displayBanco.operacaoSwitch(7,actionEvent,"");
            }
        });
    }

    private FXMLLoader loadPage(String page) {
        Parent root = null;
        FXMLLoader loader = null;

        try{
            loader = new FXMLLoader(HelloApplication.class.getResource(page+"-user-view.fxml"));
            root = loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        bp.setCenter(root);
        return loader;
    }

    public void setContaInfos(String nome, String conta, String agencia) {
        label_nome.setText(nome);
        label_conta.setText(conta);
        label_agencia.setText(agencia);
    }
}
