package org.example.proyecto;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {



    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Pantalla.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 265, 250);
        ScreenP p=fxmlLoader.getController();
        p.setSplashStage(stage);
        stage.setTitle("Cargando");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}