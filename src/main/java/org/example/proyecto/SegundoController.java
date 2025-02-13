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
import java.io.InputStream;
import java.util.Properties;
import javafx.scene.control.*;

public class SegundoController {

    @FXML private TextField txtCantidadFilete, txtCantidadPescado, txtCantidadPollo, txtCantidadHamburguesa;
    @FXML private Pane paneSegundo1, paneSegundo2, paneSegundo3;

    @FXML
    public void initialize() {
        paneSegundo1.setVisible(false);
        paneSegundo2.setVisible(false);
        paneSegundo3.setVisible(false);
        mensaje = new Properties();
    }

    @FXML
    private void incrementarCantidad(ActionEvent event) {
        Button btn = (Button) event.getSource();
        switch (btn.getId()) {
            case "btnMasFilete" -> txtCantidadFilete.setText(aumentarCantidad(txtCantidadFilete));
            case "btnMasPescado" -> txtCantidadPescado.setText(aumentarCantidad(txtCantidadPescado));
            case "btnMasPollo" -> txtCantidadPollo.setText(aumentarCantidad(txtCantidadPollo));
            case "btnMasHamburguesa" -> txtCantidadHamburguesa.setText(aumentarCantidad(txtCantidadHamburguesa));
        }
    }

    @FXML
    private void decrementarCantidad(ActionEvent event) {
        Button btn = (Button) event.getSource();
        switch (btn.getId()) {
            case "btnMenosFilete" -> txtCantidadFilete.setText(disminuirCantidad(txtCantidadFilete));
            case "btnMenosPescado" -> txtCantidadPescado.setText(disminuirCantidad(txtCantidadPescado));
            case "btnMenosPollo" -> txtCantidadPollo.setText(disminuirCantidad(txtCantidadPollo));
            case "btnMenosHamburguesa" -> txtCantidadHamburguesa.setText(disminuirCantidad(txtCantidadHamburguesa));
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

    public void abrirSegundo1(MouseEvent mouseEvent) { paneSegundo1.setVisible(true); }
    public void abrirSegundo2(MouseEvent mouseEvent) { paneSegundo2.setVisible(true); }
    public void abrirSegundo3(MouseEvent mouseEvent) { paneSegundo3.setVisible(true); }

    @FXML
    private void añadirAlCarrito(ActionEvent event) throws IOException {
        CarritoModel carrito = CarritoModel.getInstance();

        agregarSegundoSiExiste(txtCantidadFilete, "Filete de Ternera", 8.00);
        agregarSegundoSiExiste(txtCantidadPescado, "Pescado a la Plancha", 7.00);
        agregarSegundoSiExiste(txtCantidadPollo, "Pollo Asado", 6.00);
        agregarSegundoSiExiste(txtCantidadHamburguesa, "Hamburguesa Completa", 5.00);

        System.out.println("Productos añadidos al carrito.");

        // Cambiar a la pantalla del carrito
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/proyecto/Carrito.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void agregarSegundoSiExiste(TextField textField, String nombre, double precio) {
        if (textField != null && !textField.getText().isEmpty()) {
            int cantidad = Integer.parseInt(textField.getText());
            if (cantidad > 0) {
                CarritoModel.getInstance().agregarItem(nombre + " x" + cantidad, cantidad * precio);
            }
        }
    }

    @FXML
    private Label SegundosTitulo;
    @FXML
    private Label FileteLabel;
    @FXML
    private Label PescadoLabel;
    @FXML
    private Label PolloLabel;
    @FXML
    private Label HamburguesaLabel;
    @FXML
    private Hyperlink BebidasLink;
    @FXML
    private Hyperlink TapasLink;
    @FXML
    private Hyperlink PrimerosLink;
    @FXML
    private Hyperlink EspecialesLink;
    @FXML
    private Hyperlink PostresLink;
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
        SegundosTitulo.setText(mensaje.getProperty("label.segundos2", "Segundos"));
        FileteLabel.setText(mensaje.getProperty("label.filete", "Filete de Ternera"));
        PescadoLabel.setText(mensaje.getProperty("label.pescado", "Pescado a la Plancha"));
        PolloLabel.setText(mensaje.getProperty("label.pollo", "Pollo Asado"));
        HamburguesaLabel.setText(mensaje.getProperty("label.hamburguesa", "Hamburguesa Completa"));
        BebidasLink.setText(mensaje.getProperty("label.bebidas", "Bebidas"));
        TapasLink.setText(mensaje.getProperty("label.tapas", "Tapas"));
        PrimerosLink.setText(mensaje.getProperty("label.primeros2", "Primeros"));
        EspecialesLink.setText(mensaje.getProperty("label.especiales2", "Especiales"));
        PostresLink.setText(mensaje.getProperty("label.postres", "Postres"));
        btnAñadirCesta.setText(mensaje.getProperty("button.login", "A la Cesta"));
    }


}