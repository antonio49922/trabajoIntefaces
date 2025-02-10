package org.example.proyecto;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CambioPantalla {
    public static void switchScene(Stage currentStage, String fxmlPath, String title) throws IOException {
        // Cargar el archivo FXML
        FXMLLoader loader = new FXMLLoader(CambioPantalla.class.getResource(fxmlPath));
        Parent root = loader.load();

        // Crear una nueva escena
        Scene scene = new Scene(root);

        // Configurar el título y establecer la escena
        currentStage.setTitle(title);
        currentStage.setScene(scene);
        currentStage.show();
    }
}
