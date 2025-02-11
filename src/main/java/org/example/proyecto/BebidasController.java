package org.example.proyecto;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

public class BebidasController {

    @FXML private TextField txtCantidadCesar, txtCantidadCesar1, txtCantidadMixta, txtCantidadMixta1, txtCantidadMixta2, txtCantidadMixta11, txtCantidadMixta21, txtCantidadMixta111;
    @FXML private Pane ron, vodka, gin, tequila;

    @FXML
    public void initialize() {
        ron.setVisible(false);
        vodka.setVisible(false);
        gin.setVisible(false);
        tequila.setVisible(false);
    }

    @FXML
    private void incrementarCantidad(ActionEvent event) {
        Button btn = (Button) event.getSource();
        switch (btn.getId()) {
            case "btnMasCesar" -> txtCantidadCesar.setText(aumentarCantidad(txtCantidadCesar));
            case "btnMasCesar1" -> txtCantidadCesar1.setText(aumentarCantidad(txtCantidadCesar1));
            case "btnMasMixta" -> txtCantidadMixta.setText(aumentarCantidad(txtCantidadMixta));
            case "btnMasMixta1" -> txtCantidadMixta1.setText(aumentarCantidad(txtCantidadMixta1));
            case "btnMasMixta2" -> txtCantidadMixta2.setText(aumentarCantidad(txtCantidadMixta2));
            case "btnMasMixta11" -> txtCantidadMixta11.setText(aumentarCantidad(txtCantidadMixta11));
            case "btnMasMixta21" -> txtCantidadMixta21.setText(aumentarCantidad(txtCantidadMixta21));
            case "btnMasMixta111" -> txtCantidadMixta111.setText(aumentarCantidad(txtCantidadMixta111));
        }
    }

    @FXML
    private void decrementarCantidad(ActionEvent event) {
        Button btn = (Button) event.getSource();
        switch (btn.getId()) {
            case "btnMenosCesar" -> txtCantidadCesar.setText(disminuirCantidad(txtCantidadCesar));
            case "btnMenosCesar1" -> txtCantidadCesar1.setText(disminuirCantidad(txtCantidadCesar1));
            case "btnMenosMixta" -> txtCantidadMixta.setText(disminuirCantidad(txtCantidadMixta));
            case "btnMenosMixta1" -> txtCantidadMixta1.setText(disminuirCantidad(txtCantidadMixta1));
            case "btnMenosMixta2" -> txtCantidadMixta2.setText(disminuirCantidad(txtCantidadMixta2));
            case "btnMenosMixta11" -> txtCantidadMixta11.setText(disminuirCantidad(txtCantidadMixta11));
            case "btnMenosMixta21" -> txtCantidadMixta21.setText(disminuirCantidad(txtCantidadMixta21));
            case "btnMenosMixta111" -> txtCantidadMixta111.setText(disminuirCantidad(txtCantidadMixta111));
        }
    }

    private String aumentarCantidad(TextField textField) {
        if (textField == null || textField.getText().isEmpty()) return "1";
        return String.valueOf(Integer.parseInt(textField.getText()) + 1);
    }

    private String disminuirCantidad(TextField textField) {
        if (textField == null || textField.getText().isEmpty()) return "0";
        int cantidad = Integer.parseInt(textField.getText());
        return cantidad > 0 ? String.valueOf(cantidad - 1) : "0";
    }

    @FXML private void volverMenu(ActionEvent event) throws IOException { cambiarEscena(event, "Menu.fxml"); }
    @FXML private void cambiarAPrimeros(ActionEvent event) throws IOException { cambiarEscena(event, "Primero.fxml"); }
    @FXML private void cambiarASegundos(ActionEvent event) throws IOException { cambiarEscena(event, "Segundo.fxml"); }
    @FXML private void cambiarAPostres(ActionEvent event) throws IOException { cambiarEscena(event, "Postre.fxml"); }
    @FXML private void cambiarATapas(ActionEvent event) throws IOException { cambiarEscena(event, "Tapas.fxml"); }
    @FXML private void cambiarABebidas(ActionEvent event) throws IOException { cambiarEscena(event, "Bebidas.fxml"); }
    @FXML private void cambiarAEspeciales(ActionEvent event) throws IOException { cambiarEscena(event, "especial.fxml"); }

    private void cambiarEscena(ActionEvent event, String fxml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/proyecto/" + fxml));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void abrirRones(MouseEvent mouseEvent) { ron.setVisible(true); }
    public void abrirVodkas(MouseEvent mouseEvent) { vodka.setVisible(true); }
    public void abrirGin(MouseEvent mouseEvent) { gin.setVisible(true); }
    public void abrirTequila(MouseEvent mouseEvent) { tequila.setVisible(true); }
}
