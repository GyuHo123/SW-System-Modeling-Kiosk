package com.crenu.kiosk.ui.screen;

import com.crenu.kiosk.placeOrder.Order;

import javax.swing.*;
import java.awt.*;

public class OrderReceiptScreen extends JFrame {

    public OrderReceiptScreen(Order order){
        setSize(460, 460);
        setTitle("ORDER" + order.getOrderNumber());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel info = new JLabel();
        info.setText("<html>" + order.toString().replaceAll("<","&lt;").replaceAll(">",
                "&gt;").replaceAll("\n", "<br/>") + "</html>");
        add(info);
        setVisible(true);
    }
}
