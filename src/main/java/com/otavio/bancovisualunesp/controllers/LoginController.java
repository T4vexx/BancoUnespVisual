package com.otavio.bancovisualunesp.controllers;

import com.otavio.bancovisualunesp.banco.DBUtils;
import com.otavio.bancovisualunesp.banco.DisplayBanco;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private Button login;
    @FXML
    private TextField numeroDaAgencia;
    @FXML
    private TextField numeroDaConta;
    @FXML
    private PasswordField password;
    @FXML
    private Label erroNoFomulario;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                boolean isCredentialsCorrect;
                DisplayBanco displayBanco;
                displayBanco = DBUtils.getDisplayBanco();
                if(numeroDaAgencia.getText() != "" && numeroDaConta.getText() != "" && password.getText() != "") {
                    isCredentialsCorrect = displayBanco.login(actionEvent,numeroDaAgencia.getText(),numeroDaConta.getText(),password.getText());
                    if(!isCredentialsCorrect) {
                        erroNoFomulario.setText("Credencias não coincidentes");
                    }
                } else {
                    erroNoFomulario.setText("Credenciais não poddem ser vazias");
                }
            }
        });
    }

}
