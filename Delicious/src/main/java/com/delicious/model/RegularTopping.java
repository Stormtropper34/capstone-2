package com.delicious.model;

import com.delicious.utility.DeliMenu;

public class RegularTopping extends Topping{
    public RegularTopping(String name) {
        super(name);
    }

    @Override
    public double getPrice(String sandwichSize) {
        return DeliMenu.getToppingPrice(getName(), "regular", sandwichSize, false, 0);
    }
    @Override
    public String toString() {
        return getName();
    }
}
