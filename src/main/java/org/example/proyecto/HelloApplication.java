package org.example.proyecto;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

 /**
   * @author Emily-Antonio
   * @vesion 1.0
   * @since 10/02/2025
        **/

/**
 *
 */

public class HelloApplication extends Application {

    /**
     * escena la cual se carga al iniciar la aplicacion
     * @param stage
     * @throws IOException si ocurre un error al cargar la escena
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Pantalla.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 402, 233);
        ScreenP p=fxmlLoader.getController();
        p.setSplashStage(stage);
        stage.setTitle("Cargando");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * main donde se ejectua la aplicacion
     * @param args
     */

    public static void main(String[] args) {
        launch();
    }
}