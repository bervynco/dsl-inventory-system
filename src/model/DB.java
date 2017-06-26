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
    
    public Integer getIdFromEmployeeName(String name) throws ClassNotFoundException, SQLException{
        Connection c = connect();
        PreparedStatement ps = c.prepareStatement("Select employee_id from users where name = ?");
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        int employeeID = 0;
        while(rs.next()){
            employeeID = rs.getInt(1);
        }
        
        return employeeID;
    }
    
    public static List<User> filterUsers(int employeeID) throws SQLException, ClassNotFoundException{
        List<User> employees = new ArrayList<User>();
        Connection c = connect();
        
        PreparedStatement ps = c.prepareStatement("Select * from users where employee_id = ?");
        ps.setInt(1, employeeID);
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

    public String addItem(String ac, String batchNo, String colorTemp, String cri, String dc, String image, String information, String ipRate, String itemNo, String kelvin,
            String locationNo, String lumens, String power, String productName, String rackNo, String remarks,
            String rowNo, String size, int quantity, int threshold, String wattage, String beamAngle, String productionDate) throws ClassNotFoundException, SQLException {
        Connection c = connect();
        int itemID = Integer.parseInt(itemNo);
        PreparedStatement ps = c.prepareStatement("INSERT INTO items (item_id, product_name, information, ip_rate, kelvin, beam_angle, wattage,"+
                " color_temp, batch_no, row_no, rack_no, location_no, quantity, threshold, production_date, lumens, cri, power, size, ac, dc, remark"+
                ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        ps.setInt(1, itemID);
        ps.setString(2, productName);
        ps.setString(3, information);
        ps.setString(4, ipRate);
        ps.setString(5, kelvin);
        ps.setString(6, beamAngle);
        ps.setString(7, wattage);
        ps.setString(8, colorTemp);
        ps.setString(9, batchNo);
        ps.setString(10, rowNo);
        ps.setString(11, rackNo);
        ps.setString(12, locationNo);
        ps.setInt(13, quantity);
        ps.setInt(14, threshold);
        ps.setString(15, productionDate);
        ps.setString(16, lumens);
        ps.setString(17, cri);
        ps.setString(18, power);
        ps.setString(19, size);
        ps.setString(20, ac);
        ps.setString(21, dc);
        ps.setString(22, remarks);
        
        int rows = ps.executeUpdate();
        if(rows > 0){
            return "Successful";
        }
        else{
            return "Failed";
        }
    }
    
    public static List<Item> getItems() throws ClassNotFoundException, SQLException{
        List<Item> items = new ArrayList<Item>();
        Connection c = connect();
        
        PreparedStatement ps = c.prepareStatement("Select * from items order by status DESC");
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Item item = new Item();
            item.setItemNo(Integer.toString(rs.getInt(2)));
            item.setProductName(rs.getString(3));
            item.setInformation(rs.getString(4));
            item.setIpRate(rs.getString(5));
            item.setKelvin(rs.getString(6));
            item.setBeamAngle(rs.getString(7));
            item.setWattage(rs.getString(8));
            item.setColorTemp(rs.getString(9));
            item.setBatchNo(rs.getString(10));
            item.setRowNo(rs.getString(11));
            item.setRackNo(rs.getString(12));
            item.setLocationNo(rs.getString(13));
            item.setQuantity(rs.getInt(14));
            item.setThreshold(rs.getInt(15));
            item.setProductionDate(rs.getString(16));
            item.setLumens(rs.getString(17));
            item.setCri(rs.getString(18));
            item.setPower(rs.getString(19));
            item.setSize(rs.getString(20));
            item.setAc(rs.getString(21));
            item.setDc(rs.getString(22));
            item.setRemarks(rs.getString(23));
            
            items.add(item);
            
        }
        c.close();
        return items;
    }
    
    public static Item getItemDetails(int itemID) throws ClassNotFoundException, SQLException{
        Connection c = connect();
        PreparedStatement ps = c.prepareStatement("Select * from items where item_id = ?");
        ps.setInt(1, itemID);
        ResultSet rs = ps.executeQuery();
        Item item = new Item();
        while(rs.next()){
            item.setItemID(rs.getInt(2));
            item.setProductName(rs.getString(3));
            item.setInformation(rs.getString(4));
            item.setIpRate(rs.getString(5));
            item.setKelvin(rs.getString(6));
            item.setBeamAngle(rs.getString(7));
            item.setWattage(rs.getString(8));
            item.setColorTemp(rs.getString(9));
            item.setBatchNo(rs.getString(10));
            item.setRowNo(rs.getString(11));
            item.setRackNo(rs.getString(12));
            item.setLocationNo(rs.getString(13));
            item.setQuantity(rs.getInt(14));
            item.setThreshold(rs.getInt(15));
            item.setProductionDate(rs.getString(16));
            item.setLumens(rs.getString(17));
            item.setCri(rs.getString(18));
            item.setPower(rs.getString(19));
            item.setSize(rs.getString(20));
            item.setAc(rs.getString(21));
            item.setDc(rs.getString(22));
            item.setRemarks(rs.getString(23));
        }
        c.close();
        return item;
    }

    public static String updateItems(String ac, String batchNo, String colorTemp, String cri, String dc, String image, String information, String ipRate, 
            String itemID, String kelvin, String locationNo, String lumens, String power, String productName, String rackNo, String remarks, String rowNo, String size, int quantity,
            int threshold, String wattage, String beamAngle, String productionDate) throws ClassNotFoundException, SQLException {
        Connection c = connect();
        PreparedStatement ps = c.prepareStatement("UPDATE items SET ac = ?, batch_no = ?,color_temp = ?, cri = ?, dc = ?, image = ?, information = ?, ip_rate = ?, "+
                " kelvin = ?,location_no = ?, lumens = ?, power = ?, product_name = ?, rack_no = ?, remark = ?, row_no = ?, size = ?, quantity = ?, threshold = ?, wattage = ?, beam_angle = ?,"+
                "production_date = ? WHERE item_id = ?");
        
        ps.setString(1, ac);
        ps.setString(2, batchNo);
        ps.setString(3, colorTemp);
        ps.setString(4, cri);
        ps.setString(5, dc);
        ps.setString(6, "");
        ps.setString(7, information);
        ps.setString(8, ipRate);
        ps.setString(9, kelvin);
        ps.setString(10, locationNo);
        ps.setString(11, lumens);
        ps.setString(12, power);
        ps.setString(13, productName);
        ps.setString(14, rackNo);
        ps.setString(15, remarks);
        ps.setString(16, rowNo);
        ps.setString(17, size);
        ps.setInt(18, quantity);
        ps.setInt(19, threshold);
        ps.setString(20, wattage);
        ps.setString(21, beamAngle);
        ps.setString(22, productionDate);
        ps.setInt(23, Integer.parseInt(itemID));
        
        int affectedRow = ps.executeUpdate();
        c.close();
        if(affectedRow > 0){
            return "Successful";
        }
        else{
            return "Error";
        }
    }

    public String deleteItem(int itemID) throws ClassNotFoundException, SQLException {
        Connection c = connect();
        PreparedStatement ps = c.prepareStatement("Delete from items where item_id = ?");
        ps.setInt(1, itemID);
        int affectedRows = ps.executeUpdate();
        c.close();
        if(affectedRows != 0){
            return "Successful";
        }
        else{
            return "Failed";
        }
    }
    
    public static List<Item> filterItems(String filter) throws ClassNotFoundException, SQLException{
        List<Item> items = new ArrayList<Item>();
        Connection c = connect();
        
        PreparedStatement ps = null;
        if(filter.equals("No Filter")){
            ps = c.prepareStatement("Select * from items"); 
        }
        else{
            ps = c. prepareStatement("Select * from items where quantity <= threshold");
        }
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            Item item = new Item();
            item.setItemNo(Integer.toString(rs.getInt(2)));
            item.setProductName(rs.getString(3));
            item.setInformation(rs.getString(4));
            item.setIpRate(rs.getString(5));
            item.setKelvin(rs.getString(6));
            item.setBeamAngle(rs.getString(7));
            item.setWattage(rs.getString(8));
            item.setColorTemp(rs.getString(9));
            item.setBatchNo(rs.getString(10));
            item.setRowNo(rs.getString(11));
            item.setRackNo(rs.getString(12));
            item.setLocationNo(rs.getString(13));
            item.setQuantity(rs.getInt(14));
            item.setThreshold(rs.getInt(15));
            item.setProductionDate(rs.getString(16));
            item.setLumens(rs.getString(17));
            item.setCri(rs.getString(18));
            item.setPower(rs.getString(19));
            item.setSize(rs.getString(20));
            item.setAc(rs.getString(21));
            item.setDc(rs.getString(22));
            item.setRemarks(rs.getString(23));
            
            items.add(item);
            
        }
        c.close();
        return items;
    }
    public List<Transactions> getAllTransactions() throws ClassNotFoundException, SQLException{
        ArrayList<Transactions>transactions = new ArrayList<Transactions>();
        
        Connection c = connect();
        PreparedStatement ps = c.prepareStatement("Select employeeID, employeeName, itemID,"+
                " item, quantity, type, transactionDate from transactions");
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Transactions transaction = new Transactions();
            transaction.setEmployeeID(rs.getInt(1));
            transaction.setEmployeeName(rs.getString(2));
            transaction.setItemID(rs.getInt(3));
            transaction.setItemName(rs.getString(4));
            transaction.setQuantity(rs.getInt(5));
            transaction.setType(rs.getString(6));
            transaction.setTransactionDate(rs.getDate(7));
            transactions.add(transaction);
        }
        return transactions;
    }
    
    public List<Transactions> filterTransactions(String filter) throws ClassNotFoundException, SQLException{
        ArrayList<Transactions>transactions = new ArrayList<Transactions>();
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        Connection c = connect();
        PreparedStatement ps = null;
        if(filter.equals("Monthly")){
            ps = c.prepareStatement("Select employeeID, employeeName, itemID,"+
                " item, quantity, type, transactionDate from transactions where month(transactionDate) = ? and year(transactionDate)=?");
            ps.setInt(1, month);
            ps.setInt(2, year);
        }
        else if(filter.equals("Yearly")){
            ps = c.prepareStatement("Select employeeID, employeeName, itemID,"+
                " item, quantity, type, transactionDate from transactions where year(transactionDate)=?");
            ps.setInt(1, year);
        }
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Transactions transaction = new Transactions();
            transaction.setEmployeeID(rs.getInt(1));
            transaction.setEmployeeName(rs.getString(2));
            transaction.setItemID(rs.getInt(3));
            transaction.setItemName(rs.getString(4));
            transaction.setQuantity(rs.getInt(5));
            transaction.setType(rs.getString(6));
            transaction.setTransactionDate(rs.getDate(7));
            transactions.add(transaction);
        }
        return transactions;
    }
    
    
}
