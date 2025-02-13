package org.example.proyecto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase que representa el modelo de usuario.
 * Proporciona metodos para obtener y establecer el nombre y apellido del usuario.
 *
 * @author Alberto
 * @version 1.0
 * @since 13/02/2025
 */
public class UsuarioModel {
    private static UsuarioModel instance;
    private String nombre;
    private String apellido;
    private String email;

    /**
     * Constructor privado para implementar el patron Singleton.
     */
    private UsuarioModel() {
        this.nombre = "Usuario Desconocido";
        this.apellido = "";
        this.email = null;
    }

    /**
     * Obtiene la instancia unica de UsuarioModel.
     *
     * @return la instancia unica de UsuarioModel
     */
    public static UsuarioModel getInstance() {
        if (instance == null) {
            instance = new UsuarioModel();
        }
        return instance;
    }

    /**
     * Carga el usuario desde la base de datos basado en el correo electronico.
     *
     * @param email El correo del usuario que ha iniciado sesion.
     */
    public void cargarUsuarioDesdeDB(String email) {
        this.email = email;
        DAOUsuarios daoUsuarios = new DAOUsuarios();

        String[] userData = obtenerDatosUsuarioDesdeDB(daoUsuarios, email);

        if (userData != null && userData.length >= 2) {
            this.nombre = (userData[0] != null && !userData[0].isEmpty()) ? userData[0] : "Usuario";
            this.apellido = (userData[1] != null && !userData[1].isEmpty()) ? userData[1] : "";
        } else {
            System.out.println("ERROR: Datos de usuario incompletos en la base de datos.");
            this.nombre = "Usuario Desconocido";
            this.apellido = "";
        }

        System.out.println("UsuarioModel actualizado: " + this.getNombreCompleto());
    }

    /**
     * Obtiene los datos del usuario desde la base de datos basado en su correo.
     * @param daoUsuarios Instancia de DAOUsuarios para acceder a la base de datos.
     * @param email Correo del usuario.
     * @return Array con nombre y apellido del usuario o null si no se encuentra.
     */
    private String[] obtenerDatosUsuarioDesdeDB(DAOUsuarios daoUsuarios, String email) {
        String query = "SELECT nombre, apellido FROM usuarios WHERE email = ?";
        try (PreparedStatement statement = daoUsuarios.getConnection().prepareStatement(query)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new String[]{resultSet.getString("nombre"), resultSet.getString("apellido")};
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Obtiene el nombre completo del usuario.
     *
     * @return Nombre y apellido concatenados.
     */
    public String getNombreCompleto() {
        return (nombre != null && !nombre.isEmpty()) ? nombre + " " + apellido : "Usuario Desconocido";
    }
}
