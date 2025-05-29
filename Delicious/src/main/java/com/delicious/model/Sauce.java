package com.delicious.model;

public class Sauce extends Topping {
    public Sauce(String name) {
        super(name);
    }

    @Override
    public double getPrice(String sandwichSize) {
        return 0.00;
    }
}
