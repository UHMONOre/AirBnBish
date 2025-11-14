package airBnBish;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import javafx.scene.input.MouseEvent;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.TreeSet;

public class OwnerBookingContainerController {
    private Customer customer;
    private Customer tenant;
    private Home home;
    private TreeSet<LocalDate> bookingDates;
    private VBox bookingVbox;
    private String paneId;

    @FXML
    private Label titleLabel, addressLabel, customerLabel, datesLabel;

    public void initData(Customer customer, Home home, TreeSet<LocalDate> bookingDates, VBox bookingVbox, String paneId) throws SQLException {
        DBmanager dBmanager = DBmanager.createConnection();
        String sql = "select * from homebookings where BookingDate = ? and HomeId = ?";
        PreparedStatement ps = dBmanager.connection.prepareStatement(sql);
        ps.setDate(1, Date.valueOf(bookingDates.first()));
        ps.setInt(2, home.getId());
        ResultSet set = ps.executeQuery();
        set.next();

        this.tenant = Customer.retrieveCustomer(set.getInt("CustomerId"));
        this.customer = customer;
        this.home = home;
        this.bookingDates = bookingDates;
        this.bookingVbox = bookingVbox;
        this.paneId = paneId;

        titleLabel.setText(home.getTitle());
        addressLabel.setText(home.getAddress() + " " + home.getCountry() + ", " + home.getCity());
        customerLabel.setText("Username: " + tenant.getUsername() + " Email: " + tenant.getEmail());
        datesLabel.setText("From: " + bookingDates.first() + " Until: " + bookingDates.last());
    }

    public void removeBooking(MouseEvent event) throws SQLException {
        HomeBooking.cancelBooking(tenant, home, bookingDates.first(), bookingDates.last());
        HomeOwnerBookingsController.deletePane(bookingVbox, paneId);

        DBmanager dBmanager = DBmanager.createConnection();
        String sql = "insert into bookingstatus(CustomerId, HomeId, StartDate, EndDate) values (?, ?, ?, ?)";
        PreparedStatement ps = dBmanager.connection.prepareStatement(sql);

        ps.setInt(1, tenant.getId());
        ps.setInt(2, home.getId());

        Date sqlStartDate = Date.valueOf(bookingDates.first());
        Date sqlEndDate = Date.valueOf(bookingDates.last());

        ps.setDate(3, sqlStartDate);
        ps.setDate(4, sqlEndDate);
        ps.executeUpdate();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Booking Deleted");
        alert.setHeaderText(null);
        alert.setContentText("The booking has been successfully deleted.");
        alert.showAndWait();
    }
}
