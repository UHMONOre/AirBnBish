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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public void checkEdit(ActionEvent event) throws SQLException {
        customer = Customer.retrieveCustomer(customer.getId());

        boolean proceed = true;

        String username = userField.getText();
        String email = emailField.getText();
        String password = passField.getText();
        String passwordConf = passConfField.getText();

        DBmanager dBmanager = DBmanager.createConnection();

        if ( !(password.isEmpty() && passwordConf.isEmpty()) ){
            if (password.equals(customer.getPassword()) || passwordConf.equals(customer.getPassword())){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setContentText("The new password cannot be the same as the current password.");
                alert.showAndWait();
                proceed = false;
            }

            if (proceed){
                if (password.equals(passwordConf)){
                    String sql = "update customers set Password = ? where Id = ?";
                    PreparedStatement ps = dBmanager.connection.prepareStatement(sql);
                    ps.setString(1, password);
                    ps.setInt(2, customer.getId());
                    ps.executeUpdate();

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Password Changed");
                    alert.setContentText("Your password has been successfully changed.");
                    alert.showAndWait();
                }else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setContentText("The password and confirmation do not match.");
                    alert.showAndWait();
                }
            }
        }

        if ( !(username.isEmpty() || email.isEmpty()) ){
            //username
            String sql = "select Id from customers where Username = ?";
            PreparedStatement ps = dBmanager.connection.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet set = ps.executeQuery();

            if (!username.equals(customer.getUsername())){
                if (set.next()){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setContentText("Username exists.");
                    alert.showAndWait();
                }else {
                    sql = "update customers set Username = ? where Id = ?";
                    ps = dBmanager.connection.prepareStatement(sql);
                    ps.setString(1, username);
                    ps.setInt(2, customer.getId());
                    ps.executeUpdate();

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Username Changed");
                    alert.setContentText("Your username has been successfully changed.");
                    alert.showAndWait();
                }
            }

            //email
            sql = "select Id from customers where Email = ?";
            ps = dBmanager.connection.prepareStatement(sql);
            ps.setString(1, email);
            set = ps.executeQuery();

            if (!email.equals(customer.getEmail())){
                if (set.next()){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setContentText("Email exists.");
                    alert.showAndWait();
                }else {
                    sql = "update customers set Email = ? where Id = ?";
                    ps = dBmanager.connection.prepareStatement(sql);
                    ps.setString(1, email);
                    ps.setInt(2, customer.getId());
                    ps.executeUpdate();

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Email Changed");
                    alert.setContentText("Your email has been successfully changed.");
                    alert.showAndWait();
                }
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText("Username or email cannot be empty!");
            alert.showAndWait();
        }
    }

    public void returnAction(ActionEvent event) throws IOException, SQLException {
        customer = Customer.retrieveCustomer(customer.getId());

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
