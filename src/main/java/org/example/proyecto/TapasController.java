package org.example.proyecto;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

public class TapasController {

    @FXML private TextField txtCantidadPatatas, txtCantidadCroquetas, txtCantidadMontaditos, txtCantidadBunuelos;

    @FXML
    private void incrementarCantidad(ActionEvent event) {
        Button btn = (Button) event.getSource();
        switch (btn.getId()) {
            case "btnMasPatatas" -> txtCantidadPatatas.setText(aumentarCantidad(txtCantidadPatatas));
            case "btnMasCroquetas" -> txtCantidadCroquetas.setText(aumentarCantidad(txtCantidadCroquetas));
            case "btnMasMontaditos" -> txtCantidadMontaditos.setText(aumentarCantidad(txtCantidadMontaditos));
            case "btnMasBunuelos" -> txtCantidadBunuelos.setText(aumentarCantidad(txtCantidadBunuelos));
        }
    }

    @FXML
    private void decrementarCantidad(ActionEvent event) {
        Button btn = (Button) event.getSource();
        switch (btn.getId()) {
            case "btnMenosPatatas" -> txtCantidadPatatas.setText(disminuirCantidad(txtCantidadPatatas));
            case "btnMenosCroquetas" -> txtCantidadCroquetas.setText(disminuirCantidad(txtCantidadCroquetas));
            case "btnMenosMontaditos" -> txtCantidadMontaditos.setText(disminuirCantidad(txtCantidadMontaditos));
            case "btnMenosBunuelos" -> txtCantidadBunuelos.setText(disminuirCantidad(txtCantidadBunuelos));
        }
    }

    private String aumentarCantidad(TextField textField) {
        return String.valueOf(Integer.parseInt(textField.getText()) + 1);
    }

    private String disminuirCantidad(TextField textField) {
        int cantidad = Integer.parseInt(textField.getText());
        return cantidad > 0 ? String.valueOf(cantidad - 1) : "0";
    }

    @FXML private void volverMenu(ActionEvent event) throws IOException { cambiarEscena(event, "Menu.fxml"); }
    @FXML private void cambiarAPrimeros(ActionEvent event) throws IOException { cambiarEscena(event, "Primero.fxml"); }
    @FXML private void cambiarASegundos(ActionEvent event) throws IOException { cambiarEscena(event, "Segundo.fxml"); }
    @FXML private void cambiarAPostres(ActionEvent event) throws IOException { cambiarEscena(event, "Postre.fxml"); }
    @FXML private void cambiarABebidas(ActionEvent event) throws IOException { cambiarEscena(event, "Bebidas.fxml"); }

    private void cambiarEscena(ActionEvent event, String fxml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/proyecto/" + fxml));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
