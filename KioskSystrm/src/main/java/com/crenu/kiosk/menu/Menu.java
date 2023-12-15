package com.crenu.kiosk.menu;

import com.crenu.kiosk.admin.Item;
import com.crenu.kiosk.cart.CartItem;
import com.crenu.kiosk.entity.Category;

public class Menu extends Item {

    public Menu(String name, Integer price, Category category) {
        super(name, price, category);
    }

    public CartItem toCartItem(){
        return new CartItem(getName(), getPrice(), getCategory());
    }
}
