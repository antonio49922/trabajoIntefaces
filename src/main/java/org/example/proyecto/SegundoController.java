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
 * Created by David an Antonio.
 *
 * @author David
 */
public class SegundoController {

    @FXML private TextField txtCantidadEntrecot, txtCantidadSalmon, txtCantidadPollo;

    // Métodos para incrementar la cantidad de cada plato
    @FXML
    private void incrementarCantidad(ActionEvent event) {
        Button btn = (Button) event.getSource();
        switch (btn.getId()) {
            case "btnMasEntrecot" -> txtCantidadEntrecot.setText(aumentarCantidad(txtCantidadEntrecot));
            case "btnMasSalmon" -> txtCantidadSalmon.setText(aumentarCantidad(txtCantidadSalmon));
            case "btnMasPollo" -> txtCantidadPollo.setText(aumentarCantidad(txtCantidadPollo));
        }
    }

    // Métodos para disminuir la cantidad de cada plato
    @FXML
    private void decrementarCantidad(ActionEvent event) {
        Button btn = (Button) event.getSource();
        switch (btn.getId()) {
            case "btnMenosEntrecot" -> txtCantidadEntrecot.setText(disminuirCantidad(txtCantidadEntrecot));
            case "btnMenosSalmon" -> txtCantidadSalmon.setText(disminuirCantidad(txtCantidadSalmon));
            case "btnMenosPollo" -> txtCantidadPollo.setText(disminuirCantidad(txtCantidadPollo));
        }
    }

    // Métodos auxiliares para manejar cantidades
    private String aumentarCantidad(TextField textField) {
        return String.valueOf(Integer.parseInt(textField.getText()) + 1);
    }

    private String disminuirCantidad(TextField textField) {
        int cantidad = Integer.parseInt(textField.getText());
        return cantidad > 0 ? String.valueOf(cantidad - 1) : "0";
    }

    // Métodos para cambiar entre las categorías del menú
    @FXML private void volverMenu(ActionEvent event) throws IOException { cambiarEscena(event, "Menu.fxml"); }
    @FXML private void cambiarAPrimeros(ActionEvent event) throws IOException { cambiarEscena(event, "Primero.fxml"); }
    @FXML private void cambiarASegundos(ActionEvent event) throws IOException { cambiarEscena(event, "Segundo.fxml"); }
    @FXML private void cambiarAPostres(ActionEvent event) throws IOException { cambiarEscena(event, "Postre.fxml"); }
    @FXML private void cambiarATapas(ActionEvent event) throws IOException { cambiarEscena(event, "Tapas.fxml"); }
    @FXML private void cambiarABebidas(ActionEvent event) throws IOException { cambiarEscena(event, "Bebidas.fxml"); }
    @FXML private void cambiarAEspeciales(ActionEvent event) throws IOException { cambiarEscena(event, "especial.fxml"); }

    // Método auxiliar para cambiar de escena
    private void cambiarEscena(ActionEvent event, String fxml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/proyecto/" + fxml));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }


}
