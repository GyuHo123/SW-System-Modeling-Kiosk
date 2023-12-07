package com.crenu.kiosk.ui;

import com.crenu.kiosk.admin.MenuManager;
import com.crenu.kiosk.menu.Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.crenu.kiosk.ui.UIManager.*;

public class LanguageSelectionScreen{


    public LanguageSelectionScreen() {
        main.setTitle("Language Selection");
        JButton btnEnglish = new JButton("English");
        JButton btnKorean = new JButton("한국어");
        JPanel panel = panels.get(LANGUAGE_PANELNAME);
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

        panel.add(btnEnglish);
        panel.add(btnKorean);
    }

    private void openMenuDisplayScreen(String language) {
        MenuManager menuManager = new MenuManager();
        menuManager.addMenuItem(new com.crenu.kiosk.menu.Menu("Bulgogi Burger", 8, "Main"));
        menuManager.addMenuItem(new com.crenu.kiosk.menu.Menu("Cheese Burger", 7, "Main"));
        menuManager.addMenuItem(new Menu("Veggie Burger", 6, "Main"));

        MenuDisplayScreen menuDisplayScreen = new MenuDisplayScreen(language, menuManager);
        menuDisplayScreen.setVisible(true);

    }
}
