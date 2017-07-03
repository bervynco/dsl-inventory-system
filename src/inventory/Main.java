/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.DB;
import model.Item;
import model.Logs;
import model.Stock;
import model.Transactions;
import model.User;

/**
 *
 * @author L R E
 */
     

public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    private static String currentMenu = null;
    
    private static User sessionUser = null;
    DB db = new DB();
    private String itemStatus = null;
    public Main(User user, String currentMenu) throws SQLException, ClassNotFoundException, ParseException {
        initComponents();
        this.sessionUser = user;
        this.currentMenu = currentMenu;
        DefaultTableModel model = this.FillTable(this.currentMenu);
        tableList.setModel(model);
        //this.FillComboBox();
        employeeName.setText(this.sessionUser.getFullName());
        db.setLogStatus(this.sessionUser.getEmployeeID(), this.sessionUser.getFullName(), "Main Menu", "Visit");
        this.setLabelTitle();
        this.setButtonVisibility();
        
    }
    public void setLabelTitle(){
        System.out.println(this.currentMenu);
        if(this.currentMenu.equals("Inventory"))
            lblTitle.setText("List of Inventory Stocks");
        else if(this.currentMenu.equals("Transactions"))
            lblTitle.setText("List of Transactions");
        else if(this.currentMenu.equals("Users"))
            lblTitle.setText("List of Users");
        else if(this.currentMenu.equals("Items"))
            lblTitle.setText("List of Inventory Items");
        else if(this.currentMenu.equals("Logs")){
            lblTitle.setText("List of Logs");
        }
        else;
    }
    public void setButtonVisibility(){
        btnAdd.setLabel("Add");
        btnScan.setLabel("Scan");
        if(this.currentMenu.equals("Items")){
            btnAdd.setVisible(true);
            btnScan.setVisible(true);
        }
        else if(this.currentMenu.equals("Transactions")){
            btnAdd.setVisible(false);
            btnScan.setVisible(false);
        }
        else if(this.currentMenu.equals("Users")){
            btnAdd.setVisible(true);
            btnScan.setVisible(false);
        }
        else if(this.currentMenu.equals("Inventory")){
            btnAdd.setVisible(true);
            btnScan.setVisible(true);
        }
        else if(this.currentMenu.equals("Logs")){
            btnAdd.setVisible(false);
            btnScan.setVisible(false);
        }
    }
    
    public void setTableRenderer(){
        tableList.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus, int row, int col) {

                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

                if(currentMenu.equals("Inventory")){
                    if (itemStatus.equals("Bad")) {       
                        setBackground(Color.RED);
                        setForeground(Color.BLACK);
                    } else {
                        setBackground(table.getBackground());
                        setForeground(table.getForeground());
                    }
                }
                else{
                    setBackground(table.getBackground());
                    setForeground(table.getForeground());
                }

                return this;
            }   
        });
    }
