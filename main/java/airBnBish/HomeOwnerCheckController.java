package airBnBish;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class HomeOwnerCheckController {
    private Customer customer;

    @FXML
    private VBox homeContainer;

    public void initData(Customer customer) throws SQLException, IOException {
        this.customer = customer;

        loadHomes();
    }

    public void loadHomes() throws SQLException, IOException {
        ArrayList<Home> homeList = customer.ownedHomes();

        for (Home home : homeList){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeOwnerContainer.fxml"));
            AnchorPane bookingCard = loader.load();

            HomeOwnerContrainerController controller = loader.getController();
            controller.initData(customer, home);

            homeContainer.getChildren().add(bookingCard);
        }
    }

    public void addHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddHome.fxml"));
        Parent root = loader.load();

        AddHomeController controller = loader.getController();
        controller.initData(customer);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void returnAction(ActionEvent event) throws IOException, SQLException {
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
