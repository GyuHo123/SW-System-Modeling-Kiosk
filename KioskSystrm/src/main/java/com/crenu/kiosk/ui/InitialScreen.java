package com.crenu.kiosk.ui;

import com.crenu.kiosk.admin.MenuManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.crenu.kiosk.KioskSystem.uiManager;
import static com.crenu.kiosk.ui.PanelNameEntity.INITAL_PANELNAME;
import static com.crenu.kiosk.ui.PanelNameEntity.LANGUAGE_PANELNAME;

public class InitialScreen{
    public static void init() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               LanguageSelectionScreen.init();
                uiManager.allPanelVisibleOff();
                uiManager.panelSetVisble(LANGUAGE_PANELNAME, true);
            }
        });
        panel.add(startButton);
        uiManager.addPanel(INITAL_PANELNAME, panel, BorderLayout.CENTER);
        uiManager.setVisible(true);
    }
}
