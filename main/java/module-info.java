module airBnBish {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
    requires java.desktop;

    opens airBnBish to javafx.fxml;
    exports airBnBish;
}