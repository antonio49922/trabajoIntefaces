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
/**
 * Created by David and Antonio.
 *
 * @author David
 */
public class PostreController {

    @FXML private TextField txtCantidadTiramisu, txtCantidadFlan, txtCantidadCrepe, txtCantidadArroz;

    @FXML
    private void incrementarCantidad(ActionEvent event) {
        Button btn = (Button) event.getSource();
        switch (btn.getId()) {
            case "btnMasTiramisu" -> txtCantidadTiramisu.setText(aumentarCantidad(txtCantidadTiramisu));
            case "btnMasFlan" -> txtCantidadFlan.setText(aumentarCantidad(txtCantidadFlan));
            case "btnMasCrepe" -> txtCantidadCrepe.setText(aumentarCantidad(txtCantidadCrepe));
            case "btnMasArroz" -> txtCantidadArroz.setText(aumentarCantidad(txtCantidadArroz));
        }
    }

    @FXML
    private void decrementarCantidad(ActionEvent event) {
        Button btn = (Button) event.getSource();
        switch (btn.getId()) {
            case "btnMenosTiramisu" -> txtCantidadTiramisu.setText(disminuirCantidad(txtCantidadTiramisu));
            case "btnMenosFlan" -> txtCantidadFlan.setText(disminuirCantidad(txtCantidadFlan));
            case "btnMenosCrepe" -> txtCantidadCrepe.setText(disminuirCantidad(txtCantidadCrepe));
            case "btnMenosArroz" -> txtCantidadArroz.setText(disminuirCantidad(txtCantidadArroz));
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
    @FXML private void cambiarATapas(ActionEvent event) throws IOException { cambiarEscena(event, "Tapas.fxml"); }
    @FXML private void cambiarABebidas(ActionEvent event) throws IOException { cambiarEscena(event, "Bebidas.fxml"); }
    @FXML private void cambiarAEspeciales(ActionEvent event) throws IOException { cambiarEscena(event, "especial.fxml"); }


    private void cambiarEscena(ActionEvent event, String fxml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/proyecto/" + fxml));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
