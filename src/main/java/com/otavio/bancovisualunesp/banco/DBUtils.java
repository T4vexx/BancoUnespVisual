package com.otavio.bancovisualunesp.banco;

import com.otavio.bancovisualunesp.HelloApplication;
import com.otavio.bancovisualunesp.controllers.UserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * <h1>DBUtils</h1>
 * Classe que possui funçoes utils para o uso no contexto de mudanças de telas
 * @author Otavio Augusto Teixeira <otavio.a.teixeira@unesp.br>
 * @version 1.3
 * @since 1.3
 */
public class DBUtils {
    private static DisplayBanco displayBanco = new DisplayBanco();

    public static DisplayBanco getDisplayBanco() {
        return displayBanco;
    }

    /**
     * Método estatico que paga da tela de login para a tela de usuario
     * @param event ActionEvent
     * @param fxmlFile nome do arquivo fxml String
     * @param title titulo da pagina String
     * @param nome nome do usuario do banco String
     * @param agencia numero da agencia do usuario String
     * @param conta numero da conta do usuario String
     */
    public static void changeScene(ActionEvent event, String fxmlFile, String title,String nome,String agencia,String conta) {
        Parent root = null;

        if(nome != "" && agencia != "" && conta != "") {
            try {
                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("user-view.fxml"));
                root = loader.load();
                UserController userController = loader.getController();
                userController.setContaInfos(nome,conta,agencia);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(fxmlFile));
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 609,400));
        stage.show();
    }


}
