package org.example.proyecto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * @author AlejandroGpublic
 */

class Documentacion {


    public static void main(String[] args) {
        // Crear la ventana principal
        JFrame frame = new JFrame("Interfaz de Documentación");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);  // Tamaño de la ventana
        frame.setLayout(new FlowLayout());  // Diseño simple con flujo

        // Crear el botón
        JButton button = new JButton("Acceder a la Documentación");

        // Añadir acción al botón
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirDocumentacion();
            }
        });

        // Añadir el botón a la ventana
        frame.add(button);

        // Hacer visible la ventana
        frame.setVisible(true);
    }

    // Método para abrir el archivo HTML en el navegador predeterminado
    private static void abrirDocumentacion() {
        try {
            // La ruta al archivo HTML, debe ser la correcta
            File htmlFile = new File("JAvadoc\\Index.html");

            if (htmlFile.exists()) {
                Desktop.getDesktop().browse(htmlFile.toURI());  // Abre en el navegador
            } else {
                JOptionPane.showMessageDialog(null, "El archivo index.html no se encuentra.");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al intentar abrir el archivo.");

        }
    }
}

