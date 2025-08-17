package airBnBish;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

public class BookingDetailsController {
    private Customer customer;
    private Home home;
    private LocalDate startDate;
    private LocalDate endDate;

    @FXML
    Label titleLabel;

    @FXML
    Label addressLabel;

    @FXML
    Label countryCityLabel;

    @FXML
    Label startDateLabel;

    @FXML
    Label endDateLabel;

    @FXML
    ImageView imageView;

    public void initData(Customer customer, Home home, LocalDate startDate, LocalDate endDate){
        this.customer = customer;
        this.home = home;
        this.startDate = startDate;
        this.endDate = endDate;

        titleLabel.setText(home.getTitle());
        addressLabel.setText(home.getAddress());
        countryCityLabel.setText(home.getCountry() + ", " + home.getCity());
        startDateLabel.setText("Check-in: " + startDate);
        endDateLabel.setText("Check-out: " + endDate);

        Image image = new Image(home.getImageURL());
        imageView.setImage(image);
    }

    public void cancelBookingButton(ActionEvent event) throws SQLException, IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure you want to continue?");
        alert.setContentText("Press OK to continue.");

        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

        if (result == ButtonType.OK){
            HomeBooking.cancelBooking(customer, home, startDate, endDate);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("AccountPage.fxml"));
            Parent root = loader.load();

            AccountPageController controller = loader.getController();
            controller.initDate(customer);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }else {
            System.out.println("Cancellation aborted");
        }
    }

    public void returnButton(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AccountPage.fxml"));
        Parent root = loader.load();

        AccountPageController controller = loader.getController();
        controller.initDate(customer);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
