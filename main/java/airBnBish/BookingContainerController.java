package airBnBish;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.time.LocalDate;

public class BookingContainerController {
    private Customer customer;
    private Home home;
    private LocalDate startDate;
    private LocalDate endDate;

    @FXML
    private Label titleLabel;

    @FXML
    private Label addressLabel;

    @FXML
    private Label inLabel;

    @FXML
    private Label outLabel;

    public void initDate(Customer customer, Home home, LocalDate startDate, LocalDate endDate){
        this.customer = customer;
        this.home = home;
        this.startDate = startDate;
        this.endDate = endDate;

        titleLabel.setText(home.getTitle());
        addressLabel.setText(home.getAddress() + " " + home.getCountry() + ", " + home.getCity());
        inLabel.setText(startDate.toString());
        outLabel.setText(endDate.toString());
    }

    public void CheckBooking(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BookingDetails.fxml"));
        Parent root = loader.load();

        BookingDetailsController controller = loader.getController();
        controller.initData(customer,home,startDate,endDate);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
