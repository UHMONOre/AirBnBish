package airBnBish;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class HomeOwnerDetailsController {
    private Customer customer;
    private Home home;

    @FXML
    private TextField titleField, priceField, urlField;

    @FXML
    private Spinner<Integer> capSpinner;

    @FXML
    private DatePicker startDate, endDate;

    public void initData(Customer customer, Home home){
        this.customer = customer;
        this.home = home;

        titleField.setText(home.getTitle());
        priceField.setText(home.getPrice().toString());
        urlField.setText(home.getImageURL());

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,8);
        valueFactory.setValue(home.getCapacity());
        capSpinner.setValueFactory(valueFactory);
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
