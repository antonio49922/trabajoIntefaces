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

public class PrimeroController {

    @FXML private TextField txtCantidadCesar, txtCantidadMixta, txtCantidadWrap, txtCantidadSandwich;
    @FXML private Pane panePlato1, panePlato2, panePlato3;

    @FXML
    public void initialize() {
        panePlato1.setVisible(false);
        panePlato2.setVisible(false);
        panePlato3.setVisible(false);
    }

    @FXML
    private void incrementarCantidad(ActionEvent event) {
        Button btn = (Button) event.getSource();
        switch (btn.getId()) {
            case "btnMasCesar" -> txtCantidadCesar.setText(aumentarCantidad(txtCantidadCesar));
            case "btnMasMixta" -> txtCantidadMixta.setText(aumentarCantidad(txtCantidadMixta));
            case "btnMasWrap" -> txtCantidadWrap.setText(aumentarCantidad(txtCantidadWrap));
            case "btnMasSandwich" -> txtCantidadSandwich.setText(aumentarCantidad(txtCantidadSandwich));
        }
    }

    @FXML
    private void decrementarCantidad(ActionEvent event) {
        Button btn = (Button) event.getSource();
        switch (btn.getId()) {
            case "btnMenosCesar" -> txtCantidadCesar.setText(disminuirCantidad(txtCantidadCesar));
            case "btnMenosMixta" -> txtCantidadMixta.setText(disminuirCantidad(txtCantidadMixta));
            case "btnMenosWrap" -> txtCantidadWrap.setText(disminuirCantidad(txtCantidadWrap));
            case "btnMenosSandwich" -> txtCantidadSandwich.setText(disminuirCantidad(txtCantidadSandwich));
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

    public void abrirPlato1(MouseEvent mouseEvent) { panePlato1.setVisible(true); }
    public void abrirPlato2(MouseEvent mouseEvent) { panePlato2.setVisible(true); }
    public void abrirPlato3(MouseEvent mouseEvent) { panePlato3.setVisible(true); }

    @FXML
    private void añadirAlCarrito(ActionEvent event) throws IOException {
        CarritoModel carrito = CarritoModel.getInstance();

        agregarPlatoSiExiste(txtCantidadCesar, "Ensalada César", 4.00);
        agregarPlatoSiExiste(txtCantidadMixta, "Ensalada Mixta", 4.00);
        agregarPlatoSiExiste(txtCantidadWrap, "Wrap de Pollo", 4.00);
        agregarPlatoSiExiste(txtCantidadSandwich, "Sándwich Club", 4.00);

        System.out.println("Productos añadidos al carrito.");

        // Cambiar a la pantalla del carrito
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/proyecto/Carrito.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void agregarPlatoSiExiste(TextField textField, String nombre, double precio) {
        if (textField != null && !textField.getText().isEmpty()) {
            int cantidad = Integer.parseInt(textField.getText());
            if (cantidad > 0) {
                CarritoModel.getInstance().agregarItem(nombre + " x" + cantidad, cantidad * precio);
            }
        }
    }

    @FXML
    private void seguirComprando(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/proyecto/Menu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}