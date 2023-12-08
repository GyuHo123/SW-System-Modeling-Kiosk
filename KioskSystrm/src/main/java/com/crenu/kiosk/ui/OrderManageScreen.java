package com.crenu.kiosk.ui;

import com.crenu.kiosk.placeOrder.Order;

import javax.swing.*;
import java.awt.*;

import static com.crenu.kiosk.KioskSystem.orderSystem;

public class OrderManageScreen extends JPanel {
    private JPanel orderListPanel;

    public OrderManageScreen() {
        setLayout(new BorderLayout());
        initOrderList();
    }

    private void initOrderList() {
        orderListPanel = new JPanel();
        orderListPanel.setLayout(new FlowLayout());
        orderListPanel.setPreferredSize(new Dimension(860, 800));

        for (Integer orderNum : orderSystem.getOrderNumbers()) {
            Order order = orderSystem.getOrder(orderNum);
            addOrderPanel(order);
        }

        JScrollPane scrollPane = new JScrollPane(orderListPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void addOrderPanel(Order order) {
        JPanel orderPanel = new JPanel();
        orderPanel.setLayout(new BorderLayout());
        orderPanel.setPreferredSize(new Dimension(860, 100));
        JLabel orderDetails = new JLabel("<html>" + order.toString().replaceAll("\n", "<br>") + "</html>");

        JButton completeButton = new JButton("Complete");
        completeButton.addActionListener(e -> {
            orderSystem.removeOrder(order.getOrderNumber());
            orderPanel.setVisible(false);
        });

        orderPanel.add(orderDetails, BorderLayout.CENTER);
        orderPanel.add(completeButton, BorderLayout.EAST);
        orderListPanel.add(orderPanel);
    }
}
