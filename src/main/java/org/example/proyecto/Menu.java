package org.example.proyecto;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;
import javafx.scene.control.*;

/**
 * Clase en la cual se gestiona el menú principal de la aplicación
 */
public class Menu {

    @FXML
    private ImageView img;

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
    private String nombreUsuario;
    private String apellidoUsuario;
    private String selectedLanguage = "espanol_es.properties"; // Default language

    @FXML
    private void initialize() {
        mensaje = new Properties();
        loadLanguage(selectedLanguage); // Cargar idioma por defecto
        updateTexts();
    }

    /**
     * Cambia a la opción de bebidas al pulsar encima del nombre
     */
    @FXML
    public void bebidas_clic(MouseEvent event) throws IOException {
        cambiarPantalla(event, "bebidas.fxml", "Bebidas");
    }

    /**
     * Cambia a la opción de tapas al pulsar encima del nombre
     */
    @FXML
    public void tapas_clic(MouseEvent event) throws IOException {
        cambiarPantalla(event, "Tapas.fxml", "Tapas");
    }

    /**
     * Cambia a la opción de primeros al pulsar encima del nombre
     */
    @FXML
    public void primero_clic(MouseEvent event) throws IOException {
        cambiarPantalla(event, "Primero.fxml", "Primeros");
    }

    /**
     * Cambia a la opción de segundos al pulsar encima del nombre
     */
    @FXML
    public void segundo_clic(MouseEvent event) throws IOException {
        cambiarPantalla(event, "Segundo.fxml", "Segundos");
    }

    /**
     * Cambia a la opción de postres al pulsar encima del nombre
     */
    @FXML
    public void postres_clic(MouseEvent event) throws IOException {
        cambiarPantalla(event, "postre.fxml", "Postres");
    }

    /**
     * Cambia a la opción de especiales al pulsar encima del nombre
     */
    @FXML
    public void especial_clic(MouseEvent event) throws IOException {
        cambiarPantalla(event, "especial.fxml", "Especiales");
    }

    /**
     * Método para cambiar de pantalla
     */
    private void cambiarPantalla(MouseEvent event, String fxml, String titulo) throws IOException {
        Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = loader.load();

        // Obtener el controlador de la nueva pantalla
        Object controller = loader.getController();

        // Usar reflexión para llamar a setLanguage si existe
        try {
            Method setLanguageMethod = controller.getClass().getMethod("setLanguage", String.class);
            setLanguageMethod.invoke(controller, selectedLanguage);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            // El método no existe o no se pudo invocar, manejar el error si es necesario
        }

        // Usar reflexión para llamar a setWelcomeMessage si existe
        try {
            Method setWelcomeMessageMethod = controller.getClass().getMethod("setWelcomeMessage", String.class, String.class);
            setWelcomeMessageMethod.invoke(controller, nombreUsuario, apellidoUsuario);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            // El método no existe o no se pudo invocar, manejar el error si es necesario
        }

        Stage newStage = new Stage();
        newStage.setTitle(titulo);
        newStage.setScene(new Scene(root));
        newStage.show();
        currentStage.close();
    }

    /**
     * Muestra un mensaje de bienvenida al entrar
     */
    public void setWelcomeMessage(String nombre, String apellido) {
        this.nombreUsuario = nombre;
        this.apellidoUsuario = apellido;
        welcomeLabel.setText(mensaje.getProperty("label.welcome", "¡Bienvenido!") + " " + nombre + " " + apellido);
    }

    /**
     * Cambia el idioma a inglés
     */
    @FXML
    private void cambiarAIngles() {
        setLanguage("ingles_en.properties");
    }

    /**
     * Cambia el idioma a español
     */
    @FXML
    private void cambiarAEspanol() {
        setLanguage("espanol_es.properties");
    }

    /**
     * Establece el idioma
     */
    public void setLanguage(String lang) {
        this.selectedLanguage = lang;
        loadLanguage(lang);
        updateTexts();
        setWelcomeMessage(nombreUsuario, apellidoUsuario);
    }

    /**
     * Carga el archivo de idioma
     */
    private void loadLanguage(String lang) {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(lang)) {
            if (input == null) {
                System.out.println("No se pudo encontrar el archivo: " + lang);
                return;
            }
            mensaje.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Actualiza los textos de la interfaz con el idioma seleccionado
     */
    private void updateTexts() {
        welcomeLabel.setText(mensaje.getProperty("label.welcome", "¡Hola!"));
        bebidasLabel.setText(mensaje.getProperty("label.bebidas", "Bebidas"));
        tapasLabel.setText(mensaje.getProperty("label.tapas", "Tapas"));
        primerosLabel.setText(mensaje.getProperty("label.primeros", "Primer Plato"));
        segundosLabel.setText(mensaje.getProperty("label.segundos", "Segundo Plato"));
        postresLabel.setText(mensaje.getProperty("label.postres", "Postres"));
        especialesLabel.setText(mensaje.getProperty("label.especiales", "Especial de la casa"));
    }
}