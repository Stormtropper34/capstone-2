package com.delicious.model;

import java.util.ArrayList;

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

    public void addMeat(String meatName, int servings) {
        if (!DeliMenu.MEATS.contains(meatName)) {
            return;
        }

        for (Topping existingTopping : toppings) {
            if (existingTopping instanceof Meat && existingTopping.getName().equalsIgnoreCase(meatName)) {
                ((Meat) existingTopping).addServings(servings);
                return;
            }
        }
        this.toppings.add(new Meat(meatName, servings));
    }

    public void addCheese(String cheeseName, int servings) {
        if (!DeliMenu.CHEESES.contains(cheeseName)) {
            System.err.println("Warning: Attempted to add unknown cheese: " + cheeseName);
            return;
        }

        for (Topping existingTopping : toppings) {
            if (existingTopping instanceof Cheese && existingTopping.getName().equalsIgnoreCase(cheeseName)) {
                ((Cheese) existingTopping).addServings(servings);
                return;
            }
        }
        this.toppings.add(new Cheese(cheeseName, servings));
    }

    public void addTopping(Topping newTopping) {
        if (newTopping instanceof Meat || newTopping instanceof Cheese) {
            handlePremiumToppings(newTopping);
        } else {
            handleOtherToppings(newTopping);
        }
    }

    private void handlePremiumToppings(Topping newTopping) {
        boolean found = false;
        for (Topping oldTopping : toppings) {
            if (oldTopping.getName().equals(newTopping.getName()) &&
                    oldTopping.getClass().equals(newTopping.getClass())) {
                if (oldTopping instanceof Meat && newTopping instanceof Meat) {
                    ((Meat) oldTopping).addServings(((Meat) newTopping).getServings());
                } else if (oldTopping instanceof Cheese && newTopping instanceof Cheese) {
                    ((Cheese) oldTopping).addServings(((Cheese) newTopping).getServings());
                }
                found = true;
                break;
            }
        }
        if (!found) {
            this.toppings.add(newTopping);
        }
    }

    private void handleOtherToppings(Topping newTopping) {
        boolean exists = toppings.stream()
                .anyMatch(existingTopping -> existingTopping.getName().equalsIgnoreCase(newTopping.getName()) &&
                        existingTopping.getClass().equals(newTopping.getClass()));
        if (!exists) {
            this.toppings.add(newTopping);
        }
    }

    @Override
    public double calculatePrice() {
        double price = DeliMenu.getBreadPrice(this.size);

        for (Topping topping : toppings) {
            price += topping.getPrice(this.size);
        }

        if (this.toasted) {
            price += DeliMenu.TOASTING_FEE;
        }
        return price;
    }

    @Override
    public String getDescription() {
        return toString();
    }

    @Override
    public String toString() {
        StringBuilder details = new StringBuilder();
        details.append(size).append("\" ").append(bread).append(" Sandwich");
        if (toasted) {
            details.append(" (Toasted)");
        }

        if (!toppings.isEmpty()) {
            details.append("\n    Toppings:");
            toppings.forEach(topping -> details.append("\n      - ").append(topping.toString()));
        }
        details.append(String.format("\n    Item Price: $%.2f", calculatePrice()));
        return details.toString();
    }
}
