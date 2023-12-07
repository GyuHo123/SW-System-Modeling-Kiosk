package com.crenu.kiosk;

import com.crenu.kiosk.ui.InitialScreen;
import com.crenu.kiosk.ui.UIManager;

public class KioskSystem {
    public static UIManager uiManager;
    public static void main(String[] args) {
        uiManager = new UIManager();
        InitialScreen.init();
    }
}