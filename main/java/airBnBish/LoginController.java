package airBnBish;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import static airBnBish.Customer.*;

public class LoginController {
    @FXML
    private TextField userField;

    @FXML
    private PasswordField passField;

    @FXML
    private Button loginButton;

    public void initialize(){
        userField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                loginButton.fire();
            }
        });

        passField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                loginButton.fire();
            }

        });
    }

    public void Login(ActionEvent event) throws IOException, SQLException {
        String username = userField.getText();
        String password = passField.getText();

        if (username.isEmpty() || password.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input Error");
            alert.setContentText("Username and Password fields must not be empty");
            alert.showAndWait();
            return;
        }

        Integer flag = checkLoginCustomer(username,password);

        if (flag == 0){
            Customer customer = retrieveCustomer(username);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AccountPage.fxml"));
            Parent root = loader.load();

            AccountPageController controller = loader.getController();
            controller.initDate(customer);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else if (flag == 1) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input Error");
            alert.setContentText("Password is wrong");
            alert.showAndWait();
        } else if (flag == 2) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input Error");
            alert.setContentText("Account/Username not found");
            alert.showAndWait();
        }
    }

    public void Register(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Register.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
