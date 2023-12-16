package com.crenu.kiosk.ui.panel;

import javax.swing.*;

public abstract class KioskPanel extends JPanel {
    public KioskPanel() {init();}
    public abstract void init();
    public abstract void changeAction();
}
