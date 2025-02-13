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

/**
 * Clase para Controlador para la interfaz grafica
 */
public class PrimeroController {

    @FXML private TextField txtCantidadCesar, txtCantidadMixta, txtCantidadWrap, txtCantidadSandwich;
    @FXML private Pane panePlato1, panePlato2, panePlato3;

    /**
     * Inicializa los componentes de la vista y los cierra al cambiarse
     */
    @FXML
    public void initialize() {
        panePlato1.setVisible(false);
        panePlato2.setVisible(false);
        panePlato3.setVisible(false);
        mensaje = new Properties();
    }

    /**
     * Aumenta la cantidad de los platos
     * @param event El evento que se genera al hacer click en el boton correspondiente
     */

    @FXML
    private void incrementarCantidad(ActionEvent event) {
        Button btn = (Button) event.getSource();
        switch (btn.getId()) {
            case "btnMasCesar" -> txtCantidadCesar.setText(aumentarCantidad(txtCantidadCesar));
            case "btnMasMixta" -> txtCantidadMixta.setText(aumentarCantidad(txtCantidadMixta));
            case "btnMasWrap" -> txtCantidadWrap.setText(aumentarCantidad(txtCantidadWrap));
            case "btnMasSandwich" -> txtCantidadSandwich.setText(aumentarCantidad(txtCantidadSandwich));
        }
    }

    /**
     * Decrementa la cantidad de los platos
     * @param event El evento que se genera al hacer click en el boton correspondiente
     */

    @FXML
    private void decrementarCantidad(ActionEvent event) {
        Button btn = (Button) event.getSource();
        switch (btn.getId()) {
            case "btnMenosCesar" -> txtCantidadCesar.setText(disminuirCantidad(txtCantidadCesar));
            case "btnMenosMixta" -> txtCantidadMixta.setText(disminuirCantidad(txtCantidadMixta));
            case "btnMenosWrap" -> txtCantidadWrap.setText(disminuirCantidad(txtCantidadWrap));
            case "btnMenosSandwich" -> txtCantidadSandwich.setText(disminuirCantidad(txtCantidadSandwich));
        }
    }

    /**
     * Incrementa en uno el valor mostrado en el textfield
     * @param textField el campo de texto que tiene la cantidad de platos
     * @return el textfield incrementado
     */
    private String aumentarCantidad(TextField textField) {
        if (textField == null || textField.getText().isEmpty()) return "1";
        return String.valueOf(Integer.parseInt(textField.getText()) + 1);
    }

    /**
     * Decrementa en uno el valor mostrado en el texfield
     * @param textField Campo de texto que tiene la cantidad de platos
     * @return El textfield decrementado en 1,  no puede ser inferior a 0
     */
    private String disminuirCantidad(TextField textField) {
        if (textField == null || textField.getText().isEmpty()) return "0";
        int cantidad = Integer.parseInt(textField.getText());
        return cantidad > 0 ? String.valueOf(cantidad - 1) : "0";
    }

    /**
     * Funciones para volver a las escenas anteriores
     * @param event Evento que se generara
     * @throws IOException si ocurre un error al cargar la escena
     */

    @FXML private void volverMenu(ActionEvent event) throws IOException { cambiarEscena(event, "Menu.fxml"); }
    @FXML private void cambiarAPrimeros(ActionEvent event) throws IOException { cambiarEscena(event, "Primero.fxml"); }
    @FXML private void cambiarASegundos(ActionEvent event) throws IOException { cambiarEscena(event, "Segundo.fxml"); }
    @FXML private void cambiarAPostres(ActionEvent event) throws IOException { cambiarEscena(event, "Postre.fxml"); }
    @FXML private void cambiarATapas(ActionEvent event) throws IOException { cambiarEscena(event, "Tapas.fxml"); }
    @FXML private void cambiarABebidas(ActionEvent event) throws IOException { cambiarEscena(event, "Bebidas.fxml"); }
    @FXML private void cambiarAEspeciales(ActionEvent event) throws IOException { cambiarEscena(event, "especial.fxml"); }

    /**
     * Cambia la vista actual de la aplicacion a una nueva escena
     * @param event Evento que desencadena la accion
     * @param fxml Es de donde se contiene la escena que se va a cargar
     * @throws IOException si ocurre algun error al cargar la escena
     */
    private void cambiarEscena(ActionEvent event, String fxml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/proyecto/" + fxml));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void abrirPlato1(MouseEvent mouseEvent) { panePlato1.setVisible(true); }
    public void abrirPlato2(MouseEvent mouseEvent) { panePlato2.setVisible(true); }
    public void abrirPlato3(MouseEvent mouseEvent) { panePlato3.setVisible(true); }

    /**
     * Añade productos al carrito dependiendo de cual sea la eleccion del cliente
     * @param event Evento que desencadena una accion
     * @throws IOException si al ejecutarse la escena salta un error
     */
    @FXML
    private void añadirAlCarrito(ActionEvent event) throws IOException {
        CarritoModel carrito = CarritoModel.getInstance();

        agregarPlatoSiExiste(txtCantidadCesar, "Ensalada César", 8.50);
        agregarPlatoSiExiste(txtCantidadMixta, "Ensalada Mixta", 7.50);
        agregarPlatoSiExiste(txtCantidadWrap, "Wrap de Pollo", 9.00);
        agregarPlatoSiExiste(txtCantidadSandwich, "Sándwich Club", 9.50);

        System.out.println("Productos añadidos al carrito.");

        // Cambiar a la pantalla del carrito
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/proyecto/Carrito.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    /**
     * si existe el primero añade una nueva cantidad
     * @param textField cantidad de el postre
     * @param nombre nombre de el postre
     * @param precio precio de el postre
     */

    private void agregarPlatoSiExiste(TextField textField, String nombre, double precio) {
        if (textField != null && !textField.getText().isEmpty()) {
            int cantidad = Integer.parseInt(textField.getText());
            if (cantidad > 0) {
                CarritoModel.getInstance().agregarItem(nombre + " x" + cantidad, cantidad * precio);
            }
        }
    }

    @FXML
    private Label TituloPrimeros;
    @FXML
    private Label CesarLabel;
    @FXML
    private Label MixtaLabel;
    @FXML
    private Label WrapLabel;
    @FXML
    private Label SandwichLabel;
    @FXML
    private Hyperlink BebidasLink;
    @FXML
    private Hyperlink TapasLink;
    @FXML
    private Hyperlink SegundosLink;
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
        TituloPrimeros.setText(mensaje.getProperty("label.segundos2", "Primeros"));
        CesarLabel.setText(mensaje.getProperty("label.cesar", "Ensalada César"));
        MixtaLabel.setText(mensaje.getProperty("label.mixta", "Ensalada Mixta"));
        WrapLabel.setText(mensaje.getProperty("label.wrap", "Wrap de Pollo"));
        SandwichLabel.setText(mensaje.getProperty("label.sandwich", "Sándwich Club"));
        BebidasLink.setText(mensaje.getProperty("label.bebidas", "Bebidas"));
        TapasLink.setText(mensaje.getProperty("label.tapas", "Tapas"));
        SegundosLink.setText(mensaje.getProperty("label.segundos2", "Segundos"));
        EspecialesLink.setText(mensaje.getProperty("label.especiales2", "Especiales"));
        PostresLink.setText(mensaje.getProperty("label.postres", "Postres"));
        btnAñadirCesta.setText(mensaje.getProperty("button.login", "A la Cesta"));
    }



}