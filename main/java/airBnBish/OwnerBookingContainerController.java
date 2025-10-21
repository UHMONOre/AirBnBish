package airBnBish;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.TreeSet;

public class OwnerBookingContainerController {
    private Customer customer;
    private Home home;
    private TreeSet<LocalDate> bookingDates;
    private String paneId;

    @FXML
    private Label titleLabel, addressLabel, customerLabel, datesLabel;

    public void initData(Customer customer, Home home, TreeSet<LocalDate> bookingDates, String paneId){
        this.customer = customer;
        this.home = home;
        this.bookingDates = bookingDates;
        this.paneId = paneId;

        titleLabel.setText(home.getTitle());
        addressLabel.setText(home.getAddress() + " " + home.getCountry() + ", " + home.getCity());
        customerLabel.setText("Username: " + customer.getUsername() + " Email: " + customer.getEmail());
        datesLabel.setText("From: " + bookingDates.first() + " Until: " + bookingDates.last());
    }

    public void removeBooking(MouseEvent event) throws SQLException {
        HomeBooking.cancelBooking(customer, home, bookingDates.first(), bookingDates.last());
    }
}
