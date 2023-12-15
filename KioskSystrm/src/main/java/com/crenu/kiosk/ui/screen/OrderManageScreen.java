package com.crenu.kiosk.ui.screen;

import com.crenu.kiosk.entity.OrderState;
import com.crenu.kiosk.placeOrder.Order;
import com.crenu.kiosk.ui.panel.KioskPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.crenu.kiosk.KioskSystem.orderSystem;
import static com.crenu.kiosk.KioskSystem.panelManager;
import static com.crenu.kiosk.entity.PanelName.MANAGER_PANELNAME;

public class OrderManageScreen extends KioskPanel {
    private JPanel orderListPanel;


    @Override
    public void init() {
        setLayout(new BorderLayout());
        initOrderList();

        JButton backButton = new JButton("back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.changePanel(MANAGER_PANELNAME.getName());
            }
        });
        add(backButton,BorderLayout.SOUTH);
    }

    @Override
    public void changeAction() {
        updateOrderList();
    }

    private void initOrderList() {
        orderListPanel = new JPanel();
        orderListPanel.setLayout(new FlowLayout());
        orderListPanel.setPreferredSize(new Dimension(860, 800));

        JScrollPane scrollPane = new JScrollPane(orderListPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void updateOrderList(){
        orderListPanel.removeAll();
        for (Integer orderNum : orderSystem.getOrderNumbers()) {
            Order order = orderSystem.getOrder(orderNum);
            addOrderPanel(order);
        }
    }

    private void addOrderPanel(Order order) {
        JPanel orderPanel = new JPanel();
        orderPanel.setLayout(new FlowLayout());
        orderPanel.setPreferredSize(new Dimension(860, 100));
        JLabel orderDetails = new JLabel("<html>" + order.toString().replaceAll("\n", "<br>") + "</html>");
        int orderNum = order.getOrderNumber();

        JButton checkButton = new JButton("Check");
        checkButton.addActionListener(e -> {
            orderSystem.updateOrderState(orderNum, OrderState.COOKING);
        });

        JButton cancleButton = new JButton("Cancle");
        cancleButton.addActionListener(e -> {
            orderSystem.updateOrderState(orderNum, OrderState.CANCLE);
            orderSystem.removeOrder(orderNum);
            orderPanel.setVisible(false);
            orderListPanel.revalidate();
            orderListPanel.repaint();
        });

        JButton completeButton = new JButton("Complete");
        completeButton.addActionListener(e -> {
            orderSystem.updateOrderState(orderNum, OrderState.COMPLETE);
        });

        JButton recallCompeleteButton = new JButton("Recall Compelete");
        recallCompeleteButton.addActionListener(e -> {
            orderSystem.removeOrder(orderNum);
            orderSystem.removeOrderReceipts(orderNum);
            orderPanel.setVisible(false);
            orderListPanel.revalidate();
            orderListPanel.repaint();
        });

        orderPanel.add(orderDetails);
        orderPanel.add(checkButton);
        orderPanel.add(cancleButton);
        orderPanel.add(completeButton);
        orderPanel.add(recallCompeleteButton);
        orderListPanel.add(orderPanel);
        orderListPanel.revalidate();
        orderListPanel.repaint();
    }


}
