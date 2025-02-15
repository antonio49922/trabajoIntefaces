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
        tableName.getItems().addAll("platos");
        tableName.setValue("Selecciona una opción");
    }

    @FXML
    private void generateReport() {
        String selectedTable = tableName.getValue();
        String input = userInput.getText().trim();

        if (input.isEmpty()) {
            showAlert("Error", "Debe ingresar un alimento para la búsqueda.");
            return;
        }

        try {
            JasperReport report = JasperCompileManager.compileReport("src/main/resources/reports/Menu_filtrado.jrxml");

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("tableName", selectedTable);
            parameters.put("userInput", input);

            parameters.put("titulo", "¡Reporte generado!");
            parameters.put("REPORT_DIR", "src/main/resources/");

            String url = "jdbc:mysql://localhost:3306/bar";
            String user = "root";
            String password = "";
            Connection connection = DriverManager.getConnection(url, user, password);

            // Crear el directorio si no existe
            File outputDir = new File("reportesExportados");
            if (!outputDir.exists()) {
                outputDir.mkdirs();
            }

            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, connection);
            JasperExportManager.exportReportToPdfFile(jasperPrint, "MenuFiltrado.pdf");

            JasperViewer.viewReport(jasperPrint, false);

            showAlert("Éxito", "Reporte generado correctamente.");

        } catch (JRException e) {
            e.printStackTrace();
            showAlert("Error", "Ocurrió un error al generar el reporte.");

        } catch (SQLException e) {
            throw new RuntimeException(e);
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