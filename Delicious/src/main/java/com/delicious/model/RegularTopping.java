package com.delicious.model;

public class RegularTopping extends Topping{
    public RegularTopping(String name) {
        super(name);
    }

    @Override
    public double getPrice(String sandwichSize) {
        return 0.00;
    }
}
