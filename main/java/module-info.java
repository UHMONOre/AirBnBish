module airBnBish {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens airBnBish to javafx.fxml;
    exports airBnBish;
}