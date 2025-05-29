package com.delicious.model;

public class Drink extends MenuItem{
    private String type;
    private String size;

    public Drink(String type, String size) {
        super(type + " (" + size + ") Drink");
        this.type = type;
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public String getSize() {
        return size;
    }

    @Override
    public double totalPrice() {
        return DeliMenu.getDrinkPrice(this.size);
    }

    @Override
    public String getSummary() {
        return getName();
    }

    @Override
    public String toString() {
        return String.format("%s (%s) - $%.2f", type, size, totalPrice());
    }
}
