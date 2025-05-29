package com.delicious.model;

public class Side extends Topping{
    public Side(String name) {
        super(name);
    }

    @Override
    public double getPrice(String sandwichSize) {
        return 0.00;
    }
}
