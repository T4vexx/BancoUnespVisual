package com.otavio.bancovisualunesp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main
 * <p>Classe que cordena toda as funcionalidades do app
 * Algumas considerações para que o programa rode, algumas partes no codigo tem que passar o diretorio correto na onde
 * o senhor instalou o codigo, são eles: <br>
 * Na classe HelloApplication.java - a imagem esta nas resources bank-building.png<br>
 * Na classe DisplayBanco.java - os diretorios dos arquivos agencias banco contas txt<br>
 * Na classe Extrato.java - que é o caminho ate a pastas extratos onde ficaram os pdf (O senhor pode alterar o diretorio para um que fique mais facil)<br>
 * <br>
 * Dependencias:<br>
 * itextpdf - versao 5 - a 7 nao funciona quase seja instalada- https://github.com/itext/itextpdf/releases/tag/5.5.13.3<br>
 * JavaFxv
 * <br>
 * Professor o UML esta na pasta com.otavio.bancovisualunesp - tem png e .uml também <br>
 * O javadoc esta na pasta .javadoc no diretorio principal<br>
 *
 * @author Otavio Augusto Teixeira otavio.a.teixeira@unesp.br
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
        stage.getIcons().add(new Image("C:\\Users\\tavexx\\Documents\\NetBeansProjects\\bancovisualunesp\\src\\main\\resources\\com\\otavio\\bancovisualunesp\\bank-building.png"));
        //stage.getIcons().add(new Image("C:\\Users\\tavin\\OneDrive\\Desktop\\3semestre\\BancoUnesp-Visual-Java\\src\\main\\resources\\com\\otavio\\bancovisualunesp\\bank-building.png"));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}