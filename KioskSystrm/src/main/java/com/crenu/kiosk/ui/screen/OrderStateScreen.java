package com.crenu.kiosk.ui.screen;

import com.crenu.kiosk.entity.OrderState;

import javax.swing.*;
import java.awt.*;

import static com.crenu.kiosk.KioskSystem.orderSystem;

public class OrderStateScreen extends JFrame {

    private JLabel infoText;
    private int orderNum;

    public OrderStateScreen(int orderNum){
        setSize(460, 460);
        setLayout(new BorderLayout());
        this.orderNum = orderNum;
        setTitle("ORDER" + orderNum);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());
        infoText= new JLabel();
        add(infoText, BorderLayout.NORTH);
        updateInfoText();
        setVisible(true);
    }

    public void updateInfoText(){
        infoText.setText("<html>" + orderSystem.getOrder(orderNum).toString().replaceAll("<","&lt;").replaceAll(">",
                "&gt;").replaceAll("\n", "<br/>") + "</html>");
        revalidate();
        repaint();
    }

}
