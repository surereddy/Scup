/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.matejsimek.scup;

import java.awt.event.ActionEvent;
import java.util.prefs.Preferences;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 *
 * @author Enzy
 */
public class SettingsForm extends javax.swing.JFrame {

    private Preferences prefs;

    /**
     * Creates new form SettingsForm
     */
    public SettingsForm() {
	initComponents();
	setLocationRelativeTo(null);
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	prefs = Preferences.userNodeForPackage(cz.matejsimek.scup.Scup.class);

	serverText.setText(prefs.get(Scup.KEY_FTP_SERVER, "localhost"));
	usernameText.setText(prefs.get(Scup.KEY_FTP_USERNAME, "anonymous"));
	passwordField.setText(prefs.get(Scup.KEY_FTP_PASSWORD, ""));
	directoryText.setText(prefs.get(Scup.KEY_DIRECTORY, ""));
	urlText.setText(prefs.get(Scup.KEY_URL, "http://localhost"));
	uploadCheckBox.setSelected(prefs.getBoolean(Scup.KEY_UPLOAD, false));
	monitorAllCheckBox.setSelected(prefs.getBoolean(Scup.KEY_MONITOR_ALL, true));

	this.pack();

	// Set default state
	uploadCheckBoxActionPerformed(new ActionEvent(this, 0, null));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FTPConnectionPanel = new javax.swing.JPanel();
        serverLabel = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        directoryLabel = new javax.swing.JLabel();
        urlLabel = new javax.swing.JLabel();
        serverText = new javax.swing.JTextField();
        usernameText = new javax.swing.JTextField();
        directoryText = new javax.swing.JTextField();
        urlText = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        uploadCheckBox = new javax.swing.JCheckBox();
        monitorAllCheckBox = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        cancelButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Scup - Settings");
        setMaximumSize(null);
        setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        setType(java.awt.Window.Type.UTILITY);

        FTPConnectionPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("FTP Connection"));

        serverLabel.setText("Server");

        usernameLabel.setText("Username");

        passwordLabel.setText("Password");

        directoryLabel.setText("Directory");

        urlLabel.setText("URL");

        passwordField.setToolTipText("");

        javax.swing.GroupLayout FTPConnectionPanelLayout = new javax.swing.GroupLayout(FTPConnectionPanel);
        FTPConnectionPanel.setLayout(FTPConnectionPanelLayout);
        FTPConnectionPanelLayout.setHorizontalGroup(
            FTPConnectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FTPConnectionPanelLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(FTPConnectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FTPConnectionPanelLayout.createSequentialGroup()
                        .addComponent(passwordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(passwordField))
                    .addGroup(FTPConnectionPanelLayout.createSequentialGroup()
                        .addGroup(FTPConnectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(serverLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(usernameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(FTPConnectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(serverText)
                            .addComponent(usernameText)))
                    .addGroup(FTPConnectionPanelLayout.createSequentialGroup()
                        .addComponent(urlLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(urlText))
                    .addGroup(FTPConnectionPanelLayout.createSequentialGroup()
                        .addComponent(directoryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(directoryText)))
                .addGap(5, 5, 5))
        );
        FTPConnectionPanelLayout.setVerticalGroup(
            FTPConnectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FTPConnectionPanelLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(FTPConnectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(serverLabel)
                    .addComponent(serverText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(FTPConnectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameLabel)
                    .addComponent(usernameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(FTPConnectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLabel)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(FTPConnectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(directoryLabel)
                    .addComponent(directoryText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(FTPConnectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(urlLabel)
                    .addComponent(urlText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        uploadCheckBox.setText("Upload to FTP Server, otherwise save to disk");
        uploadCheckBox.setToolTipText("");
        uploadCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadCheckBoxActionPerformed(evt);
            }
        });

        monitorAllCheckBox.setText("Monitor all content in clipboard");

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        saveButton.setText("Save settings");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(cancelButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(saveButton))
            .addComponent(jSeparator1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(saveButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(FTPConnectionPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(uploadCheckBox)
                            .addComponent(monitorAllCheckBox))
                        .addGap(0, 254, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(uploadCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(FTPConnectionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(monitorAllCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
	prefs.put(Scup.KEY_FTP_SERVER, serverText.getText());
	prefs.put(Scup.KEY_FTP_USERNAME, usernameText.getText());
	prefs.put(Scup.KEY_FTP_PASSWORD, passwordField.getText());
	prefs.put(Scup.KEY_DIRECTORY, directoryText.getText());
	prefs.put(Scup.KEY_URL, urlText.getText());
	prefs.putBoolean(Scup.KEY_UPLOAD, uploadCheckBox.isSelected());
	prefs.putBoolean(Scup.KEY_MONITOR_ALL, monitorAllCheckBox.isSelected());
	prefs.putBoolean(Scup.KEY_INITIAL_SETTINGS, false);

	Scup.reloadConfiguration();
	dispose();
    }//GEN-LAST:event_saveButtonActionPerformed

    private void uploadCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadCheckBoxActionPerformed
	if (uploadCheckBox.isSelected()) {
	    FTPConnectionPanel.setEnabled(true);
	    directoryText.setEnabled(true);
	    passwordField.setEnabled(true);
	    serverText.setEnabled(true);
	    urlText.setEnabled(true);
	    usernameText.setEnabled(true);
	    directoryLabel.setEnabled(true);
	    passwordLabel.setEnabled(true);
	    serverLabel.setEnabled(true);
	    urlLabel.setEnabled(true);
	    usernameLabel.setEnabled(true);
	} else {
	    FTPConnectionPanel.setEnabled(false);
	    directoryText.setEnabled(false);
	    passwordField.setEnabled(false);
	    serverText.setEnabled(false);
	    urlText.setEnabled(false);
	    usernameText.setEnabled(false);
	    directoryLabel.setEnabled(false);
	    passwordLabel.setEnabled(false);
	    serverLabel.setEnabled(false);
	    urlLabel.setEnabled(false);
	    usernameLabel.setEnabled(false);
	}
    }//GEN-LAST:event_uploadCheckBoxActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
	try {
	    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
	/* Create and display the form */
	java.awt.EventQueue.invokeLater(new Runnable() {
	    public void run() {
		new SettingsForm().setVisible(true);
	    }
	});
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel FTPConnectionPanel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel directoryLabel;
    private javax.swing.JTextField directoryText;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JCheckBox monitorAllCheckBox;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JButton saveButton;
    private javax.swing.JLabel serverLabel;
    private javax.swing.JTextField serverText;
    private javax.swing.JCheckBox uploadCheckBox;
    private javax.swing.JLabel urlLabel;
    private javax.swing.JTextField urlText;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JTextField usernameText;
    // End of variables declaration//GEN-END:variables
}
