package com.crenu.kiosk.cart;

import com.crenu.kiosk.admin.Item;
import com.crenu.kiosk.menu.Menu;
import com.crenu.kiosk.placeOrder.OrderedItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Cart {
    private ArrayList<CartItem> cartItems;
    public Cart(){
        cartItems = new ArrayList<>();
    }

    public ArrayList<CartItem> getCartItems(){
        return this.cartItems;
    }
    public void addCartItem(CartItem item){
        cartItems.add(item);
    }

    public void removeCartItem(CartItem item){
        cartItems.remove(item);
    }

}
