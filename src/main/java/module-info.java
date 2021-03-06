module com.example.simplerpg {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.desktop;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens com.example.simplerpg to javafx.fxml;
    exports com.example.simplerpg;

    exports main;
    opens main to javafx.graphics;
    exports saveload;
    opens saveload to javafx.graphics;
}