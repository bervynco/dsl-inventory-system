/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.DB;
import model.Item;
import model.User;

/**
 *
 * @author bendrhick
 */
public class EditItem extends javax.swing.JFrame {

    /**
     * Creates new form EditItem
     */
    private static User sessionUser = null;
    private static String action = null;
    private static String pageFrom = null;
    private final JPanel panel = new JPanel();
    DB db = new DB();
    public void setEditableFields(int itemID) throws ClassNotFoundException, SQLException, IOException{
        System.out.println(itemID);
        Item item = new Item();
        item =  db.getItemDetails(itemID);
        // String[] name = user.getFullName().split(" ");
        txtAc.setText(item.getAc());
        txtBatchNo.setText(item.getBatchNo());
        txtBeamAngle.setText(item.getBeamAngle());
        txtColorTemp.setText(item.getColorTemp());
        txtCri.setText(item.getCri());
        txtDc.setText(item.getDc());
        txtImage.setText(item.getImage());
        txtInformation.setText(item.getInformation());
        txtIpRate.setText(item.getIpRate());
        txtItemNo.setText(Integer.toString(item.getItemID()));
        txtKelvin.setText(item.getKelvin());
        txtLocationNo.setText(item.getLocationNo());
        txtLumens.setText(item.getLumens());
        txtPower.setText(item.getPower());
        txtProductName.setText(item.getProductName());
        txtRackNo.setText(item.getRackNo());
        txtRemarks.setText(item.getRemarks());
        txtRowNo.setText(item.getRowNo());
        txtSize.setText(item.getSize());
        txtQuantity.setText(Integer.toString(item.getQuantity()));
        txtThreshold.setText(Integer.toString(item.getThreshold()));
        txtWattage.setText(item.getWattage());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String[] date = item.getProductionDate().split(" ");
        LocalDate localDate = LocalDate.parse(date[0]);
        dpProductionDate.setDate(localDate);
        
        
    }
    public EditItem(User user, String action) {
        initComponents();
        this.sessionUser = user;
        this.action = action;
        this.pageFrom = pageFrom;
        if(this.action == "Edit"){
            btnDelete.setVisible(false);
        }
        else if(this.action == "Delete"){
            btnEdit.setVisible(false);
        }
        else{
            btnEdit.setVisible(true);
            btnDelete.setVisible(true);
        }
    }
    public EditItem(User user, int itemID) throws ClassNotFoundException, SQLException, IOException{
        initComponents();
        this.sessionUser = user;
        this.setEditableFields(itemID);
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtItemNo = new javax.swing.JTextField();
        txtProductName = new javax.swing.JTextField();
        txtInformation = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtBatchNo = new javax.swing.JTextField();
        btnEdit = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtIpRate = new javax.swing.JTextField();
        txtKelvin = new javax.swing.JTextField();
        txtBeamAngle = new javax.swing.JTextField();
        txtWattage = new javax.swing.JTextField();
        txtColorTemp = new javax.swing.JTextField();
        txtRowNo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtImage = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtLumens = new javax.swing.JTextField();
        txtCri = new javax.swing.JTextField();
        txtPower = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtSize = new javax.swing.JTextField();
        txtAc = new javax.swing.JTextField();
        txtDc = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtRackNo = new javax.swing.JTextField();
        txtLocationNo = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtThreshold = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtRemarks = new javax.swing.JTextArea();
        dpProductionDate = new com.github.lgooddatepicker.components.DatePicker();
        btnDelete = new javax.swing.JButton();
        txtQuantity = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Edit Item");
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N

        jLabel2.setText("Item No.:");
        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel3.setText("Product Name:");
        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        jLabel4.setText("Information:");
        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel6.setText("Production Date:");
        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtItemNo.setEditable(false);
        txtItemNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtItemNoActionPerformed(evt);
            }
        });

        jLabel8.setText("Batch No.:");
        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        jLabel5.setText("IP Rate:");
        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel9.setText("Kelvin:");
        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel10.setText("Beam Angle:");
        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel11.setText("Wattage:");
        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel12.setText("Color Temp:");
        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel13.setText("Row No.:");
        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtWattage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtWattageActionPerformed(evt);
            }
        });

        jLabel7.setText("Image:");
        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel15.setText("Lumens:");
        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel16.setText("CRI:");
        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel17.setText("Power(Volts):");
        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel18.setText("Size:");
        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel19.setText("AC:");
        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel20.setText("DC:");
        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel21.setText("Rack No.:");
        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel22.setText("Location No.:");
        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel27.setText("Threshold");
        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel25.setText("Remarks:");
        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtRemarks.setColumns(20);
        txtRemarks.setRows(5);
        jScrollPane1.setViewportView(txtRemarks);

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jLabel28.setText("Quantity");
        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(171, 171, 171)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(btnDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancel)
                        .addGap(58, 58, 58))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel2)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtInformation, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtItemNo, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGap(100, 100, 100)
                                    .addComponent(txtWattage))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel11)
                                        .addComponent(jLabel12)
                                        .addComponent(jLabel8)
                                        .addComponent(jLabel13)
                                        .addComponent(jLabel21)
                                        .addComponent(jLabel27))
                                    .addGap(23, 23, 23)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtBatchNo, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtRackNo, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtRowNo)
                                            .addComponent(txtColorTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(txtThreshold, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGap(110, 110, 110)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtBeamAngle, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtKelvin, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtIpRate, javax.swing.GroupLayout.Alignment.LEADING))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel9)))
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jLabel16))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel17))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel20)))
                            .addComponent(jLabel22)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dpProductionDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(72, 72, 72)
                                .addComponent(txtImage, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel28))
                                .addGap(47, 47, 47)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCri)
                                    .addComponent(txtPower)
                                    .addComponent(txtSize)
                                    .addComponent(txtAc)
                                    .addComponent(txtDc)
                                    .addComponent(txtLumens)
                                    .addComponent(txtLocationNo)
                                    .addComponent(txtQuantity))))))
                .addGap(86, 86, 86))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(254, 254, 254)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(340, 340, 340)
                        .addComponent(jLabel25)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtItemNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(dpProductionDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtProductName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(txtImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtInformation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtIpRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtKelvin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtBeamAngle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtWattage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtColorTemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtBatchNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txtLumens, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(txtCri, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(txtPower, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(txtSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(txtAc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(txtDc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(txtLocationNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(txtRowNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtRackNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtThreshold, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25))
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancel)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnEdit)
                        .addComponent(btnDelete)))
                .addGap(0, 29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtWattageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtWattageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtWattageActionPerformed

    private void txtItemNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtItemNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtItemNoActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
//         if(this.pageFrom == "Main"){
            try {
                Main menu = new Main(this.sessionUser, "Inventory");
                menu.setTitle("DSL Inventory System | Main Menu");
                menu.pack();
                menu.setLocationRelativeTo(null);
                menu.setExtendedState(JFrame.MAXIMIZED_BOTH);
                menu.setVisible(true);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(EditItem.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(EditItem.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(EditItem.class.getName()).log(Level.SEVERE, null, ex);
            }
//        }
//        else if(this.action == "Options"){
//            ItemOption option = new ItemOption(this.sessionUser);
//            option.setTitle("DSL Inventory System | Item Option");
//            option.pack();
//            option.setLocationRelativeTo(null);
//            option.setVisible(true);
//        }
//        else;

    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        try {
            // TODO add your handling code here:
            String ac = txtAc.getText();
            String batchNo = txtBatchNo.getText();
            String beamAngle = txtBeamAngle.getText();
            String colorTemp = txtColorTemp.getText();
            String cri = txtCri.getText();
            String dc = txtDc.getText();
            String image = txtImage.getText();
            String information = txtInformation.getText();
            String ipRate = txtIpRate.getText();
            String itemNo = txtItemNo.getText();
            String kelvin = txtKelvin.getText();
            String locationNo = txtLocationNo.getText();
            String lumens = txtLumens.getText();
            String power = txtPower.getText();
            String productName = txtProductName.getText();
            String rackNo = txtRackNo.getText();
            String remarks = txtRemarks.getText();
            String rowNo = txtRowNo.getText();
            String size = txtSize.getText();
            int quantity = Integer.parseInt(txtQuantity.getText());
            int threshold = Integer.parseInt(txtThreshold.getText());
            String productionDate = dpProductionDate.getDateStringOrEmptyString();
            String wattage = txtWattage.getText();
            
            String status = db.updateItems(ac, batchNo, colorTemp, cri, dc, image, information, ipRate, itemNo, kelvin, locationNo, lumens,
                    power, productName, rackNo, remarks, rowNo, size, quantity, threshold, wattage, beamAngle, productionDate);
            if(status == "Successful"){
                    JOptionPane.showMessageDialog(panel, "Item Updated", "Success", JOptionPane.INFORMATION_MESSAGE);
                    db.setLogStatus(sessionUser.getEmployeeID(), sessionUser.getFullName(), "Edit Item Page", "Edit");
                    this.setVisible(false);
                    ItemOption option = new ItemOption(this.sessionUser);
                    option.setTitle("DSL Inventory System | Item Option");
                    option.pack();
                    option.setLocationRelativeTo(null);
                    option.setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(panel, "System Error. Contact System Administrator", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditItem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EditItem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(EditItem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        Object[] options = {"Yes, Delete it","No"};
        
        int n = JOptionPane.showOptionDialog(null,
                    "Deletion Prompt",
                    "Are you sure you want to delete it?",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.DEFAULT_OPTION,
                    null,
                    options,
                    options[1]);
        if(n == 0){
            try {
                int itemID = Integer.parseInt(txtItemNo.getText());
                String status = db.deleteItem(itemID);
                if(status == "Successful"){
                    try {
                        JOptionPane.showMessageDialog(panel, "Item Deleted", "Success", JOptionPane.INFORMATION_MESSAGE);
                        db.setLogStatus(sessionUser.getEmployeeID(), sessionUser.getFullName(), "Edit Item Page", "Delete");
                        
                        this.setVisible(false);
                        Main menu = new Main(this.sessionUser, "Inventory");
                        menu.setTitle("DSL Inventory System | Main Menu");
                        menu.pack();
                        menu.setLocationRelativeTo(null);
                        menu.setExtendedState(JFrame.MAXIMIZED_BOTH);
                        menu.setVisible(true);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(EditItem.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(EditItem.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(EditItem.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(panel, "System Error.", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(EditItem.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(EditItem.class.getName()).log(Level.SEVERE, null, ex);
            }
                
        }
        else;
        
    }//GEN-LAST:event_btnDeleteActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private com.github.lgooddatepicker.components.DatePicker dpProductionDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtAc;
    private javax.swing.JTextField txtBatchNo;
    private javax.swing.JTextField txtBeamAngle;
    private javax.swing.JTextField txtColorTemp;
    private javax.swing.JTextField txtCri;
    private javax.swing.JTextField txtDc;
    private javax.swing.JTextField txtImage;
    private javax.swing.JTextField txtInformation;
    private javax.swing.JTextField txtIpRate;
    private javax.swing.JTextField txtItemNo;
    private javax.swing.JTextField txtKelvin;
    private javax.swing.JTextField txtLocationNo;
    private javax.swing.JTextField txtLumens;
    private javax.swing.JTextField txtPower;
    private javax.swing.JTextField txtProductName;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JTextField txtRackNo;
    private javax.swing.JTextArea txtRemarks;
    private javax.swing.JTextField txtRowNo;
    private javax.swing.JTextField txtSize;
    private javax.swing.JTextField txtThreshold;
    private javax.swing.JTextField txtWattage;
    // End of variables declaration//GEN-END:variables
}
