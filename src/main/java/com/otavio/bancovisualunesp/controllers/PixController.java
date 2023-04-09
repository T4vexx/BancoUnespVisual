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

public class PixController implements Initializable {
    @FXML
    public Button pixButton;
    @FXML
    public TextField cpfPix;
    @FXML
    public TextField valorPix;
    @FXML
    public Label pixMensagem;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DisplayBanco displayBanco;
        displayBanco = DBUtils.getDisplayBanco();

        pixButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (cpfPix.getText() != "" && valorPix.getText() != "") {
                    double result = displayBanco.operacaoSwitch(5, actionEvent, cpfPix.getText()+"#"+valorPix.getText());
                    if(result == 0) {
                        pixMensagem.setText(String.format("Transferência para o cpf %s feita com succeso | Valor %s", cpfPix.getText(), valorPix.getText()));
                        pixMensagem.setTextFill(Paint.valueOf("#ffffff"));
                    } else if(result == Double.parseDouble("1")) {
                        pixMensagem.setText("Credencial de conta errada - Conta inexistente ou nao encontrada");
                        pixMensagem.setTextFill(Paint.valueOf("#ff0000"));
                    } else {
                        pixMensagem.setText("Valor de saque indisponivel - Voce nao possui saldo suficiente!");
                        pixMensagem.setTextFill(Paint.valueOf("#ff0000"));
                    }
                } else {
                    pixMensagem.setText("Erro: Falta de informações!");
                    pixMensagem.setTextFill(Paint.valueOf("#ff0000"));
                }
            }
        });

    }
}
