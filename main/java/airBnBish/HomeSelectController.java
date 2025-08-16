package airBnBish;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class HomeSelectController {
    private Customer customer;
    private String city;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer capacity;

    @FXML
    private VBox homesContainer;

    @FXML
    private Button returnButton;

    public void initData(Customer customer, String city, LocalDate startDate, LocalDate endDate, Integer capacity) throws SQLException, IOException {
        this.customer = customer;
        this.city = city;
        this.startDate = startDate;
        this.endDate = endDate;
        this.capacity = capacity;

        System.out.println("Data received:");
        System.out.println("Customer: " + customer.getUsername());
        System.out.println("City: " + city);
        System.out.println("Start: " + startDate);
        System.out.println("End: " + endDate);
        System.out.println("Capacity: " + capacity);

        loadHomes();
    }

    //check if not results
    public void loadHomes() throws IOException, SQLException {
        DBmanager dBmanager = DBmanager.createConnection();
        String sql = "select Id from homes where City = ? and Capacity >= ? and Capacity <= ? + 2";
        PreparedStatement ps = dBmanager.connection.prepareStatement(sql);
        ps.setString(1, city);
        ps.setInt(2, capacity);
        ps.setInt(3, capacity);
        ResultSet set = ps.executeQuery();
        while(set.next()){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeContainer.fxml"));
            AnchorPane homeCard = loader.load();

            Home home = Home.retrieveHome(set.getInt("Id"));
            if (HomeBooking.checkAvailability(home, startDate, endDate)){
                HomeContainerController controller = loader.getController();
                controller.initData(home, customer, startDate, endDate, capacity);
                homesContainer.getChildren().add(homeCard);
            }
        }
        homesContainer.getChildren().remove(returnButton);
        homesContainer.getChildren().add(returnButton);
        DBmanager.closeConnections(dBmanager);
    }

    public void returnAction (ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
        Parent root = loader.load();

        MainController controller = loader.getController();
        controller.returnInitData(customer);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
