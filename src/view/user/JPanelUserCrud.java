/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.user;

import domain.UserCRUDType;
import domain.user.AppUser;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.SwingUtilities;

/**
 *
 * @author Nikola
 */
public class JPanelUserCrud extends javax.swing.JPanel {

    private final AppUser appUser;
    private final UserCRUDType cRUDType;

    /**
     * Creates new form JPanelUserCrud
     *
     * @param appUser
     * @param cRUDType
     */
    public JPanelUserCrud(AppUser appUser, UserCRUDType cRUDType) {
        initComponents();
        this.appUser = appUser == null ? new AppUser() : appUser;
        this.cRUDType = cRUDType;
        jPanel2.removeAll();
        jPanel2.setLayout(new BorderLayout());
        jPanel2.add(new JPanelUserBasicInfoCRUD(this.appUser, cRUDType, this), BorderLayout.CENTER);
        jPanel2.revalidate();
        jPanel2.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabelBasicInfo = new javax.swing.JLabel();
        jLabelAddressInfo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();

        jPanel1.setMaximumSize(new java.awt.Dimension(210, 588));
        jPanel1.setMinimumSize(new java.awt.Dimension(210, 588));

        jLabel1.setText("Steps");

        jLabelBasicInfo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelBasicInfo.setText("1. User Basic Info");

        jLabelAddressInfo.setText("2. Address Info");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabelBasicInfo)
                            .addComponent(jLabelAddressInfo))
                        .addGap(0, 131, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelBasicInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelAddressInfo)
                .addContainerGap(531, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 558, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelAddressInfo;
    private javax.swing.JLabel jLabelBasicInfo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables

    protected void goToBasicInfoStep() {
        jLabelBasicInfo.setFont(new java.awt.Font("Tahoma", Font.BOLD, 11));
        jLabelAddressInfo.setFont(new java.awt.Font("Tahoma", Font.PLAIN, 11));
        jPanel2.removeAll();
        jPanel2.setLayout(new BorderLayout());
        jPanel2.add(new JPanelUserBasicInfoCRUD(appUser, cRUDType, this), BorderLayout.CENTER);
        jPanel2.revalidate();
        jPanel2.repaint();
    }

    protected void goToAddressStep() {
        jLabelBasicInfo.setFont(new java.awt.Font("Tahoma", Font.PLAIN, 11));
        jLabelAddressInfo.setFont(new java.awt.Font("Tahoma", Font.BOLD, 11));
        jPanel2.removeAll();
        jPanel2.setLayout(new BorderLayout());
        jPanel2.add(new JPanelUserAddressCRUD(appUser, cRUDType, this), BorderLayout.CENTER);
        jPanel2.revalidate();
        jPanel2.repaint();
    }

    protected void closeCrudWindow() {
        SwingUtilities.getWindowAncestor(this).setVisible(false);
    }
}