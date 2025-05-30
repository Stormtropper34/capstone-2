package com.delicious.model;

public abstract class MenuItem {
    protected String name;


    public MenuItem(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public abstract double getTotalPrice();

    public abstract String getSummary();
}
