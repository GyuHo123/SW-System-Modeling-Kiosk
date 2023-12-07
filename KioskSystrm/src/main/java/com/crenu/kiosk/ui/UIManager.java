package com.crenu.kiosk.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class UIManager extends JFrame {
    public static HashMap<String, JPanel> panels;
    public static JFrame main;
    public static String LANGUAGE_PANELNAME = "Language Selection";
    public static String INITAL_PANELNAME = "Inital";

    public UIManager(){
        setTitle("Welcome to the Kiosk");
        setSize(860, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setVisible(true);
        panels = new HashMap<>();
        initLanguageScreenPanel();
        initInitalScreen();
    }



    public void panelSetVisble(String panelName, boolean state) {
        panels.get(panelName).setVisible(state);
    }

    public void allPanelVisibleOff() {
        for (JPanel panel: panels.values()) {
            panel.setVisible(false);
        }
    }

    public void initInitalScreen(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panels.put(INITAL_PANELNAME, panel);
        add(panel, BorderLayout.CENTER);
    }

    public void initLanguageScreenPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panels.put(LANGUAGE_PANELNAME, panel);
        add(panel, BorderLayout.CENTER);
    }

}
