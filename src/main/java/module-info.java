module com.example.javafxdemo {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires org.kordamp.bootstrapfx.core;
    opens com.example.javafxdemo to javafx.fxml;
    exports com.example.javafxdemo;
}