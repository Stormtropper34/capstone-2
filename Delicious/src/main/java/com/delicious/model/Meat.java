package com.delicious.model;

public class Meat extends Topping{
    private boolean isExtra;
    private int extraCount;

    public Meat(String name, boolean isExtra, int extraCount) {
        super(name);
        this.isExtra = isExtra;
        this.extraCount = extraCount;
    }

    @Override
    public double getPrice(String sandwichSize) {
        return DeliMenu.getMeatPrice(getName(), sandwichSize, isExtra, extraCount);
    }

    @Override
    public String toString() {
        return isExtra ? getName() + " x" + extraCount + " (Extra)" : getName();
    }
}
