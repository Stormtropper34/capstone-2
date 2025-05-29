package com.delicious.model;

public class Cheese extends Topping{
    private boolean isExtra;

    public Cheese(String name, boolean isExtra) {
        super(name);
        this.isExtra = isExtra;
    }

    public boolean isExtra() {
        return isExtra;
    }

    @Override
    public double getPrice(String sandwichSize) {
        if (isExtra) {
            return DeliMenu.getExtraPriceForCheese(sandwichSize);
        } else {
            return DeliMenu.getCheeseBasePrice(sandwichSize);

        }
    }

    @Override
    public String toString() {
        return isExtra ? getName() + " (Extra)" : getName();
}
