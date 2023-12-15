package com.crenu.kiosk.ui.entity;

public enum PanelNameEntity {
    INITAL_PANELNAME("InitialScreen"),
    MENU_PANELNAME("MenuDisplayScreen"),
    LOGIN_PANELNAME("LoginScreen"),
    CART_PNAELNAME("CartScreen"),
    MANAGER_PANELNAME("ManagerScreen"),
    MENU_MANAGE_PNAELNAME("MenuManageScreen"),
    ORDER_MANAGE_PANELNAME("OrderManageScreen");
    final private String name;
    PanelNameEntity(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
