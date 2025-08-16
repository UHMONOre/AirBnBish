package com.hotel.hotel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import static com.hotel.hotel.DBmanager.*;

public class Customer {
    private Integer id;
    private String username;
    private String email;
    private String password;
    private static Integer idCustomerHolder = 0;
    private static HashMap<Integer,Customer> CustomerMap = new HashMap<>();

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Customer(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
        this.id = idCustomerHolder;
        idCustomerHolder++;
    }

    public Customer() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public static void createCustomerTable(Statement statement) throws SQLException {
        ResultSet set = statement.executeQuery("Select Id,Username,Email,Password From customers");

        while (set.next()){
            Customer temp = new Customer();
            temp.setId(set.getInt("Id"));
            temp.setEmail(set.getString("Email"));
            temp.setUsername(set.getString("Username"));
            temp.setPassword(set.getString("Password"));
            CustomerMap.put(temp.getId(),temp);
            if (idCustomerHolder < temp.getId()){
                idCustomerHolder = temp.id;
            }
        }
        idCustomerHolder++;

        set.close();
    }

    public static Integer checkRegisterCustomer(String username, String email, String password, String conf){
        for (Customer customer : CustomerMap.values()){
            if (username.equals(customer.getUsername()) || email.equals(customer.getEmail())){
                return 1; //account exists
            }
        }
        if (password.equals(conf)){
            return 0;//all good
        }else {
            return 2;//passwords wrong
        }
    }

    public static Integer checkLoginCustomer(String username, String password){
        for (Customer customer : CustomerMap.values()){
            if (username.equals(customer.getUsername())) {
                if (password.equals(customer.getPassword())){
                    return 0; //success
                }else {
                    return 1; // wrong password
                }
            }
        }
        return 2; //wrong username
    }

    public static Customer retrieveCustomer(String username, String password){
        for (Customer customer : CustomerMap.values()){
            if (username.equals(customer.getUsername())){
                return customer;
            }
        }
        return null;
    }

    public static void createCustomer(String username, String email, String password) throws SQLException {
        DBmanager dBmanager = createConnection();
        Customer customer = new Customer(username, email, password);
        CustomerMap.put(idCustomerHolder, customer);
        String sql = "INSERT INTO customers (Id, Username, Email, Password) VALUES ("
                + customer.getId() + ", '"
                + customer.getUsername() + "', '"
                + customer.getEmail() + "', '"
                + customer.getPassword() + "')";
        dBmanager.statement.executeUpdate(sql);
        closeConnections(dBmanager);
    }
}
