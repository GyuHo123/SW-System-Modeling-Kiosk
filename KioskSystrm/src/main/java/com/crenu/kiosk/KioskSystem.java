package com.crenu.kiosk;

import com.crenu.kiosk.ui.InitialScreen;
import com.crenu.kiosk.ui.UIManager;

public class KioskSystem {
    public static void main(String[] args) {
        UIManager.main = new UIManager();
        new InitialScreen();
    }
}