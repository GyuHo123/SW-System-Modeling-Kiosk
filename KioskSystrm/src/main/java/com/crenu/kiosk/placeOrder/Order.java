package com.crenu.kiosk.placeOrder;

import com.crenu.kiosk.admin.Item;
import com.crenu.kiosk.cart.CartItem;

import java.util.ArrayList;
import java.util.Iterator;

public class Order{

    private ArrayList<CartItem> orderMenus;
    private int orderNumber = 0;
    public Order(ArrayList<CartItem> orderMenus){
        this.orderMenus = (ArrayList<CartItem>)orderMenus.clone();
    }

    public void setOrderNumber(int orderNum){
        orderNumber = orderNum;
    }
    public int getOrderNumber(){
        return orderNumber;
    }
    public String toString(){
        String str = "Order Number " + orderNumber + "\n";
        int totalPrice = 0;
        for(CartItem item : orderMenus){
            int price = item.getCount()*item.getPrice();
            str+=item.getName()+" count : "+item.getCount()+" price : "+ item.getCount()*item.getPrice() + "\n";
            totalPrice+=price;
        }
        str+="TotalPrice : "+totalPrice;
        return str;
    }
}
