package com.crenu.kiosk.ui;

import com.crenu.kiosk.placeOrder.Order;

import javax.swing.*;
import java.awt.*;

public class OrderSystemScreen extends JFrame {

    public OrderSystemScreen(Order order){
        setSize(460, 600);
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
