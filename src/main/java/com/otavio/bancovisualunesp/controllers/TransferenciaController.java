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

public class TransferenciaController implements Initializable {
    @FXML
    private Button tranferirButtom;
    @FXML
    private TextField agenciaTransferencia;
    @FXML
    private TextField contaTransferencia;
    @FXML
    private TextField valorTransferencia;
    @FXML
    private Label transferenciaMensagem;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DisplayBanco displayBanco;
        displayBanco = DBUtils.getDisplayBanco();

        tranferirButtom.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(agenciaTransferencia.getText() != "" && contaTransferencia.getText() != "" && valorTransferencia.getText() != "") {
                    if(Integer.parseInt(valorTransferencia.getText()) > 0) {
                        double result = displayBanco.operacaoSwitch(4, actionEvent, agenciaTransferencia.getText() + "#" + contaTransferencia.getText() + "#" + valorTransferencia.getText());

                        if (result == 0) {
                            transferenciaMensagem.setText(String.format("Transferência para conta %s feita com succeso | Valor %s", contaTransferencia.getText(), valorTransferencia.getText()));
                            transferenciaMensagem.setTextFill(Paint.valueOf("#ffffff"));
                        } else if (result == Double.parseDouble("1")) {
                            transferenciaMensagem.setText("Credenciais de conta erradas - Conta inexistente ou nao encontrada");
                            transferenciaMensagem.setTextFill(Paint.valueOf("#ff0000"));
                        } else {
                            transferenciaMensagem.setText("Valor de saque indisponivel - Voce nao possui saldo suficiente!");
                            transferenciaMensagem.setTextFill(Paint.valueOf("#ff0000"));
                        }
                    } else {
                            transferenciaMensagem.setText("Erro: Digite um valor maior que zero");
                            transferenciaMensagem.setTextFill(Paint.valueOf("#ff0000"));
                    }
                } else {
                    transferenciaMensagem.setText("Erro: Falta de informações!");
                    transferenciaMensagem.setTextFill(Paint.valueOf("#ff0000"));
                }
            }
        });
    }
}
