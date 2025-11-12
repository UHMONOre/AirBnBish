package airBnBish;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import javafx.scene.input.MouseEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.TreeSet;

public class OwnerBookingContainerController {
    private Customer customer;
    private Home home;
    private TreeSet<LocalDate> bookingDates;
    private VBox bookingVbox;
    private String paneId;

    @FXML
    private Label titleLabel, addressLabel, customerLabel, datesLabel;

    public void initData(Customer customer, Home home, TreeSet<LocalDate> bookingDates, VBox bookingVbox, String paneId){
        this.customer = customer;
        this.home = home;
        this.bookingDates = bookingDates;
        this.bookingVbox = bookingVbox;
        this.paneId = paneId;

        titleLabel.setText(home.getTitle());
        addressLabel.setText(home.getAddress() + " " + home.getCountry() + ", " + home.getCity());
        customerLabel.setText("Username: " + customer.getUsername() + " Email: " + customer.getEmail());
        datesLabel.setText("From: " + bookingDates.first() + " Until: " + bookingDates.last());
    }

    public void removeBooking(MouseEvent event) throws SQLException {
        HomeBooking.cancelBooking(customer, home, bookingDates.first(), bookingDates.last());
        HomeOwnerBookingsController.deletePane(bookingVbox, paneId);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Booking Deleted");
        alert.setHeaderText(null);
        alert.setContentText("The booking has been successfully deleted.");
        alert.showAndWait();
    }
}
