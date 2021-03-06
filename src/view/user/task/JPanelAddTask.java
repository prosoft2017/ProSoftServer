/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.user.task;

import constant.ConstantMessages;
import controller.UserBackendController;
import domain.UserCRUDType;
import domain.user.AppUser;
import domain.task.Task;
import domain.task.TaskStatus;
import java.awt.Dialog;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import model.TaskTableModel;
import model.UserTableModel;
import view.user.JPanelUserCrud;

/**
 *
 * @author Filip
 */
public class JPanelAddTask extends javax.swing.JPanel {

    private AppUser appUser;

    /**
     * Creates new form JPanelAddTask
     *
     * @param appUser
     */
    public JPanelAddTask(AppUser appUser) {
        this.appUser = appUser;
        initComponents();
        initCustomComponents();
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
        jLabel2 = new javax.swing.JLabel();
        jTextFieldTaskTitle = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaTaskDescription = new javax.swing.JTextArea();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldStartDate = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldEndDate = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButtonEditTask = new javax.swing.JButton();
        jButtonCloseTask = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Add new task"));

        jLabel1.setText("Select User");

        jLabel2.setText("Task Title");

        jLabel3.setText("Task Description");

        jTextAreaTaskDescription.setColumns(20);
        jTextAreaTaskDescription.setRows(5);
        jScrollPane2.setViewportView(jTextAreaTaskDescription);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel4.setText("Start Date");

        jLabel5.setText("End Date");

        jButton2.setText("Add Task");
        jButton2.setMaximumSize(new java.awt.Dimension(91, 23));
        jButton2.setMinimumSize(new java.awt.Dimension(91, 23));
        jButton2.setPreferredSize(new java.awt.Dimension(91, 23));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Reset Fields");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldTaskTitle)
                            .addComponent(jComboBox1, 0, 166, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldStartDate)
                            .addComponent(jTextFieldEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextFieldStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTextFieldEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextFieldTaskTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("All tasks for selected user"));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButtonEditTask.setText("Edit Task");
        jButtonEditTask.setMaximumSize(new java.awt.Dimension(100, 25));
        jButtonEditTask.setMinimumSize(new java.awt.Dimension(100, 25));
        jButtonEditTask.setPreferredSize(new java.awt.Dimension(100, 25));
        jButtonEditTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditTaskActionPerformed(evt);
            }
        });

        jButtonCloseTask.setText("Close Task");
        jButtonCloseTask.setMaximumSize(new java.awt.Dimension(100, 25));
        jButtonCloseTask.setMinimumSize(new java.awt.Dimension(100, 25));
        jButtonCloseTask.setPreferredSize(new java.awt.Dimension(100, 25));
        jButtonCloseTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCloseTaskActionPerformed(evt);
            }
        });

        jButton1.setText("Exit");
        jButton1.setMaximumSize(new java.awt.Dimension(100, 25));
        jButton1.setMinimumSize(new java.awt.Dimension(100, 25));
        jButton1.setPreferredSize(new java.awt.Dimension(100, 25));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonCloseTask, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonEditTask, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonEditTask, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCloseTask, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        resetAllFormFields();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (appUser == null) {
            appUser = (AppUser) jComboBox1.getSelectedItem();
        }
        String taskTitle = jTextFieldTaskTitle.getText().trim();
        String taskDescription = jTextAreaTaskDescription.getText().trim();
        String startDateString = jTextFieldStartDate.getText().trim();
        String endDateString = jTextFieldEndDate.getText().trim();

        LocalDate startDate;
        LocalDate endDate;

        try {
            startDate = LocalDate.parse(startDateString, controller.Controller.getController().formatter);
            endDate = LocalDate.parse(endDateString, controller.Controller.getController().formatter);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please provide correct start and end date for user task!!", constant.ConstantMessages.ERROR_MSG_TITLE, JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (validateEmptyTaskField(taskTitle, taskDescription)) {
            Task userTask = new Task()
                    .setTitle(taskTitle)
                    .setDescription(taskDescription)
                    .setStartDate(startDate)
                    .setEndDate(endDate)
                    .setAppUser(appUser)
                    .setTaskStatus(TaskStatus.ACTIVE);

            try {
                controller.UserBackendController.getController().saveUserTask(userTask);

                TaskTableModel tableModel = (TaskTableModel) jTable1.getModel();
                tableModel.addNewTask(userTask);
                resetAllFormFields();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), constant.ConstantMessages.ERROR_MSG_TITLE, JOptionPane.ERROR_MESSAGE);
            }

        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButtonCloseTaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCloseTaskActionPerformed

        int selectedIndex = jTable1.getSelectedRow();
        if (selectedIndex < 0) {
            JOptionPane.showMessageDialog(null, "Please select user to manage their tasks!", ConstantMessages.ERROR_MSG_TITLE, JOptionPane.ERROR_MESSAGE);
            return;
        }

        TaskTableModel tableModel = (TaskTableModel) jTable1.getModel();
        Task task = tableModel.getTaskAt(selectedIndex);
        if (task.getTaskStatus() == TaskStatus.DELETED) {
            JOptionPane.showMessageDialog(null, "Task is already closed", ConstantMessages.ERROR_MSG_TITLE, JOptionPane.ERROR_MESSAGE);
            return;
        }
        int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to close this task?", "Closing Task", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            try {
                UserBackendController.getController().closeUserTask(task);
                tableModel.fireTableDataChanged();
                JOptionPane.showMessageDialog(null, "Task status changed!", ConstantMessages.ERROR_MSG_TITLE, JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error!", ConstantMessages.ERROR_MSG_TITLE, JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButtonCloseTaskActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        SwingUtilities.getWindowAncestor(this).setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButtonEditTaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditTaskActionPerformed
        int selectedIndex = jTable1.getSelectedRow();
        if (selectedIndex < 0) {
            JOptionPane.showMessageDialog(null, "Please select user to manage their tasks!", ConstantMessages.ERROR_MSG_TITLE, JOptionPane.ERROR_MESSAGE);
            return;
        }

        TaskTableModel tableModel = (TaskTableModel) jTable1.getModel();
        Task task = tableModel.getTaskAt(selectedIndex);

        JDialog dialog = new JDialog(null, "Edit Task", Dialog.ModalityType.APPLICATION_MODAL);
        JPanel panel = new JPanelEditTask(task);
        dialog.add(panel);
        dialog.setResizable(false);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);

    }//GEN-LAST:event_jButtonEditTaskActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonCloseTask;
    private javax.swing.JButton jButtonEditTask;
    private javax.swing.JComboBox<AppUser> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextAreaTaskDescription;
    private javax.swing.JTextField jTextFieldEndDate;
    private javax.swing.JTextField jTextFieldStartDate;
    private javax.swing.JTextField jTextFieldTaskTitle;
    // End of variables declaration//GEN-END:variables

    private void initCustomComponents() {

        List<Task> taskList;

        try {
            taskList = controller.UserBackendController.getController().getAllTasksForUser(appUser);
        } catch (Exception ex) {
            Logger.getLogger(JPanelAddTask.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }

        if (appUser != null) {
            jComboBox1.setModel(new DefaultComboBoxModel<>(new AppUser[]{appUser}));
            jComboBox1.setEditable(false);
        }

        TaskTableModel tableModel = new TaskTableModel(taskList);
        jTable1.setModel(tableModel);
    }

    private void resetAllFormFields() {
        jTextFieldTaskTitle.setText(null);
        jTextAreaTaskDescription.setText(null);
        jTextFieldStartDate.setText(null);
        jTextFieldEndDate.setText(null);

    }

    private boolean validateEmptyTaskField(String... fields) {
        for (String field : fields) {
            if (field.isEmpty()) {
                JOptionPane.showMessageDialog(null, "All fields are mentatory!!!", constant.ConstantMessages.ERROR_MSG_TITLE, JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }
}
