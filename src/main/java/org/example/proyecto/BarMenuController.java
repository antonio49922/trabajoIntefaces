package org.example.proyecto;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class BarMenuController implements Initializable {

    @FXML
    private ImageView burgerImageView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Cargar la imagen "Burger diseño.jpg" ubicada en:
        // src/Recursos Gráficos/Imagenes/Burger diseño.jpg
        try {
            Image burgerImage = new Image(getClass().getResourceAsStream("/Recursos Gráficos/Imagenes/Comida rápida alimentos transparente.png"));
            burgerImageView.setImage(burgerImage);
            burgerImageView.setFitWidth(400);
            burgerImageView.setFitHeight(550);
        } catch (NullPointerException e) {
            System.err.println("Error: No se pudo cargar la imagen. Verifica la ruta del archivo.");
        }
    }
}