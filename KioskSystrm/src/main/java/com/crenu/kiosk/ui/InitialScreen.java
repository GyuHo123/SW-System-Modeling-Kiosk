package com.crenu.kiosk.ui;

import com.crenu.kiosk.admin.MenuManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InitialScreen extends JFrame {

    public InitialScreen() {
        setTitle("Welcome to the Kiosk");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LanguageSelectionScreen languageSelectionScreen = new LanguageSelectionScreen();
                languageSelectionScreen.setVisible(true);
                setVisible(false); // Hide the initial screen
            }
        });

        add(startButton);
    }
}
