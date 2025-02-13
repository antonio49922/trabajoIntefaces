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
/**
 * @author David-Alberto
 * @vesion 1.0
        * @since 10/02/2025
        **/

/**
 * Controlador para gestionar la interfaz de la seccion de especiales
 * Incrementa y decrementa cantidades de productos, agregar elementos al carrito
 * y cambiar entre diferentes escenas de la aplicacion
 *
 */
public class EspecialController {

    @FXML private TextField txtCantidadChampinones, txtCantidadBerenjenas, txtCantidadBoquerones, txtCantidadRabas;
    @FXML private Pane paneEspecial1, paneEspecial2, paneEspecial3;

    @FXML
    /**
     * Inicializa la vista
     */
    public void initialize() {
        paneEspecial1.setVisible(false);
        paneEspecial2.setVisible(false);
        paneEspecial3.setVisible(false);
    }

    /**
     * Incrementa la cantidad de productos
     * @param event accion del boton
     */
    @FXML

    private void incrementarCantidad(ActionEvent event) {
        Button btn = (Button) event.getSource();
        switch (btn.getId()) {
            case "btnMasChampinones" -> txtCantidadChampinones.setText(aumentarCantidad(txtCantidadChampinones));
            case "btnMasBerenjenas" -> txtCantidadBerenjenas.setText(aumentarCantidad(txtCantidadBerenjenas));
            case "btnMasBoquerones" -> txtCantidadBoquerones.setText(aumentarCantidad(txtCantidadBoquerones));
            case "btnMasRabas" -> txtCantidadRabas.setText(aumentarCantidad(txtCantidadRabas));
        }
    }

    /**
     * Decrementa la cantidad de productos
     * @param event Accion del boton
     */

    @FXML
    private void decrementarCantidad(ActionEvent event) {
        Button btn = (Button) event.getSource();
        switch (btn.getId()) {
            case "btnMenosChampinones" -> txtCantidadChampinones.setText(disminuirCantidad(txtCantidadChampinones));
            case "btnMenosBerenjenas" -> txtCantidadBerenjenas.setText(disminuirCantidad(txtCantidadBerenjenas));
            case "btnMenosBoquerones" -> txtCantidadBoquerones.setText(disminuirCantidad(txtCantidadBoquerones));
            case "btnMenosRabas" -> txtCantidadRabas.setText(disminuirCantidad(txtCantidadRabas));
        }
    }

    /**
     * Aumenta la cantidad en uno, o lo inicializa en 1 si esta vacio
     *
     * @param textField campo de texto del producto
     * @return Nueva cantidad en formato String
     */

    private String aumentarCantidad(TextField textField) {
        if (textField == null || textField.getText().isEmpty()) return "1";
        return String.valueOf(Integer.parseInt(textField.getText()) + 1);
    }

    /**
     * Disminuye en uno la cantidad de un producto, asegurado que no sea menor a 0
     *
     * @param textField campo de texto del producto
     * @return nueva cantidad en formato String
     */
    private String disminuirCantidad(TextField textField) {
        if (textField == null || textField.getText().isEmpty()) return "0";
        int cantidad = Integer.parseInt(textField.getText());
        return cantidad > 0 ? String.valueOf(cantidad - 1) : "0";
    }

    /**
     * Cambios entre escena de la aplicaicon
     * @param event Evento de accion
     * @throws IOException por ocurre  un error al cambiar de escena
     */
    @FXML private void volverMenu(ActionEvent event) throws IOException { cambiarEscena(event, "Menu.fxml"); }
    @FXML private void cambiarAPrimeros(ActionEvent event) throws IOException { cambiarEscena(event, "Primero.fxml"); }
    @FXML private void cambiarASegundos(ActionEvent event) throws IOException { cambiarEscena(event, "Segundo.fxml"); }
    @FXML private void cambiarAPostres(ActionEvent event) throws IOException { cambiarEscena(event, "Postre.fxml"); }
    @FXML private void cambiarATapas(ActionEvent event) throws IOException { cambiarEscena(event, "Tapas.fxml"); }
    @FXML private void cambiarABebidas(ActionEvent event) throws IOException { cambiarEscena(event, "Bebidas.fxml"); }
    @FXML private void cambiarAEspeciales(ActionEvent event) throws IOException { cambiarEscena(event, "especial.fxml"); }

    /**
     * Cambia a la escena seleccionada
     * @param event Evento de accion
     * @param fxml Archivo FXML del destino
     * @throws IOException Si ocurre un error al cargar la escena
     */
    private void cambiarEscena(ActionEvent event, String fxml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/proyecto/" + fxml));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void abrirEspecial1(MouseEvent mouseEvent) { paneEspecial1.setVisible(true); }
    public void abrirEspecial2(MouseEvent mouseEvent) { paneEspecial2.setVisible(true); }
    public void abrirEspecial3(MouseEvent mouseEvent) { paneEspecial3.setVisible(true); }

    /**
     * Agrega los productos seleccionados al carrito y cambia a la pantalla del carrito
     * @param event Evento de accion
     * @throws IOException Si ocurre un error al cargar la escena
     */
    @FXML
    private void añadirAlCarrito(ActionEvent event) throws IOException {
        CarritoModel carrito = CarritoModel.getInstance();

        agregarEspecialSiExiste(txtCantidadChampinones, "Champiñones al ajillo", 6.00);
        agregarEspecialSiExiste(txtCantidadBerenjenas, "Berenjenas fritas con miel", 7.00);
        agregarEspecialSiExiste(txtCantidadBoquerones, "Boquerones en vinagre", 7.50);
        agregarEspecialSiExiste(txtCantidadRabas, "Rabas de calamar", 8.50);

        System.out.println("Productos añadidos al carrito.");

        // Cambiar a la pantalla del carrito
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/proyecto/Carrito.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Agrega un producto al carrito si su cantidad es mayor que cero
     * @param textField Campo de texto que contiene la cantidad del producto
     * @param nombre nombre del producto
     * @param precio precio del producto
     */

    private void agregarEspecialSiExiste(TextField textField, String nombre, double precio) {
        if (textField != null && !textField.getText().isEmpty()) {
            int cantidad = Integer.parseInt(textField.getText());
            if (cantidad > 0) {
                CarritoModel.getInstance().agregarItem(nombre + " x" + cantidad, cantidad * precio);
            }
        }
    }

    /**
     * Permite al usuario seguir comprando volviendo al menu principal
     * @param event evento de accion
     * @throws IOException si ocurre un error al cambiar de escena
     */

    @FXML
    private void seguirComprando(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/proyecto/Menu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}