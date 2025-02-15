package org.example.proyecto;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.regex.Pattern;

/**
 * Controlador para la gestión de usuarios en la aplicación.
 * Permite el registro y validación de usuarios antes de acceder al sistema.
 * También maneja la redirección al menú principal y la carga de datos del usuario.
 */
public class UsuarioController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField surnameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField rolField;

    @FXML
    private Button sentForm;

    private DAOUsuarios daoUsuarios = new DAOUsuarios();
    private String selectedLanguage = "espanol_es.properties"; // Default language

    @FXML
    private void initialize() {
        sentForm.setOnAction(event -> addUser());
        mensaje = new Properties(); // Initialize the mensaje property
    }

    @FXML
    private void addUser() {
        String name = nameField.getText().trim();
        String surname = surnameField.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();
        String rol = rolField.getText().trim();

        // Validar email
        if (!validateEmail(email)) {
            showAlert("ERROR", "Verifica el correo",
                    "El correo debe tener el formato texto@texto.texto.");
            return;
        }

        // Validar contraseña
        if (!validatePassword(password)) {
            showAlert("ERROR", "Verifica la contraseña",
                    "La contraseña debe tener al menos 8 carácteres, de los cuales debe haber una mayúscula, una minúscula y un número.");
            return;
        }

        // Verificar el rol
        Rol rolUser = rol.equals("losDeAtras-25") ? Rol.ADMIN : Rol.USUARIO;

        // Verificar si el usuario ya existe
        if (daoUsuarios.userExists(email)) {
            showAlert(
                    mensaje.getProperty("alert.attention", "!ATENCIÓN¡"),
                    mensaje.getProperty("alert.userExists", "El usuario ya existe"),
                    mensaje.getProperty("alert.userExistsMessage", "Ya existe un usuario con ese correo.")
            );
            redirectMenu(email);
        } else {
            // Crear y registrar el usuario
            Usuario usuario = new Usuario(name, surname, email, password, rolUser);
            daoUsuarios.addUser(usuario);

            showAlert(
                    mensaje.getProperty("alert.success", "¡ÉXITO!"),
                    mensaje.getProperty("alert.userRegistered", "Usuario registrado correctamente"),
                    mensaje.getProperty("alert.welcome", "Bienvenido/a ") + name
            );
            redirectMenu(email);
        }
    }

    private boolean validateEmail(String email) {
        String regex = "^[\\w-\\.]+@[\\w-]+\\.[a-z]{2,4}$";
        return Pattern.matches(regex, email);
    }

    private boolean validatePassword(String password) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$";
        return Pattern.matches(regex, password);
    }

    private void showAlert(String title, String header, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void redirectMenu(String email) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Menu.fxml"));
            Parent root = fxmlLoader.load();

            // Obtener el controlador del menú
            Menu menuController = fxmlLoader.getController();

            // Obtener datos del usuario desde la base de datos
            String[] userData = getUserDataFromDB(email);
            if (userData != null) {
                // Establecer mensaje de bienvenida
                menuController.setWelcomeMessage(userData[0], userData[1]);
                // Establecer el idioma
                menuController.setLanguage(selectedLanguage);
            }

            // Mostrar alerta de éxito en el idioma seleccionado
            if (selectedLanguage.contains("ingles")) {
                showAlert("SUCCESS!", "Login successful", "Welcome " + userData[0]);
            } else {
                showAlert("¡ÉXITO!", "Inicio de sesión correcto", "Bienvenido/a " + userData[0]);
            }

            // Mostrar la nueva ventana
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            // Cerrar la ventana actual
            Stage currentStage = (Stage) passwordField.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String[] getUserDataFromDB(String email) {
        String query = "SELECT nombre, apellido FROM usuarios WHERE email = ?";
        try (PreparedStatement statement = daoUsuarios.getConnection().prepareStatement(query)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                return new String[]{nombre, apellido};
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @FXML
    private Label titulo_inicio;
    @FXML
    private Label nombre;
    @FXML
    private Label apellido;
    @FXML
    private Label email;
    @FXML
    private Label password;
    @FXML
    private Label rol;

    private Properties mensaje;

    @FXML
    private void cambiarAIngles() {
        selectedLanguage = "ingles_en.properties";
        loadLanguage(selectedLanguage);
        updateTexts();
    }

    @FXML
    private void cambiarAEspanol() {
        selectedLanguage = "espanol_es.properties";
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
        titulo_inicio.setText(mensaje.getProperty("label.inicioSesion", "Inicio de Sesion"));
        nombre.setText(mensaje.getProperty("label.nombre", "Nombre"));
        apellido.setText(mensaje.getProperty("label.apellido", "Apellidos"));
        email.setText(mensaje.getProperty("label.email", "Correo"));
        password.setText(mensaje.getProperty("label.password", "Contraseña"));
        rol.setText(mensaje.getProperty("label.rol", "Rol"));
        sentForm.setText(mensaje.getProperty("button.login", "Accede al Menu"));

        // Actualizar tooltip de password
        Tooltip passwordTooltip = new Tooltip(mensaje.getProperty("tooltip.toolPass", "(Debe tener mín 8 carácteres, una mayús y un número)"));
        passwordField.setTooltip(passwordTooltip);

        // Actualizar promptText de rol
        rolField.setPromptText(mensaje.getProperty("textField.prompt", "Usuario"));
    }
}