/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import model.DB;
import model.Item;
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
    private static String currentMenu = "Employees";
    private static User sessionUser = null;
    public Main(User user, String currentMenu) throws SQLException, ClassNotFoundException, ParseException {
        initComponents();
        this.sessionUser = user;
        this.currentMenu = currentMenu;
        DefaultTableModel model = this.FillTable(this.currentMenu);
        tableList.setModel(model);
        this.FillEmployeesComboBox();
        employeeName.setText(this.sessionUser.getFullName());
        btnEdit.setVisible(false);
        btnDelete.setVisible(false);
        btnAdd.setVisible(false);
    }
    public void FillEmployeesComboBox() throws SQLException, ClassNotFoundException{
        List<User> employees = new ArrayList<User>();
        employees = DB.getUsers();
        comboBoxEmployees.addItem("No Filter");
        if(this.currentMenu.equals("Transaction")){
            
        }
        else if(this.currentMenu.equals("Inventory")){
            
        }
        else if(this.currentMenu.equals("Users")){
            
        }
//        for(int i = 0; i < employees.size(); i++){
//            String fullName = employees.get(i).getFirstName()+ " " + employees.get(i).getLastName();
//            comboBoxEmployees.addItem(fullName);
//        }
    }
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
                Object [] rowData = {employees.get(i).getEmployeeID(), employees.get(i).getFullName(), employees.get(i).getRole()};
                model.addRow(rowData);
            }
        }
        else if(this.currentMenu.equals("Inventory")){
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
            model.addColumn("Remarks");

            for(int i = 0; i < items.size(); i++){
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
                    items.get(i).getDc(),
                    items.get(i).getRemarks()
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

        jLabel1 = new javax.swing.JLabel();
        comboBoxEmployees = new javax.swing.JComboBox<>();
        btnAdd = new java.awt.Button();
        btnEdit = new java.awt.Button();
        btnDelete = new java.awt.Button();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableList = new javax.swing.JTable();
        lblTitle = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        employeeName = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuTransactions = new javax.swing.JMenu();
        menuUsers = new javax.swing.JMenu();
        menuInventory = new javax.swing.JMenu();
        menuLogout = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Filter:");

        comboBoxEmployees.setEditable(true);
        comboBoxEmployees.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        comboBoxEmployees.setModel(new javax.swing.DefaultComboBoxModel<>());
        comboBoxEmployees.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxEmployeesActionPerformed(evt);
            }
        });

        btnAdd.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnAdd.setLabel("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnEdit.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnEdit.setLabel("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 51, 51));
        btnDelete.setLabel("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
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

        jMenuBar1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N

        menuTransactions.setText("Transactions");
        menuTransactions.setToolTipText("");
        menuTransactions.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        menuTransactions.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuTransactionsMouseClicked(evt);
            }
        });
        menuTransactions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuTransactionsActionPerformed(evt);
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(comboBoxEmployees, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(employeeName, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(employeeName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEdit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addComponent(comboBoxEmployees, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 623, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuTransactionsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuTransactionsMouseClicked
        try {
            this.currentMenu = "Transactions";
            btnEdit.setVisible(false);
            btnDelete.setVisible(false);
            btnAdd.setVisible(false);
            lblTitle.setText("List of Transactions");
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

    private void menuTransactionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuTransactionsActionPerformed

    }//GEN-LAST:event_menuTransactionsActionPerformed

    private void menuUsersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuUsersMouseClicked
        try {
            // TODO add your handling code here:
            this.currentMenu = "Users";
            btnEdit.setVisible(true);
            btnDelete.setVisible(true);
            btnAdd.setVisible(true);
            lblTitle.setText("List of Users");
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
            btnEdit.setVisible(true);
            btnDelete.setVisible(true);
            btnAdd.setVisible(true);
            btnAdd.setLabel("Add");
            lblTitle.setText("List of Inventory Items");
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

    private void comboBoxEmployeesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxEmployeesActionPerformed
//        try {
//            // TODO add your handling code here:
//            String employeeName = (String) comboBoxEmployees.getSelectedItem();
//            if(employeeName.equals("No Filter")){
//                this.currentEmployeeFilter = 0;
//            }
//            else{
//                this.currentEmployeeFilter = DB.getEmployeeIDFromName(employeeName);
//            }
//            DefaultTableModel model = this.FillTable(this.currentMenu);
//            tableList.setModel(model);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ParseException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_comboBoxEmployeesActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
//        // TODO add your handling code here:
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

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
       
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void tableListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableListMouseClicked
       
    }//GEN-LAST:event_tableListMouseClicked

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button btnAdd;
    private java.awt.Button btnDelete;
    private java.awt.Button btnEdit;
    private javax.swing.JComboBox<String> comboBoxEmployees;
    private javax.swing.JLabel employeeName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JMenu menuInventory;
    private javax.swing.JMenu menuLogout;
    private javax.swing.JMenu menuTransactions;
    private javax.swing.JMenu menuUsers;
    private javax.swing.JTable tableList;
    // End of variables declaration//GEN-END:variables
}
