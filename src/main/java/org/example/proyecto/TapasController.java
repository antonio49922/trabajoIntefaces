package org.example.proyecto;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TapasController {

    @FXML private TextField txtCantidadPatatas, txtCantidadCroquetas, txtCantidadMontaditos, txtCantidadBunuelos;
    @FXML private Pane paneTapa1, paneTapa2, paneTapa3;

    @FXML
    public void initialize() {
        paneTapa1.setVisible(false);
        paneTapa2.setVisible(false);
        paneTapa3.setVisible(false);
        mensaje = new Properties();

    }

    @FXML
    private void incrementarCantidad(ActionEvent event) {
        Button btn = (Button) event.getSource();
        switch (btn.getId()) {
            case "btnMasPatatas" -> txtCantidadPatatas.setText(aumentarCantidad(txtCantidadPatatas));
            case "btnMasCroquetas" -> txtCantidadCroquetas.setText(aumentarCantidad(txtCantidadCroquetas));
            case "btnMasMontaditos" -> txtCantidadMontaditos.setText(aumentarCantidad(txtCantidadMontaditos));
            case "btnMasBunuelos" -> txtCantidadBunuelos.setText(aumentarCantidad(txtCantidadBunuelos));
        }
    }

    @FXML
    private void decrementarCantidad(ActionEvent event) {
        Button btn = (Button) event.getSource();
        switch (btn.getId()) {
            case "btnMenosPatatas" -> txtCantidadPatatas.setText(disminuirCantidad(txtCantidadPatatas));
            case "btnMenosCroquetas" -> txtCantidadCroquetas.setText(disminuirCantidad(txtCantidadCroquetas));
            case "btnMenosMontaditos" -> txtCantidadMontaditos.setText(disminuirCantidad(txtCantidadMontaditos));
            case "btnMenosBunuelos" -> txtCantidadBunuelos.setText(disminuirCantidad(txtCantidadBunuelos));
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

    public void abrirTapa1(MouseEvent mouseEvent) { paneTapa1.setVisible(true); }
    public void abrirTapa2(MouseEvent mouseEvent) { paneTapa2.setVisible(true); }
    public void abrirTapa3(MouseEvent mouseEvent) { paneTapa3.setVisible(true); }

    @FXML
    private void añadirAlCarrito(ActionEvent event) throws IOException {
        CarritoModel carrito = CarritoModel.getInstance();

        agregarTapaSiExiste(txtCantidadPatatas, "Patatas con Alioli", 4.00);
        agregarTapaSiExiste(txtCantidadCroquetas, "Croquetas de Jamón y Queso", 4.00);
        agregarTapaSiExiste(txtCantidadMontaditos, "Montaditos de Tortilla con Cebolla", 4.00);
        agregarTapaSiExiste(txtCantidadBunuelos, "Mini Buñuelos de Queso", 4.00);

        System.out.println("Productos añadidos al carrito.");

        // Cambiar a la pantalla del carrito
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/proyecto/Carrito.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void agregarTapaSiExiste(TextField textField, String nombre, double precio) {
        if (textField != null && !textField.getText().isEmpty()) {
            int cantidad = Integer.parseInt(textField.getText());
            if (cantidad > 0) {
                CarritoModel.getInstance().agregarItem(nombre + " x" + cantidad, cantidad * precio);
            }
        }
    }


    @FXML
    private Label TituloTapas;
    @FXML
    private Label PatatasLabel;
    @FXML
    private Label CroquetasLabel;
    @FXML
    private Label MontaditosLabel;
    @FXML
    private Label BunuelosLabel;
    @FXML
    private Hyperlink BebidasLink;
    @FXML
    private Hyperlink PostresLink;
    @FXML
    private Hyperlink PrimerosLink;
    @FXML
    private Hyperlink EspecialesLink;
    @FXML
    private Hyperlink SegundosLink;
    @FXML
    private Button btnAñadirCesta;


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
        TituloTapas.setText(mensaje.getProperty("label.tapas", "Tapas"));
        PatatasLabel.setText(mensaje.getProperty("label.patatas", "Patatas con Alioli"));
        CroquetasLabel.setText(mensaje.getProperty("label.croquetas", "Croquetas de Jamón \ny Queso"));
        MontaditosLabel.setText(mensaje.getProperty("label.montaditos", "Montaditos de Tortilla \ncon Cebolla"));
        BunuelosLabel.setText(mensaje.getProperty("label.bunuelos", "BunuelosLabel"));
        BebidasLink.setText(mensaje.getProperty("label.bebidas", "Bebidas"));
        PostresLink.setText(mensaje.getProperty("label.postres", "Postres"));
        PrimerosLink.setText(mensaje.getProperty("label.primeros2", "Primeros"));
        SegundosLink.setText(mensaje.getProperty("label.segundos2", "Segundos"));
        EspecialesLink.setText(mensaje.getProperty("label.especiales2", "Especiales"));
        btnAñadirCesta.setText(mensaje.getProperty("button.login", "A la Cesta"));
    }


}