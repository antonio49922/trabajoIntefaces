package org.example.proyecto;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class MenuFiltradoController {

    @FXML
    private ComboBox<String> tableName;

    @FXML
    private TextField userInput;

    @FXML
    private void initialize() {
        // Inicializar el ComboBox con opciones
        tableName.getItems().addAll("platos");
        tableName.setValue("Selecciona una opción");
    }

    // Método para generar el reporte con filtro
    @FXML
    private void generateFilteredReport() {
        try {
            // Ruta del archivo JRXML compilado
            String jasperPath = "src/main/resources/reportes/MenuFiltrado.jasper";

            // Verificar que el archivo Jasper existe
            File reportFile = new File(jasperPath);
            if (!reportFile.exists()) {
                throw new RuntimeException("El archivo del reporte no existe: " + jasperPath);
            }

            // Parámetros que se pasan al reporte
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("tableName", "platos");  // Aquí se pasa el nombre de la tabla (ajustar según el filtro)
            parameters.put("userInput", userInput.getText()); // El texto del filtro de búsqueda

            // Establecer la conexión a la base de datos
            String url = "jdbc:mysql://localhost:3306/bar";
            String user = "root";
            String password = "";
            Connection connection = DriverManager.getConnection(url, user, password);

            // Preparamos la sentencia SQL con parámetros
            String sql = "SELECT * FROM platos WHERE descripcion LIKE CONCAT('%', ?, '%')";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userInput.getText());

            // Llenar el reporte con datos de la base de datos
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperPath, parameters, connection);

            // Crear el directorio si no existe
            File outputDir = new File("reportesExportados");
            if (!outputDir.exists()) {
                outputDir.mkdirs();
            }

            // Exportar el reporte a PDF
            String pdfPath = "reportesExportados/menu_filtrado_reporte.pdf";
            JasperExportManager.exportReportToPdfFile(jasperPrint, pdfPath);

            // Mostrar alerta de éxito
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Éxito");
            alert.setHeaderText(null);
            alert.setContentText("El reporte se ha generado correctamente en: " + pdfPath);
            alert.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se pudo generar el reporte");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}