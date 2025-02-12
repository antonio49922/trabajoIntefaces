package org.example.proyecto;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;
import javafx.event.ActionEvent;

public class CarritoController {

    @FXML
    private VBox resumenPedido;

    @FXML
    private Label totalPrecio;

    @FXML
    private Button confirmarPedido;

    @FXML
    public void initialize() {
        actualizarResumen();
    }

    private void actualizarResumen() {
        CarritoModel carrito = CarritoModel.getInstance();
        resumenPedido.getChildren().clear();

        for (Map.Entry<String, Integer> item : carrito.getItems().entrySet()) {
            double precioUnitario = carrito.getPrecioUnitario(item.getKey());
            resumenPedido.getChildren().add(new Label(item.getKey() + " x" + item.getValue() + " - " + precioUnitario + " €"));
        }

        totalPrecio.setText("Total: " + carrito.getTotalPrecio() + " €");
    }

    @FXML
    private void seguirComprando(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/proyecto/Menu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void confirmarPedido() {
        CarritoModel.getInstance().limpiarCarrito();
        actualizarResumen();

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Confirmación de Pedido");
        alert.setHeaderText(null);
        alert.setContentText("Pedido aceptado");
        alert.showAndWait();
    }
}