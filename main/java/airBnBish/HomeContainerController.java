package airBnBish;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class HomeContainerController {
    private Home home;
    private Customer customer;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer capacity;

    @FXML
    private ImageView imageView;

    @FXML
    private Label titleLabel;

    @FXML
    private Label addressLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label capLabel;

    @FXML
    private Button bookButton;

    public void initData(Home home, Customer customer, LocalDate startDate, LocalDate endDate, Integer capacity){
        this.home = home;
        this.customer = customer;
        this.startDate = startDate;
        this.endDate = endDate;
        this.capacity = capacity;

        titleLabel.setText(home.getTitle());
        addressLabel.setText(home.getAddress());
        priceLabel.setText(String.valueOf(home.getPrice()));
        capLabel.setText(String.valueOf(home.getCapacity()));

        if (!home.getImageURL().trim().isEmpty()){
            Image image = new Image(home.getImageURL());
            imageView.setImage(image);
        }
    }

    public void book(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BookingHome.fxml"));
        Parent root = loader.load();

        BookingHomeController controller = loader.getController();
        controller.initDate(home, customer, startDate, endDate, capacity);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
