package com.crenu.kiosk.ui;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class UIManager extends JFrame {
    private HashMap<String, JPanel> panels;

    public UIManager(){
        setSize(860, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setVisible(true);
        panels = new HashMap<>();
    }

    public void panelSetVisible(String panelName, boolean state) {
        panels.get(panelName).setVisible(state);
    }

    public void allPanelVisibleOff() {
        for (JPanel panel: panels.values()) {
            panel.setVisible(false);
        }
    }


    public void addComponent(String name, Component component){
        panels.get(name).add(component);
    }

    public void removeComponent(String name, Component component){
        panels.get(name).remove(component);
    }

    public void addPanel(String name, JPanel panel){
        panels.put(name, panel);
        add(panel);
        setVisible(true);
    }

    public void removePanel(String name, JPanel panel){
        panels.remove(name);
        remove(panel);
        setVisible(true);
    }

    public void remove(){
        for(JPanel panel : panels.values()){
            remove(panel);
        }
        panels.clear();
        validate();
        repaint();
    }


}
