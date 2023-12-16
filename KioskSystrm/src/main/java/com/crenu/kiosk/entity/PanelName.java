package com.crenu.kiosk.entity;

public enum PanelName {
    INITIAL_PANELNAME("InitialScreen"),
    MENU_PANELNAME("MenuDisplayScreen"),
    LOGIN_PANELNAME("LoginScreen"),
    CART_PANELNAME("CartScreen"),
    MANAGER_PANELNAME("ManagerScreen"),
    MENU_MANAGE_PANELNAME("MenuManageScreen"),
    ORDER_MANAGE_PANELNAME("OrderManageScreen");
    final private String name;
    PanelName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
