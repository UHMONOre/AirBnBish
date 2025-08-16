package airBnBish;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.time.LocalDate;

public class BookingContainerController {
    private Home home;
    private LocalDate startDate;
    private LocalDate endDate;

    @FXML
    private Label titleLabel;

    @FXML
    private Label addressLabel;

    @FXML
    private Label inLabel;

    @FXML
    private Label outLabel;

    public void initDate(Home home, LocalDate startDate, LocalDate endDate){
        this.home = home;
        this.startDate = startDate;
        this.endDate = endDate;

        titleLabel.setText(home.getTitle());
        addressLabel.setText(home.getAddress() + " " + home.getCountry() + ", " + home.getCity());
        inLabel.setText(startDate.toString());
        outLabel.setText(endDate.toString());
    }
}
