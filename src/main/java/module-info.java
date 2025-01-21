module com.example.project {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.project to javafx.fxml;
    exports com.example.project;
    exports controllers;
    opens controllers to javafx.fxml;
}