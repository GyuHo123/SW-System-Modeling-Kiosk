package com.crenu.kiosk.placeOrder;

import com.crenu.kiosk.admin.Item;
import com.crenu.kiosk.cart.CartItem;
import com.crenu.kiosk.entity.OrderState;

import java.util.ArrayList;
import java.util.Iterator;

public class Order{

    private ArrayList<CartItem> orderMenus;
    private int orderNumber = 0;
    private OrderState orderState;
    public Order(ArrayList<CartItem> orderMenus){
        this.orderMenus = (ArrayList<CartItem>)orderMenus.clone();
        this.orderState = OrderState.CHECKING;
    }

    public void setOrderNumber(int orderNum){
        orderNumber = orderNum;
    }
    public int getOrderNumber(){
        return orderNumber;
    }

    public void setOrderState(OrderState orderState){
        this.orderState = orderState;
    }
    public String toString(){
        StringBuilder str = new StringBuilder("Order Number " + orderNumber + "\n");
        int totalPrice = 0;
        for(CartItem item : orderMenus){
            int price = item.getCount()*item.getPrice();
            str.append(item.getName()).append(" count : ").append(item.getCount()).append(" price : ").append(item.getCount() * item.getPrice()).append("\n");
            totalPrice+=price;
        }
        str.append("TotalPrice : ").append(totalPrice).append("\n");
        str.append("State : ").append(this.orderState.getName());
        return str.toString();
    }
}
