package org.example.proyecto;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Menu {

    @FXML
    private ImageView img;

    @FXML
    public void bebidas_clic(MouseEvent event) throws IOException {
        Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        CambioPantalla.switchScene(currentStage, "bebidas.fxml", "Bebidas");
    }
    @FXML
    public void tapas_clic(MouseEvent event) throws IOException {
        Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        CambioPantalla.switchScene(currentStage, "BarMenuPrototype.fxml", "Registrate");
    }
    @FXML
    public void primero_clic(MouseEvent event) throws IOException {
        Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        CambioPantalla.switchScene(currentStage, "BarMenuPrototype.fxml", "Registrate");
    }
    @FXML
    public void segundo_clic(MouseEvent event) throws IOException {
        Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        CambioPantalla.switchScene(currentStage, "BarMenuPrototype.fxml", "Registrate");
    }
    @FXML
    public void postres_clic(MouseEvent event) throws IOException {
        Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        CambioPantalla.switchScene(currentStage, "BarMenuPrototype.fxml", "Registrate");
    }
    @FXML
    public void especial_clic(MouseEvent event) throws IOException {
        Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        CambioPantalla.switchScene(currentStage, "BarMenuPrototype.fxml", "Registrate");
    }


}