//    public void FillComboBox() throws SQLException, ClassNotFoundException{
//        comboBoxEmployees.removeAllItems();
//        comboBoxEmployees.addItem("No Filter");
//        if(this.currentMenu.equals("Transactions")){
//            comboBoxEmployees.addItem("Montly");
//            comboBoxEmployees.addItem("Yearly");
//        }
//        else if(this.currentMenu.equals("Inventory")){
//            comboBoxEmployees.addItem("Within Threshold");
//        }
//        else if(this.currentMenu.equals("Users")){
//            List<User> employees = new ArrayList<User>();
//            employees = DB.getUsers();
//            for(int i = 0; i < employees.size(); i++){
//                String fullName = employees.get(i).getFullName();
//                comboBoxEmployees.addItem(fullName);
//            }
//        }
//
//    }
    public DefaultTableModel FillTable(String currentMenu) throws SQLException, ClassNotFoundException, ParseException{
        DefaultTableModel model = new DefaultTableModel();
        model.setRowCount(0);
        DB db = new DB();
        if(this.currentMenu.equals("Transactions")){
            List<Transactions> transactions = new ArrayList<Transactions>();

            transactions = db.getAllTransactions();
            model.addColumn("Employee ID");
            model.addColumn("Employee Name");
            model.addColumn("Item ID");
            model.addColumn("Item Name");
            model.addColumn("Quantity");
            model.addColumn("Type");
            model.addColumn("Transaction Date");
            
            for(int i = 0; i < transactions.size(); i++){
                this.setTableRenderer();
                Object [] rowData = {
                    transactions.get(i).getEmployeeID(), 
                    transactions.get(i).getEmployeeName(), 
                    transactions.get(i).getItemID(),
                    transactions.get(i).getItemName(), 
                    transactions.get(i).getQuantity(), 
                    transactions.get(i).getType(),
                    transactions.get(i).getTransactionDate()
                };
                model.addRow(rowData);
            }
        }
        else if(this.currentMenu.equals("Users")){
            List<User> employees = new ArrayList<User>();

            employees = db.getUsers();
            model.addColumn("Employee ID");
            model.addColumn("Name");
            model.addColumn("Role");

            for(int i = 0; i < employees.size(); i++){
                 this.setTableRenderer();
                Object [] rowData = {
                    employees.get(i).getEmployeeID(), 
                    employees.get(i).getFullName(), 
                    employees.get(i).getRole()
                };
                model.addRow(rowData);
            }
        }
        else if(this.currentMenu.equals("Items")){
            System.out.println("Items");
            List<Item> items = new ArrayList<Item>();

            items = db.getItems();
            model.addColumn("Item No");
            model.addColumn("Product Name");
            model.addColumn("Information");
            model.addColumn("IP Rate");
            model.addColumn("Kelvin");
            model.addColumn("Beam Angle");
            model.addColumn("Wattage");
            model.addColumn("Color Temp");
            model.addColumn("Batch No");
            model.addColumn("Row No");
            model.addColumn("Rack No");
            model.addColumn("Location No");
            model.addColumn("Threshold");
            model.addColumn("Production Date");
            model.addColumn("Lumens");
            model.addColumn("Cri");
            model.addColumn("Power");
            model.addColumn("Size");
            model.addColumn("AC");
            model.addColumn("DC");

            for(int i = 0; i < items.size(); i++){
                //System.out.println(items.get(i).getItemNo());
                this.setTableRenderer();
                Object [] rowData = {
                    items.get(i).getItemNo(),
                    items.get(i).getProductName(),
                    items.get(i).getInformation(),
                    items.get(i).getIpRate(),
                    items.get(i).getKelvin(),
                    items.get(i).getBeamAngle(),
                    items.get(i).getWattage(),
                    items.get(i).getColorTemp(),
                    items.get(i).getBatchNo(),
                    items.get(i).getRowNo(),
                    items.get(i).getRackNo(),
                    items.get(i).getLocationNo(),
                    items.get(i).getThreshold(),
                    items.get(i).getProductionDate(),
                    items.get(i).getLumens(),
                    items.get(i).getCri(),
                    items.get(i).getPower(),
                    items.get(i).getSize(),
                    items.get(i).getAc(),
                    items.get(i).getDc()
                };
                model.addRow(rowData);
            }
        }
        else if(this.currentMenu.equals("Inventory")){
            List<Stock> items = new ArrayList<Stock>();

            items = db.getAllStocks();
            model.addColumn("Item ID");
            model.addColumn("Item Name");
            model.addColumn("Quantity");
            model.addColumn("Threshold");
            model.addColumn("Status");
            model.addColumn("Replenished Date");
            
            for(int i = 0; i < items.size(); i++){
                this.itemStatus = "Good";
                if(items.get(i).getStatus() != 0){
                    this.itemStatus = "Bad";
                }
                this.setTableRenderer();
                Object [] rowData = {
                    items.get(i).getItemID(),
                    items.get(i).getItemName(),
                    items.get(i).getQuantity(),
                    items.get(i).getThreshold(),
                    this.itemStatus,
                    items.get(i).getReplenishDate()
                };
                model.addRow(rowData);
            }
        }
        else if(this.currentMenu.equals("Logs")){
            List<Logs> logs = new ArrayList<Logs>();

            logs = db.getLogs();
            model.addColumn("Employee ID");
            model.addColumn("Full Name");
            model.addColumn("Page Visited");
            model.addColumn("Action");
            model.addColumn("Timestamp");
            
            for(int i = 0; i < logs.size(); i++){
                this.setTableRenderer();
                Object [] rowData = {
                    logs.get(i).getEmployeeID(),
                    logs.get(i).getFullName(),
                    logs.get(i).getPageName(),
                    logs.get(i).getAction(),
                    logs.get(i).getTimestamp()
                };
                model.addRow(rowData);
            }
        }
        else;
        return model;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnScan = new java.awt.Button();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableList = new javax.swing.JTable();
        lblTitle = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        employeeName = new javax.swing.JLabel();
        btnAdd = new java.awt.Button();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuTransactions = new javax.swing.JMenu();
        menuUsers = new javax.swing.JMenu();
        menuInventory = new javax.swing.JMenu();
        menuItems = new javax.swing.JMenu();
        menuLogs = new javax.swing.JMenu();
        menuLogout = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnScan.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnScan.setLabel("Add");
        btnScan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnScanActionPerformed(evt);
            }
        });

        tableList.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        tableList.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tableList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableList.setRowHeight(25);
        tableList.setRowMargin(5);
        tableList.setSelectionBackground(new java.awt.Color(173, 216, 230));
        tableList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableList);

        lblTitle.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        lblTitle.setText("List of Employees");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel2.setText("Welcome, ");

        employeeName.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        employeeName.setForeground(new java.awt.Color(1, 169, 130));

        btnAdd.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnAdd.setLabel("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        jMenuBar1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N

        menuTransactions.setText("Transactions");
        menuTransactions.setToolTipText("");
        menuTransactions.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        menuTransactions.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuTransactionsMouseClicked(evt);
            }
        });
        jMenuBar1.add(menuTransactions);

        menuUsers.setText("Users");
        menuUsers.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        menuUsers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuUsersMouseClicked(evt);
            }
        });
        jMenuBar1.add(menuUsers);

        menuInventory.setText("Inventory");
        menuInventory.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        menuInventory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuInventoryMouseClicked(evt);
            }
        });
        jMenuBar1.add(menuInventory);

        menuItems.setText("List of Items");
        menuItems.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        menuItems.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuItemsMouseClicked(evt);
            }
        });
        jMenuBar1.add(menuItems);

        menuLogs.setText("Logs");
        menuLogs.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        menuLogs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuLogsMouseClicked(evt);
            }
        });
        jMenuBar1.add(menuLogs);

        menuLogout.setText("Log Out");
        menuLogout.setFont(new java.awt.Font("Segoe UI", 2, 16)); // NOI18N
        menuLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuLogoutMouseClicked(evt);
            }
        });
        jMenuBar1.add(menuLogout);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(btnScan, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(employeeName, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addContainerGap())
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1202, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(employeeName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnScan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 569, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuTransactionsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuTransactionsMouseClicked
        try {
            this.currentMenu = "Transactions";
            this.setLabelTitle();
            this.setButtonVisibility();
//            btnAdd.setVisible(false);
//            btnScan.setVisible(false);
            //lblTitle.setText("List of Transactions");
            //this.FillComboBox();
            DefaultTableModel model = this.FillTable(this.currentMenu);
            tableList.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuTransactionsMouseClicked

    private void menuUsersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuUsersMouseClicked
        try {
            // TODO add your handling code here:
            this.currentMenu = "Users";
            this.setLabelTitle();
            this.setButtonVisibility();
//            btnAdd.setLabel("Add");
//            btnAdd.setVisible(true);
            //lblTitle.setText("List of Users");
            //this.FillComboBox();
            DefaultTableModel model = this.FillTable(this.currentMenu);
            tableList.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuUsersMouseClicked

    private void menuInventoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuInventoryMouseClicked
        try {
            // TODO add your handling code here:
            this.currentMenu = "Inventory";
            this.setLabelTitle();
            this.setButtonVisibility();
//            btnScan.setVisible(true);
//            btnScan.setLabel("Scan");
//            btnAdd.setVisible(true);
//            btnAdd.setLabel("Add");
            //lblTitle.setText("List of Inventory Items");
            //this.FillComboBox();
            DefaultTableModel model = this.FillTable(this.currentMenu);
            tableList.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuInventoryMouseClicked

    private void menuLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuLogoutMouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_menuLogoutMouseClicked

    private void btnScanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnScanActionPerformed
        try {
            ScanPage scan = new ScanPage(this.sessionUser, this);
            scan.setTitle("DSL Inventory System | Scan");
            scan.pack();
            scan.setLocationRelativeTo(null);
            scan.setVisible(true);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnScanActionPerformed

    private void tableListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableListMouseClicked
        int row = tableList.rowAtPoint(evt.getPoint());
        this.setVisible(false);
        if(this.currentMenu.equals("Users")){
            try {
                int employeeID = (int) tableList.getValueAt(row, 0);
                EditUser edit = new EditUser(this.sessionUser, employeeID);
                edit.setTitle("DSL Inventory System | Edit User");
                edit.pack();
                edit.setLocationRelativeTo(null);
                edit.setVisible(true);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
       }
       else if(this.currentMenu.equals("Inventory")){
            try {
                String itemID = (String) tableList.getValueAt(row, 0);
                EditItem edit = new EditItem(this.sessionUser, Integer.parseInt(itemID));
                edit.setTitle("DSL Inventory System | Edit User");
                edit.pack();
                edit.setLocationRelativeTo(null);
                edit.setVisible(true);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
       }
       else if(this.currentMenu.equals("Transactions")){
           
       }
       else;
    }//GEN-LAST:event_tableListMouseClicked

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
          // TODO add your handling code here:
        this.setVisible(false);
        if(this.currentMenu.equals("Users")){
            try {
                AddUser add = new AddUser(this.sessionUser);
                add.setTitle("DSL Inventory System | Employees Sections");
                add.pack();
                add.setLocationRelativeTo(null);
                add.setVisible(true);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(this.currentMenu.equals("Items")){
            this.setVisible(false);
            AddNewItem newItem = new AddNewItem(this.sessionUser);
            newItem.setTitle("DSL Inventory System | Add New Item");
            newItem.pack();
            newItem.setLocationRelativeTo(null);
            newItem.setVisible(true);
        }
        else if(this.currentMenu.equals("Inventory")){
            this.setVisible(false);
            AddNewItem newItem = new AddNewItem(this.sessionUser);
            newItem.setTitle("DSL Inventory System | Add New Item");
            newItem.pack();
            newItem.setLocationRelativeTo(null);
            newItem.setVisible(true);
        }
        else;
    }//GEN-LAST:event_btnAddActionPerformed

    private void menuItemsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuItemsMouseClicked
         // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            this.currentMenu = "Items";
            this.setLabelTitle();
            this.setButtonVisibility();
//            btnScan.setVisible(true);
//            btnScan.setLabel("Scan");
//            btnAdd.setVisible(true);
//            btnAdd.setLabel("Add")
            //this.FillComboBox();
            DefaultTableModel model = this.FillTable(this.currentMenu);
            tableList.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuItemsMouseClicked

    private void menuLogsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuLogsMouseClicked
        // TODO add your handling code here: // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            this.currentMenu = "Logs";
            this.setLabelTitle();
            this.setButtonVisibility();
//            btnScan.setVisible(true);
//            btnScan.setLabel("Scan");
//            btnAdd.setVisible(true);
//            btnAdd.setLabel("Add")
            //this.FillComboBox();
            DefaultTableModel model = this.FillTable(this.currentMenu);
            tableList.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuLogsMouseClicked

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button btnAdd;
    private java.awt.Button btnScan;
    private javax.swing.JLabel employeeName;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JMenu menuInventory;
    private javax.swing.JMenu menuItems;
    private javax.swing.JMenu menuLogout;
    private javax.swing.JMenu menuLogs;
    private javax.swing.JMenu menuTransactions;
    private javax.swing.JMenu menuUsers;
    private javax.swing.JTable tableList;
    // End of variables declaration//GEN-END:variables
}
