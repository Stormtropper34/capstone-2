package com.delicious.model;

public class Meat extends Topping{
    private int servings;

    public Meat(String name, int servings) {
        super(name);
        this.servings = servings;
    }

    public int getServings() {
        return servings;
    }
    public void addServings(int count) {
        this.servings += count;
    }

    @Override
    public double getPrice(String sandwichSize) {
        double basePrice = DeliMenu.getToppingPrice("meat", "base", sandwichSize);
        double extraPricePerServing = DeliMenu.getToppingPrice("meat", "extra", sandwichSize);

        if (servings == 1) {
            return basePrice;
        } else {
            return basePrice + ((servings - 1) * extraPricePerServing);
        }
    }

    @Override
    public String toString() {
        if (servings > 1) {
            return name + servings;
        }
        return name;
    }
}
