package airBnBish;

import java.time.LocalDate;

public class BookingDetailsController {
    private Customer customer;
    private Home home;
    private LocalDate startDate;
    private LocalDate endDate;

    public void initData(Customer customer, Home home, LocalDate startDate, LocalDate endDate){
        this.customer = customer;
        this.home = home;
        this.startDate = startDate;
        this.endDate = endDate;

    }
}
