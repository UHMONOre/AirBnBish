package airBnBish;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
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

    public void initData(Customer customer, Home home, LocalDate startDate, LocalDate endDate) throws SQLException, IOException {
        this.customer = customer;
        this.home = home;
        this.startDate = startDate;
        this.endDate = endDate;

        HashMap<Customer , TreeSet<LocalDate>> bookingsMap = new HashMap<>();
        TreeSet<LocalDate> bookingsDateHolder = new TreeSet<>();

        Integer paneId = 0;

        DBmanager dBmanager = DBmanager.createConnection();
        ResultSet set;

        if (startDate == null && endDate == null){
            String sql = "select * from homebookings where HomeId = ?";

            PreparedStatement ps = dBmanager.connection.prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ps.setInt(1, home.getId());
            set = ps.executeQuery();
        } else {
            String sql = "select * from homebookings where HomeId = ? and BookingDate >= ? and BookingDate <= ?";

            PreparedStatement ps = dBmanager.connection.prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ps.setInt(1, home.getId());
            ps.setDate(2, Date.valueOf(startDate));
            ps.setDate(3, Date.valueOf(endDate));
            set = ps.executeQuery();
        }

        while (set.next()){
            bookingVbox.getChildren().remove(bookingLabel);
            Customer bookedCustomer = Customer.retrieveCustomer(set.getInt("CustomerId"));

            Date sqlDate = set.getDate("BookingDate");
            LocalDate date = sqlDate.toLocalDate();
            bookingsDateHolder.add(date);

            if (set.next()){
                Integer nextCustomerId = set.getInt("CustomerId");
                set.previous();

                if (bookedCustomer.getId() != nextCustomerId){
                    bookingsMap.put(bookedCustomer, new TreeSet<>(bookingsDateHolder));
                    bookingsDateHolder.clear();
                }
            }else {
                bookingsMap.put(bookedCustomer, new TreeSet<>(bookingsDateHolder));
            }
        }

        for (Customer bookedCustomer : bookingsMap.keySet()){
            TreeSet<LocalDate> bookingDates = bookingsMap.get(bookedCustomer);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("OwnerBookingContainer.fxml"));
            AnchorPane bookingCard = loader.load();

            OwnerBookingContainerController controller = loader.getController();
            controller.initData(customer, home, bookingDates, bookingVbox, String.valueOf(paneId));
            bookingCard.setId(String.valueOf(paneId));
            paneId++;

            bookingVbox.getChildren().add(bookingCard);
        }
    }

    public static void deletePane(VBox bookingVbox, String paneId){
        bookingVbox.getChildren().removeIf(
                node -> node instanceof AnchorPane pane && paneId.equals(pane.getId())
        );
    }

    public void returnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeOwnerDetails.fxml"));
        Parent root = loader.load();

        HomeOwnerDetailsController controller = loader.getController();
        controller.initData(customer, home);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
