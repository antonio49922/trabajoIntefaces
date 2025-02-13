package org.example.proyecto;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Antonio
 * @vesion 1.0
 * @since 10/02/2025
 **/

/**
 * Clase utilitaria para cambiar de escena en la aplicacion
 * Proporciona un metodo estatico para cargar y mostrar una nueva escena
 */
public class CambioPantalla {

    /**
     * CAmbia la escena actual a una nueva
     * @param currentStage El escenario actual
     * @param fxmlPath Ruta del archivo FXML de la nueva escena
     * @param title Titulo de la nueva ventana
     * @throws IOException Si ocurre un error al cargar el archivo FXML
     */
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
