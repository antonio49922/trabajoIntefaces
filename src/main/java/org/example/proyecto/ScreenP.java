package org.example.proyecto;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

/**@author AlejandroGpublic
**/

public class ScreenP {
    @FXML
    ProgressBar progress;
    private Stage SplashStage;

    public void setSplashStage(Stage SplashStage) {
        this.SplashStage = SplashStage;
    }

    @FXML
    public void initialize(){

        if (SplashStage != null) {
            SplashStage.close();
        }
        Task<Void> cargar= new Task<>(){

            @Override
            protected Void call() throws Exception {
                for (int i=1; i<=100; i++){
                    Thread.sleep(50);
                    updateProgress(i,100);
                }

                return null;
            }
        };
        progress.progressProperty().bind(cargar.progressProperty());
        cargar.setOnSucceeded(e -> {
            Platform.runLater(() -> {
            try {
                Principal();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        });
        new Thread(cargar).start();
    }


    public void Principal () throws IOException {

        // Cerrar la pantalla de carga si sigue abierta
        if (SplashStage != null) {
            SplashStage.close();
        }

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Menu.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 550, 700);
        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.show();
    }
}
