package com.delicious.model;

import java.util.ArrayList;
import java.util.List;

public class Sandwich extends MenuItem {
    private String size;
    private String bread;
    private boolean toasted;
    private List<Topping> toppings;

    public Sandwich(String size, String bread) {
        super("Customized Sandwich");
        this.size = size;
        this.bread = bread;
        this.toasted = false;
        this.toppings = new ArrayList<>();

    }

    public String getSize() {
        return size;
    }

    public String getBread() {
        return bread;
    }

    public boolean isToasted() {
        return toasted;
    }

    public void setToasted(boolean toasted) {
        this.toasted = toasted;
    }

    public List<Topping> getToppings() {
        return new ArrayList<>(toppings);
    }

    public void addTopping(Topping newTopping) {
        boolean isCurrentTopping = toppings.stream()
                .noneMatch(existing -> existing.getName().equalsIgnoreCase(newTopping.getName())
                        && existing.getClass().equals(newTopping.getClass()));
        if (isCurrentTopping) {
            toppings.add(newTopping);
        }
    }

    @Override
    public double totalPrice() {
        double price = DeliMenu.getBreadPrice(size);
        for (Topping topping : toppings) {
            price += topping.getPrice(size);
        }
        if (toasted) {
            price += DeliMenu.toastedFee;
        }
        return price;
    }

    @Override
    public String getSummary() {
        return toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(size).append("\" ").append(bread).append(" Sandwich");
        if (toasted) {
            sb.append(" (Toasted)");
        }

        if (!toppings.isEmpty()) {
            sb.append("\n    Toppings:");
            toppings.forEach(topping -> sb.append("\n  - ").append(topping.toString()));
        }
        sb.append(String.format("\n    Item Price: $%.2f", totalPrice()));
        return sb.toString();
    }
}