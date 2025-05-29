package com.delicious.model;

public class Cheese extends Topping{
    private int servings;

    public Cheese(String name, int servings) {
        super(name);
        this.servings = servings;
        if (this.servings < 1) { this.servings = 1; }
    }

    public int getServings() {
        return servings;
    }
    public void addServings(int count) {
        this.servings += count;
    }

    @Override
    public double getPrice(String sandwichSize) {
        double basePrice = DeliMenu.getToppingPrice("cheese", "base", sandwichSize);
        double extraPricePerServing = DeliMenu.getToppingPrice("cheese", "extra", sandwichSize);

        if (servings == 1) {
            return basePrice;
        } else {
            return basePrice + ((servings - 1) * extraPricePerServing);
        }
    }

    @Override
    public String toString() {
        if (servings > 1) {
            return name + " (x" + servings + ")";
        }
        return name;
    }
}
