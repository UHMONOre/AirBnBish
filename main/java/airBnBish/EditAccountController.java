package airBnBish;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class EditAccountController {
    private Customer customer;

    @FXML
    private TextField userField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passField;

    @FXML
    private PasswordField passConfField;

    public void initData(Customer customer){
        this.customer = customer;

        userField.setText(customer.getUsername());
        emailField.setText(customer.getEmail());
    }

    public void checkEdit(ActionEvent event){
        String username = userField.getText();
        String email = emailField.getText();
        String password = passField.getText();
        String passwordConf = passConfField.getText();

        if (username.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText("Username cannot be empty!");
            alert.showAndWait();
        }

        //finish this
        //maybe make the check on customer???
        //fix the home and customer dependency on maps -> sql
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
