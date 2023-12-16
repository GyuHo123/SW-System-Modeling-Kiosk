package com.crenu.kiosk.entity;

public enum Category {
    MAIN(1),
    SIDE(2),
    DRINK(3),
    SET(4);

    private final int value;

    Category(int value) {
        this.value = value;
    }
}

