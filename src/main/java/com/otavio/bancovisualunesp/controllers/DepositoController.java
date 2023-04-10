package com.otavio.bancovisualunesp.controllers;

import com.otavio.bancovisualunesp.banco.DBUtils;
import com.otavio.bancovisualunesp.banco.DisplayBanco;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;

import java.net.URL;
import java.util.ResourceBundle;

public class DepositoController implements Initializable {
    @FXML
    private Button depositarButtom;
    @FXML
    private TextField valorDeposito;
    @FXML
    private Label setarSaldo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DisplayBanco displayBanco;
        displayBanco = DBUtils.getDisplayBanco();

        depositarButtom.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if(valorDeposito.getText() != "") {
                    if(Integer.parseInt(valorDeposito.getText()) > 0) {
                        double meuSaldo = displayBanco.operacaoSwitch(2, actionEvent, valorDeposito.getText());
                        setarSaldo.setText("Seu novo saldo é : "+meuSaldo);
                        setarSaldo.setTextFill(Paint.valueOf("#ffffff"));
                    } else {
                        setarSaldo.setTextFill(Paint.valueOf("#ff0000"));
                        setarSaldo.setText("Erro: Digite um valor maior que zero");
                    }
                } else {
                    setarSaldo.setTextFill(Paint.valueOf("#ff0000"));
                    setarSaldo.setText("Erro: Digite um valor");
                }
            }
        });
    }
}
