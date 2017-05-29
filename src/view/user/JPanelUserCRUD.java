/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.user;

import controller.Controller;
import domain.AppUser;
import domain.UserCRUDType;
import java.awt.Color;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author Nikola
 */
public class JPanelUserCRUD extends javax.swing.JPanel {

    private final AppUser appUser;
    private final UserCRUDType cRUDType;
    private boolean formSubmitetedOnce = false;
    private final String passwordPaterrn = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    private final String emailPaterrn = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,64}";

    /**
     * Creates new form JPanelUserCRUD
     *
     * @param appUser
     * @param cRUDType
     */
    public JPanelUserCRUD(AppUser appUser, UserCRUDType cRUDType) {
        initComponents();
        this.appUser = appUser == null ? new AppUser() : appUser;
        this.cRUDType = cRUDType;
        initCustomCompoents();
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
        jTextFieldFirstname = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldLastname = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldUsername = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldEmail = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jTextFieldImagePath = new javax.swing.JTextField();
        jButtonSelectImage = new javax.swing.JButton();
        jButtonNextStep = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();
        jPasswordField = new javax.swing.JPasswordField();
        jPasswordFieldRepeat = new javax.swing.JPasswordField();
        FirstNameFieldErrorLabel = new javax.swing.JLabel();
        LastnameFieldErrorLabel = new javax.swing.JLabel();
        UsernameFieldErrorLabel = new javax.swing.JLabel();
        EmailFieldErrorLabel = new javax.swing.JLabel();
        PasswordFieldErrorLabel = new javax.swing.JLabel();
        PasswordRepeatFieldErrorLabel = new javax.swing.JLabel();
        jButtonSave = new javax.swing.JButton();
        jButtonBack = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Border Title Add/Edit/Read"));

        jLabel1.setText("Firstname");

        jTextFieldFirstname.setName("FirstNameField"); // NOI18N
        jTextFieldFirstname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldFirstnameKeyReleased(evt);
            }
        });

        jLabel2.setText("Lastname");

        jTextFieldLastname.setName("LastnameField"); // NOI18N
        jTextFieldLastname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldLastnameKeyReleased(evt);
            }
        });

        jLabel3.setText("Username");

        jTextFieldUsername.setName("UsernameField"); // NOI18N
        jTextFieldUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldUsernameKeyReleased(evt);
            }
        });

        jLabel4.setText("Email");

        jTextFieldEmail.setName("EmailField"); // NOI18N
        jTextFieldEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldEmailKeyReleased(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/image/user-help.png"))); // NOI18N
        jLabel5.setText("Password");
        jLabel5.setToolTipText("<html>\n<ul>\n<li>At least 8 chars</li>\n<li>Contains at least one digit</li>\n<li>Contains at least one lower alpha char and one upper alpha char</li>\n<li>Contains at least one char within a set of special chars (@#%$^ etc.)</li>\n<li>Does not contain space, tab, etc</li>\n</ul>\n</htlm>");

        jLabel6.setText("Reapet Password");

        jTextFieldImagePath.setEditable(false);

        jButtonSelectImage.setText("Select Image");

        jButtonNextStep.setText("Next Step");
        jButtonNextStep.setMaximumSize(new java.awt.Dimension(100, 25));
        jButtonNextStep.setMinimumSize(new java.awt.Dimension(100, 25));
        jButtonNextStep.setPreferredSize(new java.awt.Dimension(100, 25));
        jButtonNextStep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNextStepActionPerformed(evt);
            }
        });

        jButtonCancel.setText("Cancel");
        jButtonCancel.setMaximumSize(new java.awt.Dimension(100, 25));
        jButtonCancel.setMinimumSize(new java.awt.Dimension(100, 25));
        jButtonCancel.setPreferredSize(new java.awt.Dimension(100, 25));
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        jPasswordField.setName("PasswordField"); // NOI18N
        jPasswordField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jPasswordFieldKeyReleased(evt);
            }
        });

        jPasswordFieldRepeat.setName("PasswordRepeatField"); // NOI18N
        jPasswordFieldRepeat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jPasswordFieldRepeatKeyReleased(evt);
            }
        });

        UsernameFieldErrorLabel.setText(" ");

        jButtonSave.setText("Save User");
        jButtonSave.setEnabled(false);
        jButtonSave.setMaximumSize(new java.awt.Dimension(100, 25));
        jButtonSave.setMinimumSize(new java.awt.Dimension(100, 25));
        jButtonSave.setPreferredSize(new java.awt.Dimension(100, 25));

        jButtonBack.setText("Back");
        jButtonBack.setEnabled(false);
        jButtonBack.setMaximumSize(new java.awt.Dimension(100, 25));
        jButtonBack.setMinimumSize(new java.awt.Dimension(100, 25));
        jButtonBack.setPreferredSize(new java.awt.Dimension(100, 25));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextFieldImagePath, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonSelectImage))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldEmail)
                            .addComponent(jTextFieldUsername)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldLastname)
                            .addComponent(jTextFieldFirstname)))
                    .addComponent(jSeparator1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(35, 35, 35)
                        .addComponent(jPasswordField))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(jPasswordFieldRepeat))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(EmailFieldErrorLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(UsernameFieldErrorLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(LastnameFieldErrorLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(FirstNameFieldErrorLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(PasswordFieldErrorLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(PasswordRepeatFieldErrorLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonNextStep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldFirstname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FirstNameFieldErrorLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldLastname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LastnameFieldErrorLabel)
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(UsernameFieldErrorLabel)
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EmailFieldErrorLabel)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PasswordFieldErrorLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jPasswordFieldRepeat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PasswordRepeatFieldErrorLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldImagePath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSelectImage))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonNextStep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        SwingUtilities.getWindowAncestor(this).setVisible(false);
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void jButtonNextStepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNextStepActionPerformed
        formSubmitetedOnce = true;
        boolean formValid;
        try {
            formValid = validateUser();
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "System Error", JOptionPane.ERROR_MESSAGE);
//            JOptionPane.showMessageDialog(null, "System Error. Please contact your administator at \"Help Section\"", "System Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!formValid) {
//            JOptionPane.showMessageDialog(null, "Form is not valid! Please check all fields and submit form again.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        appUser.setFirstname(jTextFieldFirstname.getText().trim());
        appUser.setLastname(jTextFieldLastname.getText().trim());
        appUser.setUsername(jTextFieldUsername.getText().trim());
        appUser.setEmail(jTextFieldEmail.getText().trim());
        appUser.setPassword(jPasswordField.getText().trim());
        appUser.setImagePath(jTextFieldImagePath.getText().trim());
        
        JPanel panel = new JPanelUserAddressCRUD(appUser, cRUDType);
        JDialog dialog  = (JDialog) SwingUtilities.getWindowAncestor(this);
        dialog.remove(this);
        dialog.add(panel);
        dialog.invalidate();
        dialog.validate();
        dialog.repaint();

//        switch (cRUDType) {
//            case Add:
//            case Edit: {
//                try {
//                    Controller.getController().saveAppUser(appUser);
//                } catch (Exception ex) {
//                    JOptionPane.showMessageDialog(null, "System Error. " + ex.getMessage(), "System Error", JOptionPane.ERROR_MESSAGE);
//                    return;
//                }
//            }
//            break;
//            default:
//                JOptionPane.showMessageDialog(null, "System Error. This shouldnt have hapend", "System Error", JOptionPane.ERROR_MESSAGE);
//        }
//
//        JOptionPane.showMessageDialog(null, "User saved successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButtonNextStepActionPerformed

    private void jTextFieldFirstnameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldFirstnameKeyReleased
        if (formSubmitetedOnce) {
            validateOneField(jTextFieldFirstname);
        }
    }//GEN-LAST:event_jTextFieldFirstnameKeyReleased

    private void jTextFieldLastnameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldLastnameKeyReleased
        if (formSubmitetedOnce) {
            validateOneField(jTextFieldLastname);
        }
    }//GEN-LAST:event_jTextFieldLastnameKeyReleased

    private void jTextFieldUsernameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldUsernameKeyReleased
        if (formSubmitetedOnce) {
            validateOneField(jTextFieldUsername);
        }
    }//GEN-LAST:event_jTextFieldUsernameKeyReleased

    private void jTextFieldEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldEmailKeyReleased
        if (formSubmitetedOnce) {
            validateOneField(jTextFieldEmail);
        }
    }//GEN-LAST:event_jTextFieldEmailKeyReleased

    private void jPasswordFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordFieldKeyReleased
        if (formSubmitetedOnce) {
            validateOneField(jPasswordField);
        }
    }//GEN-LAST:event_jPasswordFieldKeyReleased

    private void jPasswordFieldRepeatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordFieldRepeatKeyReleased
        if (formSubmitetedOnce) {
            validateOneField(jPasswordFieldRepeat);
        }
    }//GEN-LAST:event_jPasswordFieldRepeatKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel EmailFieldErrorLabel;
    private javax.swing.JLabel FirstNameFieldErrorLabel;
    private javax.swing.JLabel LastnameFieldErrorLabel;
    private javax.swing.JLabel PasswordFieldErrorLabel;
    private javax.swing.JLabel PasswordRepeatFieldErrorLabel;
    private javax.swing.JLabel UsernameFieldErrorLabel;
    private javax.swing.JButton jButtonBack;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonNextStep;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonSelectImage;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPasswordField jPasswordField;
    private javax.swing.JPasswordField jPasswordFieldRepeat;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldFirstname;
    private javax.swing.JTextField jTextFieldImagePath;
    private javax.swing.JTextField jTextFieldLastname;
    private javax.swing.JTextField jTextFieldUsername;
    // End of variables declaration//GEN-END:variables

    private boolean validateUser() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        boolean formValid = true;

        if (!validateIsEmpty(jTextFieldFirstname, jTextFieldLastname, jPasswordField, jPasswordFieldRepeat, jTextFieldUsername, jTextFieldEmail)) {
            JOptionPane.showMessageDialog(this, "All fields on form are mentatory!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!validateUsername()) {
            JOptionPane.showMessageDialog(this, "Username must be valid!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!validateEmail()) {
            JOptionPane.showMessageDialog(this, "Email must be valid!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!validatePassword()) {
            JOptionPane.showMessageDialog(this, "Password must be valid!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return formValid;
    }

    private boolean validateIsEmpty(JTextField... fields) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        boolean fieldsNotEmpty = true;
        for (JTextField field : fields) {
            if (field.getText().isEmpty()) {
                fieldsNotEmpty = false;
                Field fieldError = this.getClass().getDeclaredField(field.getName() + "ErrorLabel");
                JLabel errorLab = (JLabel) fieldError.get(this);
                errorLab.setText("Please fill this field!");
                errorLab.setForeground(Color.red);
            }
        }

        return fieldsNotEmpty;
    }

    private boolean validateUsername() {
        if (controller.Controller.getController().checkUsername(jTextFieldUsername.getText())) {
            UsernameFieldErrorLabel.setText(null);
            return true;
        } else {
            UsernameFieldErrorLabel.setText("Username must be unique!");
            UsernameFieldErrorLabel.setForeground(Color.red);
            return false;
        }
    }

    private boolean validateEmail() {
        String email = jTextFieldEmail.getText();

        if (!email.matches(this.emailPaterrn)) {
            EmailFieldErrorLabel.setText("Email must be valid!");
            EmailFieldErrorLabel.setForeground(Color.red);
            return false;
        }

        if (controller.Controller.getController().checkEmail(email)) {
            EmailFieldErrorLabel.setText(null);
        } else {
            EmailFieldErrorLabel.setText("Email must be unique!");
            EmailFieldErrorLabel.setForeground(Color.red);
            return false;
        }

        return true;
    }

    private boolean validatePassword() {
        if (cRUDType == UserCRUDType.Add) {
            String password = jPasswordField.getText();
            String passwordRepeate = jPasswordFieldRepeat.getText();

            if (!password.matches(this.passwordPaterrn)) {
                PasswordFieldErrorLabel.setText("Password must match patern!");
                PasswordFieldErrorLabel.setForeground(Color.red);
                return false;
            } else {
                PasswordFieldErrorLabel.setText(null);
            }

            if (!password.equals(passwordRepeate)) {
                PasswordRepeatFieldErrorLabel.setText("Password repeat must match password!");
                PasswordRepeatFieldErrorLabel.setForeground(Color.red);
                return false;
            } else {
                PasswordRepeatFieldErrorLabel.setText(null);
            }
        }

        return true;
    }

    private void validateOneField(JTextField field) {
        try {
            Field fieldError = this.getClass().getDeclaredField(field.getName() + "ErrorLabel");
            JLabel errorLab = (JLabel) fieldError.get(this);

            if (field.getText().isEmpty()) {
                errorLab.setText("Please fill this field!");
                errorLab.setForeground(Color.red);
            } else if (field == jPasswordField || field == jPasswordFieldRepeat) {
                validatePassword();
            } else if (field == jTextFieldUsername) {
                validateUsername();
            } else if (field == jTextFieldEmail) {
                validateEmail();
            } else {
                errorLab.setText(null);
            }

        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void initCustomCompoents() {
        switch (cRUDType) {
            case Add:
                break;
            case View:
                jButtonNextStep.setEnabled(false);
                Arrays.asList(jTextFieldFirstname, jTextFieldLastname, jTextFieldUsername, jTextFieldEmail, jTextFieldImagePath, jPasswordField, jPasswordFieldRepeat)
                        .stream()
                        .forEach((field) -> field.setEditable(false));
            case Edit:
                formSubmitetedOnce = true;
                setUpFields();
                break;
        }
    }

    private void setUpFields() {
        jTextFieldFirstname.setText(appUser.getFirstname());
        jTextFieldLastname.setText(appUser.getLastname());
        jTextFieldUsername.setText(appUser.getUsername());
        jTextFieldEmail.setText(appUser.getEmail());
        jTextFieldImagePath.setText(appUser.getImagePath());
    }
}
