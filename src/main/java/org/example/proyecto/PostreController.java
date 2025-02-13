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


public class PostreController {

    @FXML private TextField txtCantidadTarta, txtCantidadHelado, txtCantidadFlan, txtCantidadBrownie;
    @FXML private Pane panePostre1, panePostre2, panePostre3;

    @FXML
    public void initialize() {
        panePostre1.setVisible(false);
        panePostre2.setVisible(false);
        panePostre3.setVisible(false);
        mensaje = new Properties();
    }

    @FXML
    private void incrementarCantidad(ActionEvent event) {
        Button btn = (Button) event.getSource();
        switch (btn.getId()) {
            case "btnMasTarta" -> txtCantidadTarta.setText(aumentarCantidad(txtCantidadTarta));
            case "btnMasHelado" -> txtCantidadHelado.setText(aumentarCantidad(txtCantidadHelado));
            case "btnMasFlan" -> txtCantidadFlan.setText(aumentarCantidad(txtCantidadFlan));
            case "btnMasBrownie" -> txtCantidadBrownie.setText(aumentarCantidad(txtCantidadBrownie));
        }
    }

    @FXML
    private void decrementarCantidad(ActionEvent event) {
        Button btn = (Button) event.getSource();
        switch (btn.getId()) {
            case "btnMenosTarta" -> txtCantidadTarta.setText(disminuirCantidad(txtCantidadTarta));
            case "btnMenosHelado" -> txtCantidadHelado.setText(disminuirCantidad(txtCantidadHelado));
            case "btnMenosFlan" -> txtCantidadFlan.setText(disminuirCantidad(txtCantidadFlan));
            case "btnMenosBrownie" -> txtCantidadBrownie.setText(disminuirCantidad(txtCantidadBrownie));
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

    public void abrirPostre1(MouseEvent mouseEvent) { panePostre1.setVisible(true); }
    public void abrirPostre2(MouseEvent mouseEvent) { panePostre2.setVisible(true); }
    public void abrirPostre3(MouseEvent mouseEvent) { panePostre3.setVisible(true); }

    @FXML
    private void añadirAlCarrito(ActionEvent event) throws IOException {
        CarritoModel carrito = CarritoModel.getInstance();

        agregarPostreSiExiste(txtCantidadTarta, "Tarta de Queso", 3.00);
        agregarPostreSiExiste(txtCantidadHelado, "Helado de Vainilla", 2.50);
        agregarPostreSiExiste(txtCantidadFlan, "Flan de Huevo", 2.00);
        agregarPostreSiExiste(txtCantidadBrownie, "Brownie de Chocolate", 3.50);

        System.out.println("Productos añadidos al carrito.");

        // Cambiar a la pantalla del carrito
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/proyecto/Carrito.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void agregarPostreSiExiste(TextField textField, String nombre, double precio) {
        if (textField != null && !textField.getText().isEmpty()) {
            int cantidad = Integer.parseInt(textField.getText());
            if (cantidad > 0) {
                CarritoModel.getInstance().agregarItem(nombre + " x" + cantidad, cantidad * precio);
            }
        }
    }


    @FXML
    private Label TituloPostres;
    @FXML
    private Label TartaLabel;
    @FXML
    private Label HeladoLabel;
    @FXML
    private Label FlanLabel;
    @FXML
    private Label BrownieLabel;
    @FXML
    private Hyperlink BebidasLink;
    @FXML
    private Hyperlink TapasLink;
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
        TituloPostres.setText(mensaje.getProperty("label.postres", "Postres"));
        TartaLabel.setText(mensaje.getProperty("label.tarta", "Tarta de Queso"));
        HeladoLabel.setText(mensaje.getProperty("label.helado", "Helado de Vainilla"));
        FlanLabel.setText(mensaje.getProperty("label.flan", "Flan de Huevo"));
        BrownieLabel.setText(mensaje.getProperty("label.brownie", "Brownie de Chocolate"));
        BebidasLink.setText(mensaje.getProperty("label.bebidas", "Bebidas"));
        TapasLink.setText(mensaje.getProperty("label.tapas", "Tapas"));
        PrimerosLink.setText(mensaje.getProperty("label.primeros2", "Primeros"));
        SegundosLink.setText(mensaje.getProperty("label.segundos2", "Segundos"));
        EspecialesLink.setText(mensaje.getProperty("label.especiales2", "Especiales"));
        btnAñadirCesta.setText(mensaje.getProperty("button.login", "A la Cesta"));
    }




}