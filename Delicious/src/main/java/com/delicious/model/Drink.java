package com.delicious.model;

public class Drink extends MenuItem{
    private String flavor;
    private String size;


    public Drink(String flavor, String size) {
        super(flavor + " Drink");
        this.flavor = flavor;
        this.size = size;
    }

    public String getFlavor() {
        return flavor;
    }

    public String getSize() {
        return size;
    }

    @Override
    public double getTotalPrice() {
        return DeliMenu.getDrinkPrice(size);
    }

    @Override
    public String getSummary() {
        return String.format("%s (%s)", flavor, size);
    }

    @Override
    public String toString() {
        return String.format("%s (%s) - $%.2f", flavor, size, getTotalPrice());
    }
}
