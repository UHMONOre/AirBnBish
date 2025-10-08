package airBnBish;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HomeOwnerContrainerController {
    private Customer customer;
    private Home home;

    @FXML
    private Label titleLabel;

    @FXML
    private Label addressLabel;

    @FXML
    private Label bookingsLabel;

    public void initData(Customer customer, Home home){
        this.customer = customer;
        this.home = home;

        titleLabel.setText(home.getTitle());
        addressLabel.setText(home.getAddress());
        //continue
    }
}
