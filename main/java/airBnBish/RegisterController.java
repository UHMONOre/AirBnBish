package airBnBish;

import javafx.animation.PauseTransition;
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
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;

import static airBnBish.Customer.*;

public class RegisterController {
    @FXML
    private TextField userField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passField;
    @FXML
    private PasswordField confField;
    @FXML
    private Button regButton;

    public void initialize(){
        userField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                regButton.fire();
            }
        });

        emailField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                regButton.fire();
            }

        });

        passField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                regButton.fire();
            }

        });

        confField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                regButton.fire();
            }

        });
    }

    public void Register(ActionEvent event) throws SQLException, IOException {
        String username = userField.getText();
        String email = emailField.getText();
        String password = passField.getText();
        String conf = confField.getText();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || conf.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input Error");
            alert.setContentText("All fields must not be empty");
            alert.showAndWait();
            return;
        }

        Integer flag = checkRegisterCustomer(username, email, password, conf);
        if (flag == 0){
            createCustomer(username, email, password);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registration Successful");
            alert.setContentText("Account was successfully created");
            alert.show();
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(event1 -> alert.close());
            delay.play();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
        }else if (flag == 1) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input Error");
            alert.setContentText("A account already exist with that username/email");
            alert.showAndWait();
        }else if (flag == 2){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input Error");
            alert.setContentText("Password and it's confirmation must be the same");
            alert.showAndWait();
        }
    }

    public void Return(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
}
