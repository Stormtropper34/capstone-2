package com.delicious.model;

public abstract class Topping {
    protected String name;

    public Topping(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract double getPrice(String sandwichSize);

    @Override
    public String toString() {
        return getName();
    }
}
