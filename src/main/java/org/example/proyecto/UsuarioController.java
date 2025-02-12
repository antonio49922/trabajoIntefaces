package org.example.proyecto;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Pattern;

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

    @FXML
    private void initialize() {
        sentForm.setOnAction(event -> addUser());
    }

    private void addUser() {
        String name = nameField.getText().trim();
        String surname = surnameField.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();
        String rol = rolField.getText().trim();

        // Validar contraseña
        if(!validatePassword(password)) {
            showAlert("ERROR", "Verifica la contraseña",
                    "La contraseña debe tener al menos 8 carácteres, de los cuales debe haber una mayúscula, una minúscula y un número.");
            return;
        }

        // Verificar el rol
        Rol rolUser = rol.equals("losDeAtras-25") ? Rol.ADMIN : Rol.USUARIO;

        // Verificar si el usuario ya existe
        if(daoUsuarios.userExists(email)) {
            showAlert("ERROR", "El usuario ya existe", "Ya existe un usuario con ese correo.");
            redirectMenu();
        } else {
            // Crear y registrar el usuario
            Usuario usuario = new Usuario(name, surname, email, password, rolUser);
            daoUsuarios.addUser(usuario);

            showAlert("¡ÉXITO!", "Usuario registrado correctamente", "Bienvenido/a " + name);
            redirectMenu();
        }
    }

    private boolean validatePassword(String password) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$";
        return Pattern.matches(regex, password);
    }

    private void showAlert(String title, String header, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void redirectMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) sentForm.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
