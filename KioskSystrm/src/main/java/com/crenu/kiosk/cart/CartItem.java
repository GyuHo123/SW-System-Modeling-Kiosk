package com.crenu.kiosk.cart;

import com.crenu.kiosk.admin.Item;
import com.crenu.kiosk.entity.Category;

import java.util.ArrayList;

public class CartItem extends Item {

    private ArrayList<String> options;
    private int count;
    public CartItem(String menuName, Integer price, Category category) {
        super(menuName, price, category);
        this.options = new ArrayList<>();
        this.count = 1;
    }
    public void addSide(String item){
        this.options.add(item);
    }
    public void removeSide(String item){
        this.options.remove(item);
    }

    public void addCount(){
        this.count++;
    }

    public int getCount(){
        return this.count;
    }

    public void minusCount(){
        this.count--;
        if(this.count < 0){
            this.count = 0;
        }
    }

    public void setCount(int count){
        this.count = count;
    }

}
