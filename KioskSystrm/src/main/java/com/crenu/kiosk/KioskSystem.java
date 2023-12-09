package com.crenu.kiosk;

import com.crenu.kiosk.admin.MenuManager;
import com.crenu.kiosk.cart.Cart;
import com.crenu.kiosk.placeOrder.OrderSystem;
import com.crenu.kiosk.ui.InitialScreen;
import com.crenu.kiosk.ui.MenuManageScreen;
import com.crenu.kiosk.ui.UIManager;

public class KioskSystem {
    public static UIManager uiManager;
    public static MenuManager menuManager;
    public static Cart cart;
    public static OrderSystem orderSystem;
    public static void main(String[] args) {
        uiManager = new UIManager();
        orderSystem = new OrderSystem();
        init();
    }
    public static void init(){
        uiManager.remove();
        menuManager = new MenuManager();
        cart = new Cart();
        InitialScreen.init();
    }
}