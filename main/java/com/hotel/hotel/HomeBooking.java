package com.hotel.hotel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class HomeBooking {
    private Integer bookingId;
    private Home home;
    private Customer customer;
    private LocalDate bookingDate;

    public HomeBooking(int bookingId, Home home, LocalDate bookingDate, Customer customer) {
        this.bookingId = bookingId;
        this.home = home;
        this.bookingDate = bookingDate;
        this.customer = customer;
    }

    public HomeBooking(Home home, LocalDate bookingDate, Customer customer) {
        this.home = home;
        this.bookingDate = bookingDate;
        this.customer = customer;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public static boolean checkAvailability(Home home, LocalDate startDate, LocalDate endDate) throws SQLException {
        DBmanager dBmanager = DBmanager.createConnection();
        ArrayList<LocalDate> dates = new ArrayList<>();
        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)){
            String sql = "select 1 from homebookings where HomeId = ? and BookingDate = ?";
            PreparedStatement ps = dBmanager.connection.prepareStatement(sql);
            ps.setInt(1, home.getId());
            ps.setDate(2, java.sql.Date.valueOf(date));
            ResultSet set = ps.executeQuery();
            if (set.next()){
                DBmanager.closeConnections(dBmanager);
                return false; // is booked
            }
        }
        DBmanager.closeConnections(dBmanager);
        return true; // is available
    }

    public static ArrayList<Home> checkBookedHomes(Customer customer) throws SQLException {
        ArrayList<Home> homeList = new ArrayList<>();
        DBmanager dBmanager = DBmanager.createConnection();
        String sql = "select distinct HomeId from homebookings where CustomerId = ?";
        PreparedStatement ps = dBmanager.connection.prepareStatement(sql);
        ps.setInt(1, customer.getId());
        ResultSet set = ps.executeQuery();
        while (set.next()){
            Home home = Home.retrieveHome(set.getInt("HomeId"));
            homeList.add(home);
        }
        return homeList;
    }
}
