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

    @FXML private TextField txtCantidadCesar, txtCantidadWrap, txtCantidadSandwich;
    @FXML private Pane ron;
    @FXML private Pane vodka;
    @FXML private Pane gin;
    @FXML private Pane tequila;


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
            case "btnMasWrap" -> txtCantidadWrap.setText(aumentarCantidad(txtCantidadWrap));
            case "btnMasSandwich" -> txtCantidadSandwich.setText(aumentarCantidad(txtCantidadSandwich));
        }
    }

    @FXML
    private void decrementarCantidad(ActionEvent event) {
        Button btn = (Button) event.getSource();
        switch (btn.getId()) {
            case "btnMenosCesar" -> txtCantidadCesar.setText(disminuirCantidad(txtCantidadCesar));
            case "btnMenosWrap" -> txtCantidadWrap.setText(disminuirCantidad(txtCantidadWrap));
            case "btnMenosSandwich" -> txtCantidadSandwich.setText(disminuirCantidad(txtCantidadSandwich));
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
    @FXML private void cambiarATapas(ActionEvent event) throws IOException { cambiarEscena(event, "Tapas.fxml"); }
    @FXML private void cambiarABebidas(ActionEvent event) throws IOException { cambiarEscena(event, "Bebidas.fxml"); }
    @FXML private void cambiarAEspeciales(ActionEvent event) throws IOException { cambiarEscena(event, "especial.fxml"); }

//    @FXML private void cambiarA(ActionEvent event) throws IOException { cambiarEscena(event, "Bebidas.fxml"); }

    private void cambiarEscena(ActionEvent event, String fxml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/proyecto/" + fxml));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void abrirRones(MouseEvent mouseEvent) {
        ron.setVisible(true);
    }

    public void abrirVodkas(MouseEvent mouseEvent) {
        vodka.setVisible(true);
    }
    public void abrirGin(MouseEvent mouseEvent) {
        gin.setVisible(true);
    }
    public void abrirTequila(MouseEvent mouseEvent){
        tequila.setVisible(true);
    }

}
