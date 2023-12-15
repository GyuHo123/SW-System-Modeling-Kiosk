package com.crenu.kiosk.entity;

public enum OrderState {
    COOKING("Cooking"),
    COMPLETE("Complete"),
    RECALLCOMPLETE("RecallComplete"),
    CANCLE("Cancle"),
    CHECKING("Checking");
    final private String stateName;
    OrderState(String name) {
        this.stateName = name;
    }
    public String getName() {
        return stateName;
    }
}
