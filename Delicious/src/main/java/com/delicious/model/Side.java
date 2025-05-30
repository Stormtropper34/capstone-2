package com.delicious.model;

public class Side extends Topping{
    public Side(String name) {
        super(name);
    }

    @Override
    public double getPrice(String sandwichSize) {
        return DeliMenu.getToppingPrice(getName(), "side", sandwichSize, false, 0);
    }

    @Override
    public String toString() {
        return getName();
    }
}
