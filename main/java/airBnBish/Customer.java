package airBnBish;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class Customer {
    private Integer id;
    private String username;
    private String email;
    private String password;

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
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

    public static Integer checkRegisterCustomer(String username, String email, String password, String conf) throws SQLException {
        DBmanager dBmanager = DBmanager.createConnection();
        String sql = "select Username, Email from customers";
        PreparedStatement ps = dBmanager.connection.prepareStatement(sql);
        ResultSet set = ps.executeQuery();

        DBmanager.closeConnections(dBmanager);

        if (set.next()) return 1; //account exists

        if (password.equals(conf)){
            return 0; //all good
        }else {
            return 2; // passwords wrong
        }
    }

    public static Integer checkLoginCustomer(String username, String password) throws SQLException {
        Customer customer = retrieveCustomer(username);

        if (customer == null){
            return 2; // wrong username
        }else {
            if (password.equals(customer.getPassword())){
                return 0; //success
            }else {
                return 1; // wrong password
            }
        }
    }

    public static Customer retrieveCustomer(String username) throws SQLException {
        DBmanager dBmanager = DBmanager.createConnection();
        String sql = "select * from customers where Username = ?";
        PreparedStatement ps = dBmanager.connection.prepareStatement(sql);
        ps.setString(1, username);
        ResultSet set = ps.executeQuery();

        if (set.next()){
            Customer temp = new Customer();

            temp.setId(set.getInt("Id"));
            temp.setEmail(set.getString("Email"));
            temp.setUsername(set.getString("Username"));
            temp.setPassword(set.getString("Password"));

            return temp;
        }else {
            return null;
        }
    }

    public static void createCustomer(String username, String email, String password) throws SQLException {
        DBmanager dBmanager = DBmanager.createConnection();
        String sql = "insert into customers (Username, Email, Password) values (?, ?, ?)";
        PreparedStatement ps = dBmanager.connection.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, email);
        ps.setString(3, password);
        ps.executeUpdate();
    }
}
