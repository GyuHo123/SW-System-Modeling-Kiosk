package com.crenu.kiosk.placeOrder;

import java.util.List;

public class MakeOrder {
    private ModifyOrder modifyOrder;

    public MakeOrder(ModifyOrder modifyOrder) {
        this.modifyOrder = modifyOrder;
    }

    public void finalizeOrder() {
        int totalAmount = modifyOrder.getTotalPrice();
        proceedToPayment(totalAmount);
    }

    private void proceedToPayment(int totalAmount) {
        //TODO UI 구현 후 화면 넘어가도록 설계
        System.out.println("Proceeding to payment with total amount: " + totalAmount);
    }

    public void displayCurrentOrder() {
        //TODO UI 구현 후 호출
        modifyOrder.displayOrder();
    }
}
