package com.crenu.kiosk.ui;

import com.crenu.kiosk.admin.MenuManager;
import com.crenu.kiosk.menu.Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LanguageSelectionScreen extends JFrame {

    public LanguageSelectionScreen() {
        setTitle("Language Selection");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JButton btnEnglish = new JButton("English");
        JButton btnKorean = new JButton("한국어");

        btnEnglish.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openMenuDisplayScreen("English");
            }
        });

        btnKorean.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openMenuDisplayScreen("한국어");
            }
        });

        add(btnEnglish);
        add(btnKorean);
    }

    private void openMenuDisplayScreen(String language) {
        MenuManager menuManager = new MenuManager();
        menuManager.addMenuItem(new com.crenu.kiosk.menu.Menu("Bulgogi Burger", 8, "Main"));
        menuManager.addMenuItem(new com.crenu.kiosk.menu.Menu("Cheese Burger", 7, "Main"));
        menuManager.addMenuItem(new Menu("Veggie Burger", 6, "Main"));

        MenuDisplayScreen menuDisplayScreen = new MenuDisplayScreen(language, menuManager);
        menuDisplayScreen.setVisible(true);
        setVisible(false); // Hide the language selection screen
    }
}
