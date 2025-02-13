package org.example.proyecto;

import java.sql.*;

public class DAOUsuarios {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

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