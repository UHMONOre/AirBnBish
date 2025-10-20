package airBnBish;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.TreeSet;

public class OwnerBookingContainerController {
    private Customer customer;
    private Home home;
    private TreeSet<LocalDate> bookingDates;

    public void initData(Customer customer, Home home, TreeSet<LocalDate> bookingDates){
        this.customer = customer;
        this.home = home;
        this.bookingDates = bookingDates;
        System.out.println(customer.getId());
    }
}
