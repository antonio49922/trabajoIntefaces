package org.example.proyecto;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javafx.scene.control.*;


/**
 * Clase en la cual se gestionan el menu principal de la aplicacion
 */
public class Menu {

    @FXML
    private ImageView img;

    /**
     * Cambia a la opcion de bebidas al pulsar encima de el nombre
     * @param event evento al pulsar por encima del nombre
     * @throws IOException si ocurre algun error al momento de cargar la escena
     */
    @FXML
    public void bebidas_clic(MouseEvent event) throws IOException {
        Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        CambioPantalla.switchScene(currentStage, "bebidas.fxml", "Bebidas");
    }
    /**
     * Cambia a la opcion de tapas al pulsar encima de el nombre
     * @param event evento al pulsar por encima del nombre
     * @throws IOException si ocurre algun error al momento de cargar la escena
     */
    @FXML
    public void tapas_clic(MouseEvent event) throws IOException {
        Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        CambioPantalla.switchScene(currentStage, "Tapas.fxml", "Registrate");
    }
    /**
     * Cambia a la opcion de primeros al pulsar encima de el nombre
     * @param event evento al pulsar por encima del nombre
     * @throws IOException si ocurre algun error al momento de cargar la escena
     */
    @FXML
    public void primero_clic(MouseEvent event) throws IOException {
        Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        CambioPantalla.switchScene(currentStage, "Primero.fxml", "Registrate");
    }
    /**
     * Cambia a la opcion de segundos al pulsar encima de el nombre
     * @param event evento al pulsar por encima del nombre
     * @throws IOException si ocurre algun error al momento de cargar la escena
     */
    @FXML
    public void segundo_clic(MouseEvent event) throws IOException {
        Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        CambioPantalla.switchScene(currentStage, "Segundo.fxml", "Registrate");
    }
    /**
     * Cambia a la opcion de postres al pulsar encima de el nombre
     * @param event evento al pulsar por encima del nombre
     * @throws IOException si ocurre algun error al momento de cargar la escena
     */
    @FXML
    public void postres_clic(MouseEvent event) throws IOException {
        Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        CambioPantalla.switchScene(currentStage, "postre.fxml", "Registrate");
    }
    /**
     * Cambia a la opcion de especiales al pulsar encima de el nombre
     * @param event evento al pulsar por encima del nombre
     * @throws IOException si ocurre algun error al momento de cargar la escena
     */
    @FXML
    public void especial_clic(MouseEvent event) throws IOException {
        Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        CambioPantalla.switchScene(currentStage, "especial.fxml", "Registrate");
    }

    /**
     * Muestra un mensaje de bienvenido al entrar
     * @param nombre nombre de el cliente
     * @param apellido apellido de el cliente
     */
    @FXML
    private Label welcomeLabel;
    @FXML
    private Label bebidasLabel;
    @FXML
    private Label tapasLabel;
    @FXML
    private Label primerosLabel;
    @FXML
    private Label segundosLabel;
    @FXML
    private Label postresLabel;
    @FXML
    private Label especialesLabel;


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
        welcomeLabel.setText(mensaje.getProperty("label.welcome", "¡Hola!"));
        bebidasLabel.setText(mensaje.getProperty("label.bebidas", "Bebidas"));
        tapasLabel.setText(mensaje.getProperty("label.tapas", "Tapas"));
        primerosLabel.setText(mensaje.getProperty("label.primeros", "Primer Plato"));
        segundosLabel.setText(mensaje.getProperty("label.segundos", "Segundo Plato"));
        postresLabel.setText(mensaje.getProperty("label.postres", "Postres"));
        especialesLabel.setText(mensaje.getProperty("label.especiales", "Especial de la casa"));
    }

    public void setWelcomeMessage(String nombre, String apellido) {
        welcomeLabel.setText("¡Bienvenido, " + nombre + " " + apellido + "!");
    }
}