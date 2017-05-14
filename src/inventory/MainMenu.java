/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory;

/**
 *
 * @author bendrhick
 */
public class MainMenu extends javax.swing.JFrame {

    /**
     * Creates new form MainMenu
     */
    public MainMenu() {
        initComponents();
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
        btnLogout = new javax.swing.JButton();
        btnUser = new javax.swing.JButton();
        btnInventoryOverview = new javax.swing.JButton();
        btnItemOption = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Main Menu");

        btnLogout.setText("Log Out");

        btnUser.setText("User");

        btnInventoryOverview.setText("Inventory Overview");

        btnItemOption.setText("Item Option");
        btnItemOption.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnItemOptionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInventoryOverview)
                    .addComponent(jLabel1)
                    .addComponent(btnItemOption, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUser, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(120, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(btnInventoryOverview)
                .addGap(18, 18, 18)
                .addComponent(btnItemOption)
                .addGap(18, 18, 18)
                .addComponent(btnUser)
                .addGap(18, 18, 18)
                .addComponent(btnLogout)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnItemOptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnItemOptionActionPerformed
        // TODO add your handling code here:
        ItemOption option = new ItemOption();
        option.setTitle("DSL Inventory System | Item Option");
        option.pack();
        option.setLocationRelativeTo(null);
        option.setVisible(true);
    }//GEN-LAST:event_btnItemOptionActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInventoryOverview;
    private javax.swing.JButton btnItemOption;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnUser;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
