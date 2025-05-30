package com.delicious.model;

import com.delicious.utility.DeliMenu;

public class Sauce extends Topping {
    public Sauce(String name) {
        super(name);
    }

    @Override
    public double getPrice(String sandwichSize) {
        return DeliMenu.getToppingPrice(getName(), "sauce", sandwichSize, false, 0);
    }

    @Override
    public String toString() {
        return getName();
    }
}
