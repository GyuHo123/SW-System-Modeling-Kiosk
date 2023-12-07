package com.crenu.kiosk;

public class OrderedItem extends Menu{
    private Integer count;
    private Integer unitPrice; // Added to store the price of a single item

    OrderedItem(String menuName, Integer price, String category, Integer count) {
        super(menuName, price, category);
        this.unitPrice = price; // Initialize unitPrice with the original price
        this.count = count;
        this.setPrice(unitPrice * count); // Set the total price
    }

    public void increaseCount(){
        count++;
        this.setPrice(unitPrice * count); // Recalculate the total price based on unitPrice
    }

    public void decreaseCount(){
        if (count > 0) { // Only decrease if count is greater than 0
            count--;
            this.setPrice(unitPrice * count); // Recalculate the total price
        }
    }
    public Integer getCount(){
        return this.count;
    }
}
