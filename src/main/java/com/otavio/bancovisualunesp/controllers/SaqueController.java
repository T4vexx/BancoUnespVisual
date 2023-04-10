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

import java.net.URL;
import java.util.ResourceBundle;

public class SaqueController implements Initializable {

    @FXML
    private TextField valorSaque;
    @FXML
    private Label setarSaque;
    @FXML
    private Label setarErroSaque;
    @FXML
    private Button sacarButtom;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DisplayBanco displayBanco;
        displayBanco = DBUtils.getDisplayBanco();

        sacarButtom.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(valorSaque.getText() != "") {
                    if(Integer.parseInt(valorSaque.getText()) > 0) {
                        double meuSaldo = displayBanco.operacaoSwitch(3, actionEvent, valorSaque.getText());
                        if(meuSaldo == 0) {
                            setarSaque.setText("Saque de R$"+valorSaque.getText()+" efeuado | seu saldo é: "+displayBanco.operacaoSwitch(1,actionEvent,""));
                            setarErroSaque.setText("");
                        } else {
                            setarSaque.setText("");
                            setarErroSaque.setText("Erro: Saldo insuficiente");
                        }
                    } else {
                        setarSaque.setText("");
                        setarErroSaque.setText("Erro: Digite um valor maior que zero");
                    }
                } else {
                    setarSaque.setText("");
                    setarErroSaque.setText("Erro: Digite um valor");
                }
            }
        });

    }
}
