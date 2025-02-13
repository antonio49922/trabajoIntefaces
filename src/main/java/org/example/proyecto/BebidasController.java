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
 * @author Antonio-- Alberto
 * @vesion 1.0
 * @since 10/02/2025
 **/

/**
 *Controlador para la interfaz de seleccion de bebidas en la aplicacion
 * se permite incrementar y decrementar la cantidad de bebidas, cambiar de menu
 * y añadir nuevos elementos al carrito
 **/
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

    /**
     * Incrementa la cantidad de bebidas
     * segun el boton presionado
     * @param event Evento que se genera al precionar el boton
     */
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

    /**
     * Decrementa la cantidad de bebidas
     * segun el boton precionado
     * @param event Evento que se genera al precionar el boton
     */
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

    /**
     * Aumento en uno la cantidad de bebida que se asocia a un textfield
     *
     * @param textField Campo de texto con la cantidad actual
     * @return Nueva cantidad
     */

    private String aumentarCantidad(TextField textField) {
        if (textField == null || textField.getText().isEmpty()) return "1";
        return String.valueOf(Integer.parseInt(textField.getText()) + 1);
    }

    /**
     * Disminuye en uno la cantidad de bebida asociada a un textfield
     *
     * @param textField Campo de texto con la cantidad actual de bebida
     *
     * @return nueva cantidad, no puede ser inferior a 0
     */

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


    /**
     * Cambiar la escena
     * @param event Evento de accion
     * @param fxml Nombre del archivo fxml
     * @throws IOException Si ocurre un error al cargar la escena
     */

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

    /**
     * Añade bebidas seleccionadas al carrito si la cantidasd es mayor a 0
     *
     * @param event Evento de accion del boton añadir
     * @throws IOException Si ocurre un error al cambiar a la pantalla del carrito
     */
    @FXML
    private void añadirAlCarrito(ActionEvent event) throws IOException {
        CarritoModel carrito = CarritoModel.getInstance();

        agregarBebidaSiExiste(txtCantidadCesar, "Mojito", 5.00);
        agregarBebidaSiExiste(txtCantidadCesar1, "Daikiri Bosque", 8.00);
        agregarBebidaSiExiste(txtCantidadMixta, "Cosmopolitan", 7.00);
        agregarBebidaSiExiste(txtCantidadMixta1, "Long Island Ice Tea", 12.00);
        agregarBebidaSiExiste(txtCantidadMixta2, "Gin Fizz", 10.00);
        agregarBebidaSiExiste(txtCantidadMixta11, "Leche de Bantha", 6.00);
        agregarBebidaSiExiste(txtCantidadMixta21, "Tequila Sunrise", 7.00);
        agregarBebidaSiExiste(txtCantidadMixta111, "Margarita", 10.00);

        System.out.println("Productos añadidos al carrito.");

        // Cambiar a la pantalla del carrito
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/proyecto/Carrito.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Agrega una bebida al carrito si su cantidad es mayor que 0
     *
     * @param textField campo de texto con la cantidad
     * @param nombre Nombre de la bebida
     * @param precio PRecio de la bebida
     */

    private void agregarBebidaSiExiste(TextField textField, String nombre, double precio) {
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