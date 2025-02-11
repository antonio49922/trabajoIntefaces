module org.example.proyecto {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;


    opens org.example.proyecto to javafx.fxml;
    exports org.example.proyecto;
}