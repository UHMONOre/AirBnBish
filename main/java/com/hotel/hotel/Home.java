package com.hotel.hotel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeSet;

public class Home {
    private String address;
    private String title;
    private String country;
    private String city;
    private Integer capacity;
    private Integer id;
    private Double price;
    private String imageURL;
    private static Integer idHomeHolder = 0;
    private static HashMap<Integer,Home> homeMap = new HashMap<>();

    @Override
    public String toString() {
        return "Home{" +
                "address='" + address + '\'' +
                ", title='" + title + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", capacity=" + capacity +
                ", id=" + id +
                ", price=" + price +
                '}';
    }

    public Home(String address, String title, String country, String city, Integer capacity, Integer id, Double price, String imageURL) {
        this.address = address;
        this.title = title;
        this.country = country;
        this.city = city;
        this.capacity = capacity;
        this.price = price;
        this.id = id;
        this.imageURL = imageURL;
    }

    public Home(String address, String title, String country, String city, Integer capacity, Double price, String imageURL) {
        this.address = address;
        this.title = title;
        this.country = country;
        this.city = city;
        this.capacity = capacity;
        this.price = price;
        this.imageURL = imageURL;
        this.id = idHomeHolder;
        idHomeHolder++;
    }

    public Home(){}

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Integer getId() {
        return id;
    }

    public void setPrice(Double price){
        this.price = price;
    }

    public Double getPrice(){
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public static void createHomeTable(Statement statement) throws SQLException {
        ResultSet set = statement.executeQuery("Select Address,Capacity,City,Country,Id,Title,Price,Image From homes");

        while (set.next()){
            Home temp = new Home();
            temp.setAddress(set.getString("Address"));
            temp.setCapacity(set.getInt("Capacity"));
            temp.setCity(set.getString("City"));
            temp.setCountry(set.getString("Country"));
            temp.setId(set.getInt("Id"));
            temp.setTitle(set.getString("Title"));
            temp.setPrice(set.getDouble("Price"));
            temp.setImageURL(set.getString("Image"));
            homeMap.put(temp.getId(), temp);
            if (idHomeHolder < temp.getId()){
                idHomeHolder = temp.getId();
            }
        }
        idHomeHolder++;
        set.close();
    }

    public static void arrangeCountryCity (TreeSet<String> countries, LinkedHashMap<String, TreeSet<String>> countryCity){
        for (Home home : homeMap.values()){
            countries.add(home.getCountry());
            countryCity.putIfAbsent(home.getCountry(), new TreeSet<>());
            countryCity.get(home.getCountry()).add(home.city);
        }
    }

    public static Home retrieveHome(Integer id){
        for (Home home : homeMap.values()){
            if (home.getId().equals(id)){
                return home;
            }
        }
        return null;
    }
}
