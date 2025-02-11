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

    @FXML private TextField txtCantidadClasica, txtCantidadDoble, txtCantidadMerluza, txtCantidadLomo;

    // Metodos para incrementar la cantidad de cada plato
    @FXML
    private void incrementarCantidad(ActionEvent event) {
        Button btn = (Button) event.getSource();
        switch (btn.getId()) {
            case "btnMasClasica" -> txtCantidadClasica.setText(aumentarCantidad(txtCantidadClasica));
            case "btnMasDoble" -> txtCantidadDoble.setText(aumentarCantidad(txtCantidadDoble));
            case "btnMasMerluza" -> txtCantidadMerluza.setText(aumentarCantidad(txtCantidadMerluza));
            case "btnMasLomo" -> txtCantidadLomo.setText(aumentarCantidad(txtCantidadLomo));
        }
    }

    // Metodos para disminuir la cantidad de cada plato
    @FXML
    private void decrementarCantidad(ActionEvent event) {
        Button btn = (Button) event.getSource();
        switch (btn.getId()) {
            case "btnMenosClasica" -> txtCantidadClasica.setText(disminuirCantidad(txtCantidadClasica));
            case "btnMenosDoble" -> txtCantidadDoble.setText(disminuirCantidad(txtCantidadDoble));
            case "btnMenosMerluza" -> txtCantidadMerluza.setText(disminuirCantidad(txtCantidadMerluza));
            case "btnMenosLomo" -> txtCantidadLomo.setText(disminuirCantidad(txtCantidadLomo));
        }
    }

    // Metodos auxiliares para manejar cantidades
    private String aumentarCantidad(TextField textField) {
        String text = textField.getText().trim();
        int cantidad = text.isEmpty() ? 0 : Integer.parseInt(text);
        return String.valueOf(cantidad + 1);
    }

    private String disminuirCantidad(TextField textField) {
        String text = textField.getText().trim();
        int cantidad = text.isEmpty() ? 0 : Integer.parseInt(text);
        return cantidad > 0 ? String.valueOf(cantidad - 1) : "0";
    }

    // Metodos para cambiar entre las categorías del menu
    @FXML private void volverMenu(ActionEvent event) throws IOException { cambiarEscena(event, "Menu.fxml"); }
    @FXML private void cambiarAPrimeros(ActionEvent event) throws IOException { cambiarEscena(event, "Primero.fxml"); }
    @FXML private void cambiarASegundos(ActionEvent event) throws IOException { cambiarEscena(event, "Segundo.fxml"); }
    @FXML private void cambiarAPostres(ActionEvent event) throws IOException { cambiarEscena(event, "Postre.fxml"); }
    @FXML private void cambiarATapas(ActionEvent event) throws IOException { cambiarEscena(event, "Tapas.fxml"); }
    @FXML private void cambiarABebidas(ActionEvent event) throws IOException { cambiarEscena(event, "Bebidas.fxml"); }
    @FXML private void cambiarAEspeciales(ActionEvent event) throws IOException { cambiarEscena(event, "especial.fxml"); }

    // Metodo auxiliar para cambiar de escena
    private void cambiarEscena(ActionEvent event, String fxml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/proyecto/" + fxml));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
