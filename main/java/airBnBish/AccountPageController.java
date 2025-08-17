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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
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

        usernameLabel.setText("Welcome " + customer.getUsername() + "!");

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

    public void logout(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
