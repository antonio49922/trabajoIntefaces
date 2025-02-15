package org.example.proyecto;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import net.sf.jasperreports.engine.*;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

public class InformesController {

    @FXML
    private void generateUsersPDF() {
        generatePDF("src/main/resources/reportes/UsuariosUsuarios.jasper", "usuarios_reporte.pdf");
    }

    @FXML
    private void generateAllUsersPDF() {
        generatePDF("src/main/resources/reportes/UsuariosCasaNoble.jasper", "casa_noble_usuarios.pdf");
    }

    @FXML
    private void generateAdminsPDF() {
        generatePDF("src/main/resources/reportes/UsuariosAdministradores.jasper", "administradores_reporte.pdf");
    }

    @FXML
    private void generatePDF(String jasperPath, String pdfFileName) {
        try {
            File reportFile = new File(jasperPath);

            if (!reportFile.exists()) {
                throw new RuntimeException("El archivo del reporte no existe: " + jasperPath);
            }

            // Crear parámetros del reporte
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("titulo", "¡Reporte generado!");
            parameters.put("REPORT_DIR", "src/main/resources/");

            // Establecer la conexión a la base de datos
            String url = "jdbc:mysql://localhost:3306/bar";
            String user = "root";
            String password = "";
            Connection connection = DriverManager.getConnection(url, user, password);

            // Llenar el reporte con datos de la base de datos
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperPath, parameters, connection);

            // Crear el directorio si no existe
            File outputDir = new File("reportesExportados");
            if (!outputDir.exists()) {
                outputDir.mkdirs();
            }

            // Exportar el reporte a PDF
            String pdfPath = "reportesExportados/" + pdfFileName;
            JasperExportManager.exportReportToPdfFile(jasperPrint, pdfPath);

            // Mostrar alerta de éxito
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Éxito");
            alert.setHeaderText(null);
            alert.setContentText("El PDF se ha generado correctamente en: " + pdfPath);
            alert.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se pudo generar el PDF");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}