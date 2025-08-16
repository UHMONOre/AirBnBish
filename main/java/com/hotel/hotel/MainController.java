package com.hotel.hotel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.TreeSet;

import static com.hotel.hotel.Home.arrangeCountryCity;

public class MainController {
    private Customer customer;
    private String city;
    private LocalDate startDate;
    private LocalDate endDate;

    @FXML
    private Spinner<Integer> guestSpinner;

    @FXML
    private TreeView<String> treeView;

    @FXML
    private Label cityLabel;

    @FXML
    private DatePicker dateIn;

    @FXML
    private DatePicker dateOut;

    public void initData(Customer customer){
        this.customer = customer;
    }

    public void returnInitData(Customer customer){
        this.customer = customer;
    }

    @FXML
    private void initialize(){
        //spinner
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,8);
        valueFactory.setValue(1);
        guestSpinner.setValueFactory(valueFactory);

        //tree view
        TreeSet<String> countries = new TreeSet<>();
        LinkedHashMap<String, TreeSet<String>> countryCity = new LinkedHashMap<>();
        arrangeCountryCity(countries, countryCity);

        TreeItem<String> masterRoot = new TreeItem<>("Countries");
        for (String key : countryCity.keySet()){
            TreeSet<String> cities = countryCity.get(key);
            TreeItem<String> rootItem = new TreeItem<>(key);

            for (String city : cities){
                TreeItem<String> branchItem = new TreeItem<>(city);
                rootItem.getChildren().add(branchItem);
            }
            masterRoot.getChildren().add(rootItem);
        }
        treeView.setShowRoot(false);
        treeView.setRoot(masterRoot);
    }

    public void selectItem(){
        TreeItem<String> item = treeView.getSelectionModel().getSelectedItem();
        if (item != null){
            if (!item.getChildren().isEmpty()){
                treeView.getSelectionModel().clearSelection();
            }else {
                city = item.getValue();
                cityLabel.setText(city);
            }
        }
    }

    public boolean checkDates(){
        LocalDate today = LocalDate.now();
        LocalDate maxDate = today.plusYears(2);
        LocalDate tempStartDate = dateIn.getValue();
        LocalDate tempEndDate = dateOut.getValue();

        //check null
        if (tempStartDate == null || tempEndDate == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input Error");
            alert.setContentText("Please select both Check-in and Check-out dates");
            alert.showAndWait();
            return false;
        }

        //check similarity
        if (tempStartDate.equals(tempEndDate)){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input Error");
            alert.setContentText("The check-in date must not be the same as the check-out date");
            alert.showAndWait();
            return false;
        }

        //check range
        if (tempStartDate.isBefore(today) || tempStartDate.isAfter(maxDate)){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input Error");
            alert.setContentText("Check-in date must be between today and " + maxDate + ".");
            alert.showAndWait();
            return false;
        }
        if (tempEndDate.isBefore(today) || tempEndDate.isAfter(maxDate)){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input Error");
            alert.setContentText("Check-out date must be between today and " + maxDate + ".");
            alert.showAndWait();
            return false;
        }

        //check startDate after endDate
        if (!tempStartDate.isBefore(tempEndDate)){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input Error");
            alert.setContentText("The check-in date must be before the check-out date");
            alert.showAndWait();
            return false;
        }

        startDate = tempStartDate;
        endDate = tempEndDate;
        return true;
    }

    public void search(ActionEvent event) throws IOException, SQLException {
        if (checkDates()){
            if (city != null){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeSelect.fxml"));
                Parent root = loader.load();

                HomeSelectController homeSelectController = loader.getController();
                int currentValue = guestSpinner.getValue();
                homeSelectController.initData(customer, city, startDate, endDate, currentValue);

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Input Error");
                alert.setContentText("A city must be selected");
                alert.showAndWait();
            }
        }
    }

    public void returnAction(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AccountPage.fxml"));
        Parent root = loader.load();

        AccountPageController controller = loader.getController();
        controller.initDate(customer);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
}
