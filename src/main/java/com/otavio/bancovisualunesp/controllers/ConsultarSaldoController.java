package com.otavio.bancovisualunesp.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ConsultarSaldoController implements Initializable {

    @FXML
    private Label consulta_saldo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setConsultarSaldosInfos(double valor) {
        consulta_saldo.setText("R$ "+valor);
    }
}
