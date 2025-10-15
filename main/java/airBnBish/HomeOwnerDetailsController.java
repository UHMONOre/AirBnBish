package airBnBish;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HomeOwnerDetailsController {
    private Customer customer;
    private Home home;

    @FXML
    private TextField titleField, priceField, imageField;

    @FXML
    private Spinner<Integer> capSpinner;

    @FXML
    private DatePicker startDate, endDate;

    public void initData(Customer customer, Home home){
        this.customer = customer;
        this.home = home;

        titleField.setText(home.getTitle());
        priceField.setText(home.getPrice().toString());
        imageField.setText(home.getImageURL());

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,8);
        valueFactory.setValue(home.getCapacity());
        capSpinner.setValueFactory(valueFactory);
    }

    public void editHome(ActionEvent event) throws SQLException {
        DBmanager dBmanager = DBmanager.createConnection();

        Boolean change = false;

        String title = titleField.getText();
        String stringPrice = priceField.getText();
        String image = imageField.getText();
        Integer cap = capSpinner.getValue();

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

        if (!title.equals(home.getTitle())){
            String sql = "select * from homes where Title = ?";
            PreparedStatement ps = dBmanager.connection.prepareStatement(sql);
            ps.setString(1, title);
            ResultSet set = ps.executeQuery();

            if (!set.next()){
                sql = "update homes set Title = ? where Id = ?";
                ps = dBmanager.connection.prepareStatement(sql);
                ps.setString(1, title);
                ps.setInt(2, home.getId());
                ps.executeUpdate();
                change = true;
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Input");
                alert.setHeaderText(null);
                alert.setContentText("The title already exists.");
                alert.showAndWait();
                return;
            }
        }

        //continue editing

        if (change){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Edit Successful");
            alert.setHeaderText(null);
            alert.setContentText("Your home has been edited successfully.");
            alert.showAndWait();
        }
    }

    public void removeHome(ActionEvent event) throws SQLException, IOException {
        DBmanager dBmanager = DBmanager.createConnection();
        String sql = "delete from homes where Id = ?";
        PreparedStatement ps = dBmanager.connection.prepareStatement(sql);
        ps.setInt(1, home.getId());
        ps.executeUpdate();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Deletion Successful");
        alert.setHeaderText(null);
        alert.setContentText("Your home has been deleted.");
        alert.showAndWait();

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
