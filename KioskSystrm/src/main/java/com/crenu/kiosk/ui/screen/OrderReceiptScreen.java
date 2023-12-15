package com.crenu.kiosk.ui.screen;

import com.crenu.kiosk.placeOrder.Order;

import javax.swing.*;
import java.awt.*;
import java.util.jar.JarEntry;

import static com.crenu.kiosk.KioskSystem.orderSystem;

public class OrderReceiptScreen extends JFrame {

    private JLabel infoText;
    private int orderNum;

    public OrderReceiptScreen(int orderNum){
        setSize(460, 460);
        setLayout(new BorderLayout());
        this.orderNum = orderNum;
        setTitle("ORDER" + orderNum);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        infoText= new JLabel();
        String infostr = orderSystem.getOrder(orderNum).toString() + "\n" + "State : Cooking";
        infoText.setText("<html>" + infostr.replaceAll("<","&lt;").replaceAll(">",
                "&gt;").replaceAll("\n", "<br/>") + "</html>");
        add(infoText, BorderLayout.NORTH);
        setVisible(true);
    }

    public void compeleteState(){
        System.out.println(orderNum);
        String infostr = orderSystem.getOrder(orderNum).toString() + "\n" + "State : Compelete";
        infoText.setText("<html>" + infostr.replaceAll("<","&lt;").replaceAll(">",
                "&gt;").replaceAll("\n", "<br/>") + "</html>");
        revalidate();
        repaint();
    }

}
