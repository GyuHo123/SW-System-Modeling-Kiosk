package com.crenu.kiosk.placeOrder;


import com.crenu.kiosk.ui.screen.OrderManageScreen;
import com.crenu.kiosk.ui.screen.OrderReceiptScreen;

import java.util.HashMap;
import java.util.Set;

import static com.crenu.kiosk.KioskSystem.cart;

public class OrderSystem {

    private HashMap<Integer, Order> orderList;
    private HashMap<Integer, OrderReceiptScreen> orderReceipts;
    private int orderNum;
    public OrderSystem(){
        orderList = new HashMap<>();
        orderReceipts = new HashMap<>();
        orderNum = 0;
    }

    public int addNowOrder(){
        if(cart.getCartItems().isEmpty()) return -1;
        Order order = new Order(cart.getCartItems());
        orderNum++;
        order.setOrderNumber(orderNum);
        orderList.put(orderNum, order);
        orderReceipts.put(orderNum, new OrderReceiptScreen(order));
        return orderNum;
    }



    public void removeOrder(int orderNum){
        orderList.remove(orderNum);
        orderReceipts.get(orderNum).dispose();
        orderReceipts.remove(orderNum);
    }

    public Set<Integer> getOrderNumbers() {
        return orderList.keySet();
    }


    public Order getOrder(int orderNum){
        return  orderList.get(orderNum);
    }
}
