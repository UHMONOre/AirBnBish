module com.hotel.hotel {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.hotel.hotel to javafx.fxml;
    exports com.hotel.hotel;
}