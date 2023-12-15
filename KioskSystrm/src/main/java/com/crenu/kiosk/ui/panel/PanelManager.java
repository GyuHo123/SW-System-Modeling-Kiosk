package com.crenu.kiosk.ui.panel;

import com.crenu.kiosk.ui.screen.*;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

import static com.crenu.kiosk.entity.PanelName.*;

public class PanelManager extends JFrame {
    private HashMap<String, KioskPanel> panels;

    public PanelManager(){
        setSize(860, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        panels = new HashMap<>();

        initAddPanel();
        panelSetVisible(INITAL_PANELNAME.getName(), true);
        setVisible(true);
    }

    public void initAddPanel(){
        addPanel(INITAL_PANELNAME.getName(), new InitialScreen());
        addPanel(MENU_PANELNAME.getName(), new MenuDisplayScreen());
        addPanel(LOGIN_PANELNAME.getName(), new LoginScreen());
        addPanel(CART_PNAELNAME.getName(), new CartScreen());
        addPanel(MANAGER_PANELNAME.getName(), new ManagerScreen());
        addPanel(MENU_MANAGE_PNAELNAME.getName(), new MenuManageScreen());
        addPanel(ORDER_MANAGE_PANELNAME.getName(), new OrderManageScreen());
    }

    private void panelSetVisible(String panelName, boolean state) {
        panels.get(panelName).setVisible(state);
    }

    private void allPanelVisibleOff() {
        for (KioskPanel panel: panels.values()) {
            panel.setVisible(false);
        }
    }

    public void changePanel(String panelName){
        allPanelVisibleOff();
        panelSetVisible(panelName, true);
        panels.get(panelName).changeAction();
        revalidate();
        repaint();
    }


    public void addPanel(String name, KioskPanel panel){
        panels.put(name, panel);
        panelSetVisible(name, false);
        add(panel);
    }


    public void removeAll(){
        for(KioskPanel panel : panels.values()){
            remove(panel);
        }
        panels.clear();
        validate();
        repaint();
    }


}
