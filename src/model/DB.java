package model;


import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DB {
    private static String user = "root";
    private static String pass = "password";
    private static String host = "localhost";
    private static String port = "3306";
    private static String url = "jdbc:mysql://" + host + ":" + port + "/dsl_inventory_system";
    
    public static Connection connect() throws ClassNotFoundException, SQLException {

       Connection conn = null;

       Class.forName("com.mysql.jdbc.Driver");

       conn = (Connection) DriverManager.getConnection(url, user, pass);
       
       return conn;
    }
    
    public static Timestamp getDateToday(){
        java.util.Date utilDate = new java.util.Date();
        java.sql.Timestamp sq = new java.sql.Timestamp(utilDate.getTime());  

        System.out.println(sq);
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        //System.out.println(sdf.format(sq));
        return sq;
    }
    public static User login(int employeeID, String password) throws ClassNotFoundException, SQLException{
        User user = new User();
        Connection c = connect();
        
        PreparedStatement ps = c.prepareStatement("Select employee_id, name, role from users where employee_id = ? and password = ?");
        ps.setInt(1, employeeID);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            user.setEmployeeID(rs.getInt(1));
            user.setFullName(rs.getString(2));
            user.setRole(rs.getString(3));
        }
        
        return user;
    }
    
    public static boolean determineDuplicateEmployeeID(int employeeID) throws ClassNotFoundException, SQLException{
        Connection c = connect();
        PreparedStatement ps = c.prepareStatement("Select * from users where employee_id = ? ");
        ps.setInt(1, employeeID);
        ResultSet rs = ps.executeQuery();
        
        
        return rs.first();
    }
    
    public static String registerUser(int employeeID, String name, String role, String password) throws ClassNotFoundException, ClassNotFoundException, SQLException{
        Connection c = connect();
        PreparedStatement ps = c.prepareStatement("INSERT INTO users (employee_id, name, role, password) VALUES (?,?,?,?)");
        boolean employeeIDExist = determineDuplicateEmployeeID(employeeID);
        if(!employeeIDExist){
            ps.setInt(1, employeeID);
            ps.setString(2, name);
            ps.setString(3, role);
            ps.setString(4, password);
        }
        else{
            return "Duplicate";
        }
        int rows = ps.executeUpdate();
        c.close();

        if(rows > 0){
            return "Successful";
        }
        else{
            return "Failed";
        }
    }
    public static String updateUser(int employeeID, String name, String role, String password) throws ClassNotFoundException, SQLException{
        Connection c = connect();
        PreparedStatement ps = c.prepareStatement("UPDATE users SET name = ?, role = ?, password = ? WHERE employee_id = ?");
        
        ps.setString(1, name);
        ps.setString(2, role);
        ps.setString(3, password);
        ps.setInt(4, employeeID);
        
        int affectedRow = ps.executeUpdate();
        c.close();
        if(affectedRow > 0){
            return "Successful";
        }
        else{
            return "Error";
        }
    }
    
    public static String deleteUser(int employeeID) throws ClassNotFoundException, SQLException{
        Connection c = connect();
        PreparedStatement ps = c.prepareStatement("Delete from users where employee_id = ?");
        ps.setInt(1, employeeID);
        int affectedRows = ps.executeUpdate();
        c.close();
        if(affectedRows != 0){
            return "Successful";
        }
        else{
            return "Failed";
        }
    }
    public static List<User> getUsers() throws SQLException, ClassNotFoundException{
        List<User> employees = new ArrayList<User>();
        Connection c = connect();
        
        PreparedStatement ps = c.prepareStatement("Select * from users");
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
                User user = new User();
                System.out.println(rs.getInt(2));
                user.setEmployeeID(rs.getInt(2));
                user.setFullName(rs.getString(3));
                user.setRole(rs.getString(4));
                
                employees.add(user);
        }
        c.close();
        return employees;
    }
    
    public static User getUserDetails(int employeeID) throws ClassNotFoundException, SQLException{
        Connection c = connect();
        PreparedStatement ps = c.prepareStatement("Select employee_id, name, role, password from users where employee_id = ?");
        ps.setInt(1, employeeID);
        ResultSet rs = ps.executeQuery();
        User user = new User();
        while(rs.next()){
            user.setEmployeeID(rs.getInt(1));
            user.setFullName(rs.getString(2));
            user.setRole(rs.getString(3));
            user.setPassword(rs.getString(4));
        }
        c.close();
        return user;
    }
    
     public static void setLogStatus(int employeeID, String name, String page, String action) throws ClassNotFoundException, SQLException, ParseException{
        Timestamp dateToday = DB.getDateToday();
        //java.sql.Date.
        //;
        Connection c = connect();
        PreparedStatement ps = c.prepareStatement("INSERT into logs(employee_id, name, page, action, log_date) VALUES (?,?,?,?,?)");
        ps.setInt(1, employeeID);
        ps.setString(2, name);
        ps.setString(3, page);
        ps.setString(4, action);
        ps.setTimestamp(5, dateToday);

        int rows = ps.executeUpdate();
        c.close();
    }
}
