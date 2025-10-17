package airBnBish;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.TreeSet;

public class HomeOwnerBookingsController {
    private Customer customer;
    private Home home;
    private LocalDate startDate, endDate;

    @FXML
    private Label bookingLabel;

    @FXML
    private VBox bookingVbox;

    public void initData(Customer customer, Home home, LocalDate startDate, LocalDate endDate) throws SQLException {
        this.customer = customer;
        this.home = home;
        this.startDate = startDate;
        this.endDate = endDate;

        HashMap<Customer , TreeSet<LocalDate>> bookings = new HashMap<>();

        DBmanager dBmanager = DBmanager.createConnection();
        String sql = "select * from homebookings where HomeId = ?";
        PreparedStatement ps = dBmanager.connection.prepareStatement(sql);
        ps.setInt(1, home.getId());
        ResultSet set = ps.executeQuery();

        while (set.next()){
            bookingVbox.getChildren().remove(bookingLabel);
            //hashmap with customer of booking as key and treeset of dates for data
        }
    }
}
