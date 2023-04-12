package com.otavio.bancovisualunesp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * <h1>Main</h1>
 * <p>Classe que cordena toda as funcionalidades do app
 *
 * @author Otavio Augusto Teixeira <otavio.a.teixeira@unesp.br>
 * @version 1.0
 * @since 1.0
 */
public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 609, 400);
        stage.setTitle("Banco Unesp | Log-In page");
        stage.setResizable(false);
        //stage.getIcons().add(new Image("C:\\Users\\tavexx\\Documents\\NetBeansProjects\\bancovisualunesp\\src\\main\\resources\\com\\otavio\\bancovisualunesp\\bank-building.png"));
        stage.getIcons().add(new Image("C:\\Users\\tavin\\OneDrive\\Desktop\\3semestre\\BancoUnesp-Visual-Java\\src\\main\\resources\\com\\otavio\\bancovisualunesp\\bank-building.png"));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}