package airBnBish;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BookingHomeController {
    private Home home;
    private Customer customer;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer capacity;

    @FXML
    private Label titleLabel;

    @FXML
    private Label addressLabel;

    @FXML
    private Label countryCityLabel;

    @FXML
    private Label datesLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label nightLabel;

    @FXML
    private ImageView imageView;

    public void initDate(Home home, Customer customer, LocalDate startDate, LocalDate endDate, Integer capacity){
        this.home = home;
        this.customer = customer;
        this.startDate = startDate;
        this.endDate = endDate;
        this.capacity = capacity;

        titleLabel.setText(home.getTitle());
        addressLabel.setText(home.getAddress());
        countryCityLabel.setText(home.getCountry() + " , " + home.getCity());
        datesLabel.setText("Check-in: " + startDate + " Check-out: " + endDate);

        Long nights = ChronoUnit.DAYS.between(startDate,endDate);
        Double finalPrice = home.getPrice() * nights;

        priceLabel.setText(String.valueOf(finalPrice));
        nightLabel.setText("For " + nights + " nights");

        Image image = new Image(home.getImageURL());
        imageView.setImage(image);
    }

    public void book(ActionEvent event) throws SQLException, IOException {
        DBmanager dBmanager = DBmanager.createConnection();
        String sql = "insert into homebookings (BookingDate, CustomerID, HomeId) values (?, " + customer.getId() + ", " + home.getId() + ")";

        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)){
            PreparedStatement ps = dBmanager.connection.prepareStatement(sql);
            ps.setDate(1, java.sql.Date.valueOf(date));
            ps.executeUpdate();
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AccountPage.fxml"));
        Parent root = loader.load();

        AccountPageController controller = loader.getController();
        controller.initDate(customer);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void returnAction(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeSelect.fxml"));
        Parent root = loader.load();

        HomeSelectController controller = loader.getController();
        controller.initData(customer, home.getCity(), startDate, endDate, capacity);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
