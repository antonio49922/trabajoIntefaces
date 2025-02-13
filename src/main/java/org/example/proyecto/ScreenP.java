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

/**
 * Esta controla una pantalla de carga al iniciar la aplicacion, utiliza la barra de progreso
 * para mostrar el progreso de la carga
 */
public class ScreenP {
    @FXML
    ProgressBar progress;
    private Stage SplashStage;

    public void setSplashStage(Stage SplashStage) {
        this.SplashStage = SplashStage;
    }

    /**
     * Se ejecuta automaticamente cuando se inicia la escena en la pantalla de carga
     * si el splash no es null se cierra
     */

    @FXML
    public void initialize(){

        if (SplashStage != null) {
            SplashStage.close();
        }
        /**
         * Se crea una tarea para simular la pantalla de carga
         */
        Task<Void> cargar= new Task<>(){

            /**
             * En el bucle se simula una carga de 0 a 100 que se actualiza cada 50 milisegundos
             * @return retorna nulo
             * @throws Exception si ocurre un error al hacer la pantalla de carga
             */
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

    /**
     * Se ejecuta despues de que la pantalla de carga halla finalizado, su funcion es cargar la pantalla siguiente
     * @throws IOException
     */

    public void Principal () throws IOException {
        if (SplashStage != null) {
            SplashStage.close();
        }

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("InicioSesion.fxml"));

        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 584, 469);

        stage.setTitle("Acceso a menú");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}