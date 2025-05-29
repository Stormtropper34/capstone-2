package com.delicious.model;

public class Chips extends MenuItem{
    private String type;

    public Chips(String name, String type) {
        super(name);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public double totalPrice() {
        return DeliMenu.getChipPrice();
    }

    @Override
    public String getSummary() {
        return getName();
    }

    @Override
    public String toString() {
        return String.format("%s Chips - $%.2f", type, totalPrice());
    }
}
