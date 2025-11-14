package airBnBish;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddHomeController {
    private Customer customer;

    @FXML
    private TextField titleField, addressField, countryField, cityField, priceField, imageField;

    @FXML
    private Spinner<Integer> capSpinner;

    public void initData(Customer customer){
        this.customer = customer;

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,8);
        valueFactory.setValue(1);
        capSpinner.setValueFactory(valueFactory);
    }

    public void addHome(ActionEvent event) throws SQLException, IOException {

        DBmanager dBmanager = DBmanager.createConnection();
        String title = titleField.getText();
        String address = addressField.getText();
        String country = countryField.getText();
        String city = cityField.getText();
        String stringPrice = priceField.getText();
        String image = imageField.getText();

        Integer cap = capSpinner.getValue();

        if (title.trim().isEmpty() || address.trim().isEmpty() || country.trim().isEmpty() || city.trim().isEmpty() || stringPrice.trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Missing Information");
            alert.setContentText("All the fields except Image Url must be filled.");
            alert.showAndWait();
            return;
        }

        String sql = "select * from homes where title = ?";
        PreparedStatement ps = dBmanager.connection.prepareStatement(sql);
        ps.setString(1, title);
        ResultSet set = ps.executeQuery();

        if (set.next()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Wrong Information");
            alert.setContentText("This title already exists.");
            alert.showAndWait();
            return;
        }

        sql = "select * from homes where Address = ? and Country = ? and City = ?";
        ps = dBmanager.connection.prepareStatement(sql);
        ps.setString(1, address);
        ps.setString(2, country);
        ps.setString(3, city);
        set = ps.executeQuery();

        if (set.next()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Wrong Information");
            alert.setContentText("This home already exists.");
            alert.showAndWait();
            return;
        }

        Double price;

        try {
            price = Double.parseDouble(stringPrice);
        } catch (NumberFormatException ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid double number.");
            alert.showAndWait();
            return;
        }

        if (price <= 0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a positive non zero number.");
            alert.showAndWait();
            return;
        }

        if (!isValidURL(image) && !image.trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid image url.");
            alert.showAndWait();
            return;
        }

        sql = "insert into homes (Title, Address, Country, City, Price, Capacity, Image, CustomerId) values (?,?,?,?,?,?,?,?)";
        ps = dBmanager.connection.prepareStatement(sql);

        ps.setString(1, title);
        ps.setString(2, address);
        ps.setString(3, country);
        ps.setString(4, city);
        ps.setDouble(5, price);
        ps.setInt(6, cap);
        ps.setString(7, image);
        ps.setInt(8, customer.getId());

        ps.executeUpdate();

        //return action
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeOwnerCheck.fxml"));
        Parent root = loader.load();

        HomeOwnerCheckController controller = loader.getController();
        controller.initData(customer);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public boolean isValidURL(String image) {
        try {
            new java.net.URL(image); // just parsing it
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }

    public void returnAction(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeOwnerCheck.fxml"));
        Parent root = loader.load();

        HomeOwnerCheckController controller = loader.getController();
        controller.initData(customer);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
