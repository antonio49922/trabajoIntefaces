package org.example.proyecto;

import java.sql.Connection;
import java.sql.DriverManager;

/**@author AlejandroGpublic
**/

/**
 * Clase para gestionar la conexion con la base de datos
 *
 */

public class Cone {
    String url="jdbc:mysql://localhost:3306/bar";
    String usuario="root";
    String password="";
    Connection con;

    /**
     * Establece la conexion con la base de datos
     * @return Si la conexion es exitosa devuelve el objeto connection,
     * si falla es null
     */

    public Connection conectar() {


        try {
            con= DriverManager.getConnection(url,usuario,password);
            System.out.println("Conexion establecida :)");

        } catch (Exception e) {
            System.out.println("No se localiza la base de datos");
        }


        return con;
    }

}
