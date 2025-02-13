package org.example.proyecto;

import java.sql.*;

/**
 * @author Emily-Antonio
 * @vesion 1.0
 * @since 10/02/2025
 **/


/**
 * Clase para gestionar la conexion a la base de datos
 * y la manipulacio de usuario
 * Proporciona metodos para conectar a la base de datos y gestionar usuarios
 *
 */

public class DAOUsuarios {

    private Connection connection;

    /**
     * Constructor que establece la conexion de la base de datos
     */
    public DAOUsuarios() {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Initialize the database connection
            String url = "jdbc:mysql://localhost:3306/bar";
            String user = "root";
            String password = "";
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Verifica si un usuario existe en la base de datos meduante su correo
     *
     * @param email Correo del usuario
     * @return True si existe, False en caso contrario
     */

    public boolean userExists(String email) {
        String query = "SELECT COUNT(*) FROM usuarios WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1); // Obtener el conteo de usuarios
                return count > 0; // Devolver true si hay al menos un usuario
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Devolver false si hay un error o no se encontró el usuario
    }

    /**
     * Agregar un usuario a la base de datos
     *
     * @param usuario  Objeto con la informacion del usuario a agregar
     */

    public void addUser(Usuario usuario) {
        String query = "INSERT INTO usuarios (nombre, apellido, email, contraseña, rol) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getApellido());
            stmt.setString(3, usuario.getCorreo());
            stmt.setString(4, usuario.getPassword());
            stmt.setString(5, usuario.getRol().toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}