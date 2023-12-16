package com.crenu.kiosk.placeOrder;


import com.crenu.kiosk.entity.OrderState;
import com.crenu.kiosk.ui.screen.OrderStateScreen;

import java.util.HashMap;
import java.util.Set;

import static com.crenu.kiosk.KioskSystem.cart;
import static com.crenu.kiosk.KioskSystem.orderSystem;

public class OrderSystem {

    private HashMap<Integer, Order> orderList;
    private HashMap<Integer, OrderStateScreen> orderReceipts;
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
        orderReceipts.put(orderNum, new OrderStateScreen(order.getOrderNumber()));
        return orderNum;
    }



    public void removeOrder(int orderNum){
        orderList.remove(orderNum);
    }

    public void removeOrderReceipts(int orderNum){
        orderReceipts.get(orderNum).dispose();
        orderReceipts.remove(orderNum);
    }

    public void updateOrderState(int orderNum, OrderState state){
        orderList.get(orderNum).setOrderState(state);
        orderSystem.orderReceipts(orderNum).updateInfoText();
    }

    public Set<Integer> getOrderNumbers() {
        return orderList.keySet();
    }


    public Order getOrder(int orderNum){
        return  orderList.get(orderNum);
    }

    public OrderStateScreen orderReceipts(int orderNum){
        return  orderReceipts.get(orderNum);
    }
}
