package com.hotel.hotel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;

import java.io.IOException;

import static com.hotel.hotel.Customer.*;
import static com.hotel.hotel.DBmanager.*;
import static com.hotel.hotel.Home.*;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Login.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Hotel");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        try {
            DBmanager dBmanager = createConnection();

            createCustomerTable(dBmanager.statement);
            createHomeTable(dBmanager.statement);

            launch();

            closeConnections(dBmanager);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}