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
 * Controlador para gestionar el resumen del pedido y confirmar la compra.
 *
 * @author Alberto
 * @version 1.0
 * @since 10/02/2025
 */
public class CarritoController {

    @FXML
    private VBox resumenPedido;

    @FXML
    private Label totalPrecio;

    @FXML
    private Label usuarioLabel;  // Muestra el nombre del usuario

    @FXML
    private Button confirmarPedido;  // Botón para confirmar el pedido

    @FXML
    private Label TituloResumen;

    @FXML
    private Button btnSeguirComprando; // Se corrigió el tipo de dato a Button

    private Properties mensaje;

    /**
     * Inicialización del controlador y actualización del pedido.
     */
    @FXML
    public void initialize() {
        actualizarResumen();
        mostrarUsuario();
        mensaje = new Properties();
    }

    /**
     * Actualiza la vista con los elementos del carrito y muestra el total del pedido.
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
     * Muestra el nombre del usuario que ha iniciado sesión.
     */
    private void mostrarUsuario() {
        UsuarioModel usuario = UsuarioModel.getInstance();
        String nombreCompleto = usuario.getNombreCompleto();

        if (nombreCompleto == null || nombreCompleto.trim().isEmpty()) {
            usuarioLabel.setText("Usuario: No identificado");
        } else {
            usuarioLabel.setText("Usuario: " + nombreCompleto);
        }
    }

    /**
     * Permite al usuario seguir comprando y vuelve al menú principal.
     *
     * @param event Evento de acción del botón.
     * @throws IOException Si ocurre un error al cargar la nueva escena.
     */
    @FXML
    private void seguirComprando(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/proyecto/Menu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Confirma el pedido, vacía el carrito y muestra una alerta de confirmación.
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

    /**
     * Carga el idioma seleccionado desde el archivo de propiedades.
     *
     * @param lang Nombre del archivo de idioma.
     */
    private void loadLanguage(String lang) {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(lang)) {
            if (input == null) {
                System.out.println("Lo siento, no se pudo encontrar el archivo: " + lang);
                return;
            }
            mensaje.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Actualiza los textos de la interfaz con el idioma seleccionado.
     */
    private void updateTexts() {
        TituloResumen.setText(mensaje.getProperty("label.resumen", "Resumen del Pedido"));
        btnSeguirComprando.setText(mensaje.getProperty("label.seguirCompra", "Seguir Comprando"));
        confirmarPedido.setText(mensaje.getProperty("label.finCompra", "Confirmar Pedido"));
    }
}
