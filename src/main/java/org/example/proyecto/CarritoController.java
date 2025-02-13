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
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import javafx.event.ActionEvent;

/**
 * @author Alberto
 * @vesion 1.0
 * @since 10/02/2025
 **/

/**
 * Comtrolador para gestionar el resumen del pedido y confirmar comprar
 */
public class CarritoController {

    @FXML
    private VBox resumenPedido;

    @FXML
    private Label totalPrecio;

    @FXML
    private Button confirmarPedido;

    /**
     * Inicializacion del controlador y actualizacion del pedido
     */
    @FXML
    public void initialize() {
        actualizarResumen();
        mensaje = new Properties();
    }

    /**
     * ACtualiza la vista con los elementos del carrito y mueestra el total del pedido
     */

    private void actualizarResumen() {
        CarritoModel carrito = CarritoModel.getInstance();
        resumenPedido.getChildren().clear();

        for (Map.Entry<String, Integer> item : carrito.getItems().entrySet()) {
            double precioUnitario = carrito.getPrecioUnitario(item.getKey());
            resumenPedido.getChildren().add(new Label(item.getKey() + " x" + item.getValue() + " - " + precioUnitario + " €"));
        }

        totalPrecio.setText("Total: " + carrito.getTotalPrecio() + " €");
    }

    /**
     * Permite al usuario seguir comprando y vuelve al menu principal
     * @param event Evento de accion del boton
     * @throws IOException Si ocurre un error al cargar la nueva escena
     */

    @FXML
    private void seguirComprando(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/proyecto/Menu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Confirma el pedido, vacia el carrito y muestra una alerta de confirmacion
     */

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





    private Properties mensaje;

    @FXML
    private void cambiarAIngles() {
        String selectedLanguage = "ingles_en.properties";
        loadLanguage(selectedLanguage);
        updateTexts();
    }

    @FXML
    private void cambiarAEspanol() {
        String selectedLanguage = "espanol_es.properties";
        loadLanguage(selectedLanguage);
        updateTexts();
    }

    private void loadLanguage(String lang) {
        String fileName = lang;
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                System.out.println("Lo siento, no se pudo encontrar el archivo: " + fileName);
                return;
            }
            mensaje.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void updateTexts() {
        TituloEspeciales.setText(mensaje.getProperty("label.especiales2", "Especiales"));
        ChampinonesLabel.setText(mensaje.getProperty("label.champinones", "Champiñones al ajillo"));
        BerenjenasLabel.setText(mensaje.getProperty("label.berenjenas", "Berenjenas fritas con \nmiel"));
        BoqueronesLabel.setText(mensaje.getProperty("label.boquerones", "Boquerones en vinagre"));
        RabasLabel.setText(mensaje.getProperty("label.rabas", "Rabas de calamar"));
        BebidasLink.setText(mensaje.getProperty("label.bebidas", "Bebidas"));
        TapasLink.setText(mensaje.getProperty("label.tapas", "Tapas"));
        PrimerosLink.setText(mensaje.getProperty("label.primeros2", "Primeros"));
        SegundosLink.setText(mensaje.getProperty("label.segundos2", "Segundos"));
        PostresLink.setText(mensaje.getProperty("label.postres", "Postres"));
        btnAñadirCesta.setText(mensaje.getProperty("button.login", "A la Cesta"));
    }
}