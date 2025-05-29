package com.delicious.model;

public class Chips extends MenuItem{
    private String type;

    public Chips(String type) {
        super(type + " Chips"); // Name for MenuItem is "Lay's Chips"
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public double calculatePrice() {
        return DeliMenu.getChipPrice(this.type);
    }

    @Override
    public String getDescription() {
        return getName(); // Returns "Lay's Chips"
    }

    @Override
    public String toString() {
        return String.format("%s Chips - $%.2f", type, calculatePrice());
    }
}
