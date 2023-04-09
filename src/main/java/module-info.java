module com.otavio.bancovisualunesp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.otavio.bancovisualunesp to javafx.fxml;
    exports com.otavio.bancovisualunesp;
    exports com.otavio.bancovisualunesp.controllers;
    opens com.otavio.bancovisualunesp.controllers to javafx.fxml;
}