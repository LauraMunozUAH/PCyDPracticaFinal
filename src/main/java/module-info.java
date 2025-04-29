module com.example.pcydpracticafinal {
    requires javafx.controls;
    requires javafx.fxml;
    //requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    //requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    //requires eu.hansolo.tilesfx;
    //requires com.almasb.fxgl.all;
    requires java.desktop;
    //requires org.apache.logging.log4j;

    opens com.example.pcydpracticafinal to javafx.fxml;
    exports com.example.pcydpracticafinal;
}