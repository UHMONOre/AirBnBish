package airBnBish;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
    private Integer customerId;

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
                ", imageURL='" + imageURL + '\'' +
                ", customerId='" + customerId + '\'' +
                '}';
    }

    public Home(String address, String title, String country, String city, Integer capacity, Integer id, Double price, String imageURL, Integer customerId) {
        this.address = address;
        this.title = title;
        this.country = country;
        this.city = city;
        this.capacity = capacity;
        this.id = id;
        this.price = price;
        this.imageURL = imageURL;
        this.customerId = customerId;
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

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public static void arrangeCountryCity (TreeSet<String> countries, LinkedHashMap<String, TreeSet<String>> countryCity) throws SQLException {
        DBmanager dBmanager = DBmanager.createConnection();
        String sql = "select Country, City from homes";
        PreparedStatement ps = dBmanager.connection.prepareStatement(sql);
        ResultSet set = ps.executeQuery();

        while (set.next()) {
            String country = set.getString("Country");
            String city = set.getString("City");

            countries.add(country);
            countryCity.putIfAbsent(country, new TreeSet<>());
            countryCity.get(country).add(city);
        }
    }

    public static Home retrieveHome(Integer id) throws SQLException {
        DBmanager dBmanager = DBmanager.createConnection();
        String sql = "select * from homes where Id = ?";
        PreparedStatement ps = dBmanager.connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet set = ps.executeQuery();

        if (set.next()){
            Home tempHome = new Home();

            tempHome.setId(set.getInt("Id"));
            tempHome.setAddress(set.getString("Address"));
            tempHome.setTitle(set.getString("Title"));
            tempHome.setCountry(set.getString("Country"));
            tempHome.setCity(set.getString("City"));
            tempHome.setCapacity(set.getInt("Capacity"));
            tempHome.setPrice(set.getDouble("Price"));
            tempHome.setImageURL(set.getString("Image"));
            tempHome.setCustomerId(set.getInt("CustomerId"));

            return tempHome;
        }
        return null;
    }
}
