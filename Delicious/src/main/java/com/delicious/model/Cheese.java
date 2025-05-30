package com.delicious.model;

import com.delicious.utility.DeliMenu;

public class Cheese extends Topping {
    private boolean isExtra;
    private int extraCount;

    public Cheese(String name) {
        super(name);
        this.isExtra = isExtra;
        this.extraCount = extraCount;
    }
    public boolean isExtra() {
        return isExtra;
    }
    public int getExtraCount() {
        return extraCount;
    }
    public double getPrice(String sandwichSize) {
        return DeliMenu.getToppingPrice(getName(),"cheese", sandwichSize, isExtra, extraCount);
    }
    @Override
    public String toString() {
        return getName();
    }

}

