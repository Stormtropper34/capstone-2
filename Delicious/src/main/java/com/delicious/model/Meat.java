package com.delicious.model;

public class Meat extends Topping{
    private boolean isExtra;
    private int extraCount;

    public Meat(String name) {
        super(name);
        this.name = name;
        this.isExtra = isExtra;
        this.extraCount = extraCount;
    }


    @Override
    public double getPrice(String sandwichSize) {
        return DeliMenu.getToppingPrice(getName(),"meat", sandwichSize, isExtra, extraCount);
    }

    @Override
    public String toString() {
        return isExtra ? getName() + " x" + extraCount + " (Extra)" : getName();
    }
}
