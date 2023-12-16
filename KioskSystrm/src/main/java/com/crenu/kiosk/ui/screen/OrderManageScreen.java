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
            checkOrder(orderNum);
        });

        JButton cancleButton = new JButton("Cancle");
        cancleButton.addActionListener(e -> {
            cancleOrder(orderNum);
            orderPanel.setVisible(false);
            orderListPanel.revalidate();
            orderListPanel.repaint();
        });

        JButton completeButton = new JButton("Complete");
        completeButton.addActionListener(e -> {
            completeOrder(orderNum);
        });

        JButton recallCompleteButton = new JButton("Recall Compelete");
        recallCompleteButton.addActionListener(e -> {
            recallCompleteOrder(orderNum);
            orderPanel.setVisible(false);
            orderListPanel.revalidate();
            orderListPanel.repaint();
        });

        orderPanel.add(orderDetails);
        orderPanel.add(checkButton);
        orderPanel.add(cancleButton);
        orderPanel.add(completeButton);
        orderPanel.add(recallCompleteButton);
        orderListPanel.add(orderPanel);
        orderListPanel.revalidate();
        orderListPanel.repaint();
    }

    public void checkOrder(Integer orderNum){
        orderSystem.updateOrderState(orderNum, OrderState.COOKING);
    }
    public void cancleOrder(Integer orderNum){
        orderSystem.updateOrderState(orderNum, OrderState.CANCLE);
        orderSystem.removeOrder(orderNum);
    }
    public void completeOrder(Integer orderNum){
        orderSystem.updateOrderState(orderNum, OrderState.COMPLETE);
    }
    public void recallCompleteOrder(Integer orderNum){
        orderSystem.removeOrder(orderNum);
        orderSystem.removeOrderReceipts(orderNum);
    }
}
