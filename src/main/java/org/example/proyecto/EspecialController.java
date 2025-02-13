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

public class EspecialController {

    @FXML private TextField txtCantidadChampinones, txtCantidadBerenjenas, txtCantidadBoquerones, txtCantidadRabas;
    @FXML private Pane paneEspecial1, paneEspecial2, paneEspecial3;

    @FXML
    public void initialize() {
        paneEspecial1.setVisible(false);
        paneEspecial2.setVisible(false);
        paneEspecial3.setVisible(false);
        mensaje = new Properties();

    }

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

    public void abrirEspecial1(MouseEvent mouseEvent) { paneEspecial1.setVisible(true); }
    public void abrirEspecial2(MouseEvent mouseEvent) { paneEspecial2.setVisible(true); }
    public void abrirEspecial3(MouseEvent mouseEvent) { paneEspecial3.setVisible(true); }

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

    private void agregarEspecialSiExiste(TextField textField, String nombre, double precio) {
        if (textField != null && !textField.getText().isEmpty()) {
            int cantidad = Integer.parseInt(textField.getText());
            if (cantidad > 0) {
                CarritoModel.getInstance().agregarItem(nombre + " x" + cantidad, cantidad * precio);
            }
        }
    }


    @FXML
    private Label TituloEspeciales;
    @FXML
    private Label ChampinonesLabel;
    @FXML
    private Label BerenjenasLabel;
    @FXML
    private Label BoqueronesLabel;
    @FXML
    private Label RabasLabel;
    @FXML
    private Hyperlink BebidasLink;
    @FXML
    private Hyperlink TapasLink;
    @FXML
    private Hyperlink PrimerosLink;
    @FXML
    private Hyperlink PostresLink;
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