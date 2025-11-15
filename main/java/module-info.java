module airBnBish {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
    requires java.desktop;
    requires jdk.compiler;

    opens airBnBish to javafx.fxml;
    exports airBnBish;
}