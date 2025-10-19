package airBnBish;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.sql.Date;
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

        HashMap<Customer , TreeSet<LocalDate>> bookingsMap = new HashMap<>();
        TreeSet<LocalDate> bookingsDate = new TreeSet<>();

        DBmanager dBmanager = DBmanager.createConnection();
        String sql = "select * from homebookings where HomeId = ?";
        PreparedStatement ps = dBmanager.connection.prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ps.setInt(1, home.getId());
        ResultSet set = ps.executeQuery();

        while (set.next()){
            bookingVbox.getChildren().remove(bookingLabel);
            Customer bookedCustomer = Customer.retrieveCustomer(set.getInt("CustomerId"));

            Date sqlDate = set.getDate("BookingDate");
            LocalDate date = sqlDate.toLocalDate();
            bookingsDate.add(date);

            if (set.next()){
                Integer nextCustomerId = set.getInt("CustomerId");
                set.previous();

                if (bookedCustomer.getId() != nextCustomerId){
                    bookingsMap.put(bookedCustomer, new TreeSet<>(bookingsDate));
                    bookingsDate.clear();
                }
            }else {
                bookingsMap.put(bookedCustomer, new TreeSet<>(bookingsDate));
            }
        }
    }
}
