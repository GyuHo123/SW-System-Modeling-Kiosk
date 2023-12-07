package com.crenu.kiosk.ui;

import com.crenu.kiosk.admin.MenuManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InitialScreen extends UIManager{

    public InitialScreen() {

        JPanel panel = panels.get(INITAL_PANELNAME);
        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LanguageSelectionScreen languageSelectionScreen = new LanguageSelectionScreen();
                panels.get(LANGUAGE_PANELNAME).setVisible(true);
                panel.setVisible(false);
            }
        });
        panel.add(startButton);
        panel.setVisible(true);
    }
}
