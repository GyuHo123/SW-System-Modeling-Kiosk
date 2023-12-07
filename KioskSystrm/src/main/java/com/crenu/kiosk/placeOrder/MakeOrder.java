package com.crenu.kiosk.placeOrder;

import com.crenu.kiosk.placeOrder.ModifyOrder;

public class MakeOrder {
    private ModifyOrder modifyOrder;

    public MakeOrder(ModifyOrder modifyOrder) {
        this.modifyOrder = modifyOrder;
    }

    public void finalizeOrder() {
        if (!modifyOrder.getItems().isEmpty()) {
            int totalAmount = modifyOrder.getTotalPrice();
            proceedToPayment(totalAmount);
        } else {
            System.out.println("Order is empty. Cannot proceed to payment.");
        }
    }

    private void proceedToPayment(int totalAmount) {
        // Actual payment implementation here
        System.out.println("Proceeding to payment with total amount: " + totalAmount);
    }

    public void displayCurrentOrder() {
        modifyOrder.displayOrder();
    }
}
