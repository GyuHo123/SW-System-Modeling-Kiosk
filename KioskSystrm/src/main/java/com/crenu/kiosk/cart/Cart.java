package com.crenu.kiosk.cart;

import java.util.ArrayList;

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
