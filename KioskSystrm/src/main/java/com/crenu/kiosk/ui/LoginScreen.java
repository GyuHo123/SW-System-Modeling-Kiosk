package com.crenu.kiosk.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen extends JPanel {

    public LoginScreen() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JButton btnLogin = new JButton("Login");
        JTextField userIDField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkCredentials(userIDField.getText(), String.valueOf(passwordField.getPassword()));
            }
        });

        add(userIDField);
        add(passwordField);
        add(btnLogin);
    }

    private void checkCredentials(String userID, String password) {
        String adminID = "admin";
        String adminPassword = "password";

        if(userID.equals(adminID) && password.equals(adminPassword)) {
            JOptionPane.showMessageDialog(null, "로그인 성공");
        } else {
            JOptionPane.showMessageDialog(null, "Invalid credentials. Please try again.");
        }
    }
}
