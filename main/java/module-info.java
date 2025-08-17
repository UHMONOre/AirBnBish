module airBnBish {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;

    opens airBnBish to javafx.fxml;
    exports airBnBish;
}