module com.example.possystem {

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires org.controlsfx.controls;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires org.json;
    requires org.apache.commons.io;

    opens com.example.possystem to javafx.fxml;
    opens com.example.possystem.controller to javafx.fxml;
    exports com.example.possystem;
    exports com.example.possystem.controller;
}
