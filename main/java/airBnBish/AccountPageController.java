package airBnBish;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeSet;

public class AccountPageController {
    private Customer customer;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label noBookingLabel;

    @FXML
    private VBox bookingContainer;

    public void initDate(Customer customer) throws SQLException, IOException {
        this.customer = customer;

        //create the alert and offer the choose of deleting the altered booking or keeping it
        // and inform if it was completely deleted
        checkBookingStatus();


        usernameLabel.setText("Welcome " + customer.getUsername() + "!");
        System.out.println("....");

        loadBookings();
    }

    public void makeBooking(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
        Parent root = loader.load();

        MainController controller = loader.getController();
        controller.initData(customer);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void loadBookings() throws SQLException, IOException {
        DBmanager dBmanager = DBmanager.createConnection();
        ArrayList<Home> homeList = HomeBooking.checkBookedHomes(customer);
        String sql = "select BookingDate from homebookings where CustomerId = ? and HomeId = ?";
        if (!homeList.isEmpty()){
            noBookingLabel.setText(null);
            for (Home home : homeList){
                TreeSet<LocalDate> dates = new TreeSet<>();
                PreparedStatement ps = dBmanager.connection.prepareStatement(sql);
                ps.setInt(1, customer.getId());
                ps.setInt(2, home.getId());
                ResultSet rs = ps.executeQuery();

                while (rs.next()){
                    dates.add(rs.getDate("BookingDate").toLocalDate());
                }

                FXMLLoader loader = new FXMLLoader(getClass().getResource("BookingContainer.fxml"));
                AnchorPane bookingCard = loader.load();

                BookingContainerController controller = loader.getController();
                controller.initDate(customer, home, dates.first(), dates.last());

                bookingContainer.getChildren().add(bookingCard);
            }
        }
    }

    public void editAccount(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EditAccount.fxml"));
        Parent root = loader.load();

        EditAccountController controller = loader.getController();
        controller.initData(customer);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void checkBookingStatus() throws SQLException {
        DBmanager dBmanager = DBmanager.createConnection();
        String sql = "select * from bookingstatus where CustomerId = ?";
        PreparedStatement ps = dBmanager.connection.prepareStatement(sql);
        ps.setInt(1, customer.getId());
        ResultSet set = ps.executeQuery();

        while (set.next()){
            Customer customer1 = Customer.retrieveCustomer(set.getInt("CustomerId"));
            Home home1 = Home.retrieveHome(set.getInt("HomeId"));
            LocalDate startDate = set.getDate("StartDate").toLocalDate();
            LocalDate endDate = set.getDate("EndDate").toLocalDate();

            sql = "select * from homebookings where CustomerId = ? and HomeId = ?";
            ps = dBmanager.connection.prepareStatement(sql);
            ps.setInt(1, customer.getId());
            ps.setInt(2, set.getInt("HomeId"));
            ResultSet set1 = ps.executeQuery();

            TreeSet<LocalDate> dates = new TreeSet<>();
            while (set1.next()){
                LocalDate date = set1.getDate("BookingDate").toLocalDate();
                dates.add(date);
            }

            if (dates.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Booking Canceled");
                alert.setHeaderText(null);
                alert.setContentText("The booking for the house: " + home1.getTitle() + " with address: " + home1.getAddress() + " on the dates: " + startDate + " to " + endDate + " has been canceled.");
                alert.showAndWait();
            }else {
                //check continuity
            }
        }
    }

    public void checkHomes(ActionEvent event) throws IOException, SQLException {
        ArrayList<Home> homeList = customer.ownedHomes();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeOwnerCheck.fxml"));
        Parent root = loader.load();

        HomeOwnerCheckController controller = loader.getController();
        controller.initData(customer);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void logout(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
