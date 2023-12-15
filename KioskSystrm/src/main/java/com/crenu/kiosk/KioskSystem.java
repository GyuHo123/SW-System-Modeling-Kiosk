package com.crenu.kiosk;

import com.crenu.kiosk.admin.MenuManager;
import com.crenu.kiosk.cart.Cart;
import com.crenu.kiosk.placeOrder.OrderSystem;
import com.crenu.kiosk.ui.panel.PanelManager;
import com.crenu.kiosk.ui.screen.InitialScreen;

import static com.crenu.kiosk.ui.entity.PanelNameEntity.INITAL_PANELNAME;

public class KioskSystem {
    public static PanelManager panelManager;
    public static MenuManager menuManager;
    public static Cart cart;
    public static OrderSystem orderSystem;
    public static void main(String[] args) {
        orderSystem = new OrderSystem();
        menuManager = new MenuManager();
        cart = new Cart();
        panelManager = new PanelManager();
    }
    public static void dataInit(){
        cart.clear();
    }
}