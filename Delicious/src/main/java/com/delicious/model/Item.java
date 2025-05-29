package com.delicious.model;

public class Item extends MenuItem {
    private String size;
    private double price;

    public Item(String name, String size, double price) {
        super(name);
        this.size = size;
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public double totalPrice() {
        return price;
    }

    @Override
    public String getSummary() {
        return name + " (" + size + "): $" + price;
    }
}
