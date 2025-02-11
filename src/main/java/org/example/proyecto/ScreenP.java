package org.example.proyecto;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

import java.io.IOException;

/**@author AlejandroGpublic
**/
public class ScreenP {
    @FXML
    ProgressBar progress;

    @FXML
    public void initialize() {
        Task<Void> cargar = new Task<>() {
            @Override
            protected Void call() throws Exception {
                for (int i = 1; i <= 100; i++) {
                    Thread.sleep(50);
                    updateProgress(i, 100);
                }
                return null;
            }

        };

        progress.progressProperty().bind(cargar.progressProperty());

        cargar.setOnSucceeded(event -> {
            try {
                Principal();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        new Thread(cargar).start();
    }
    public void Principal () throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Menu.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 500, 700);
        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.show();
    }
}
