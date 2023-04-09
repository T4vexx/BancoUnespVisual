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

public class MudarSenhaControll implements Initializable {
    @FXML
    private Button changeSenhaButton;
    @FXML
    private TextField oldSenha;
    @FXML
    private TextField newSenha;
    @FXML
    private Label senhaMensagem;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DisplayBanco displayBanco;
        displayBanco = DBUtils.getDisplayBanco();

        changeSenhaButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(oldSenha.getText() != "" && newSenha.getText() != "") {
                    double result = displayBanco.operacaoSwitch(8, actionEvent, oldSenha.getText()+"#"+newSenha.getText());
                    if(result == 0) {
                        senhaMensagem.setText("Senha alterada com sucesso");
                        senhaMensagem.setTextFill(Paint.valueOf("#ffffff"));
                    } else {
                        senhaMensagem.setText("Falha ao tentar mudar sua senha, Tente novamente!");
                        senhaMensagem.setTextFill(Paint.valueOf("#ff0000"));
                    }
                } else {
                    senhaMensagem.setText("Erro: Falta de informações!");
                    senhaMensagem.setTextFill(Paint.valueOf("#ff0000"));
                }
            }
        });
    }
}
