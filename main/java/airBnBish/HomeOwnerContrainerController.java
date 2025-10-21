package airBnBish;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class HomeOwnerContrainerController {
    private Customer customer;
    private Home home;

    @FXML
    private ImageView imageView;

    @FXML
    private Label titleLabel;

    @FXML
    private Label addressLabel;

    @FXML
    private Label statusLabel;

    public void initData(Customer customer, Home home) throws SQLException {
        this.customer = customer;
        this.home = home;

        Image image = new Image(home.getImageURL());
        imageView.setImage(image);
        titleLabel.setText(home.getTitle());
        addressLabel.setText(home.getAddress() + " " + home.getCountry() + ", " + home.getCity());

        LocalDate today = LocalDate.now();

        DBmanager dBmanager = DBmanager.createConnection();
        String sql = "select * from homebookings where BookingDate = ? and  HomeId = ?";
        PreparedStatement ps = dBmanager.connection.prepareStatement(sql);
        ps.setDate(1, Date.valueOf(today));
        ps.setInt(2, home.getId());
        ResultSet set = ps.executeQuery();

        if (!set.next()){
            statusLabel.setText("Currently Unbooked");
        }else {
            statusLabel.setText("Currently Booked");
        }
    }

    public void checkHome(MouseEvent event) throws IOException {
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
