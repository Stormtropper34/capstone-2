package com.delicious.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeliMenu {
    public static final List<MenuItem> breadPrices = new ArrayList<>();
    public static final List<PriceTopping> toppingPrices = new ArrayList<>();
    public static final List<MenuItem> drinkPrices = new ArrayList<>();
    public static final double chipPrice = 1.50;
    public static final double toastedFee = 0.50;

    public static final List<String> typeOfBread = Arrays.asList("White", "Wheat", "Rye", "Wrap");
    public static final List<String> sandwichSizes = Arrays.asList("4", "8", "12");

    public static final List<String> meats = Arrays.asList("Steak", "Ham", "Salami", "Roast Beef", "Chicken", "Bacon");
    public static final List<String> cheeses = Arrays.asList("American", "Provolone", "Cheddar", "Swiss");
    public static final List<String> regularTopping = Arrays.asList("Lettuce", "Peppers", "Onions", "Tomatoes", "Jalapeños", "Cucumbers", "Pickles", "Guacamole", "Mushrooms");
    public static final List<String> sauces = Arrays.asList("Mayo", "Mustard", "Ketchup", "Ranch", "Thousand Islands", "Vinaigrette");
    public static final List<String> sides = Arrays.asList("Au Jus", "Sauce");

    public static final List<String> drinkSizes = Arrays.asList("Small", "Medium", "Large");
    public static final List<String> drinkFlavors = Arrays.asList("Coke", "Pepsi", "Sprite", "Orange Juice", "Water");
    public static final List<String> chipTypes = Arrays.asList("BBQ", "Salt & Vinegar", "Original", "Cheddar & Sour Cream");

    static {
        breadPrices.add(new MenuItem("Bread", "4", 5.50));
        breadPrices.add(new MenuItem("Bread", "8", 7.00));
        breadPrices.add(new MenuItem("Bread", "12", 8.50));

        for (String meat : meats) {
            toppingPrices.add(new Topping(meat, "meat", "4", 1.00, 0.50));
            toppingPrices.add(new Topping(meat, "meat", "8", 2.00, 1.00));
            toppingPrices.add(new Topping(meat, "meat", "12", 3.00, 1.50));
        }

        for (String cheese : cheeses) {
            toppingPrices.add(new Topping(cheese, "cheese", "4", 0.75, 0.30));
            toppingPrices.add(new Topping(cheese, "cheese", "8", 1.50, 0.60));
            toppingPrices.add(new Topping(cheese, "cheese", "12", 2.25, 0.90));
        }

        for (String topping : regularTopping) {
            toppingPrices.add(new Topping(topping, "regular", "4", 0.00, 0.00));
            toppingPrices.add(new Topping(topping, "regular", "8", 0.00, 0.00));
            toppingPrices.add(new Topping(topping, "regular", "12", 0.00, 0.00));
        }

        for (String sauce : sauces) {
            toppingPrices.add(new Topping(sauce, "sauce", "4", 0.00, 0.00));
            toppingPrices.add(new Topping(sauce, "sauce", "8", 0.00, 0.00));
            toppingPrices.add(new Topping(sauce, "sauce", "12", 0.00, 0.00));
        }

        for (String side : sides) {
            toppingPrices.add(new Topping(side, "side", "4", 0.00, 0.00));
            toppingPrices.add(new Topping(side, "side", "8", 0.00, 0.00));
            toppingPrices.add(new Topping(side, "side", "12", 0.00, 0.00));
        }

        drinkPrices.add(new MenuItem("Drink", "Small", 2.00));
        drinkPrices.add(new MenuItem("Drink", "Medium", 2.50));
        drinkPrices.add(new MenuItem("Drink", "Large", 3.00));
    }

    public static double getBreadPrice(String size) {
        for (MenuItem item : breadPrices) {
            if (item.getName().equals("Bread") && item.getSize().equals(size)) {
                return item.getPrice();
            }
        }
        return 0.00;
    }

    private static Topping getTopping(String name, String type, String size) {
        for (Topping t : toppingPrices) {
            if (t.getName().equalsIgnoreCase(name) && t.getType().equalsIgnoreCase(type) && t.getSize().equals(size)) {
                return t;
            }
        }
        return null;
    }

    public static double getMeatPrice(String name, String size, boolean isExtra, int extraCount) {
        Topping t = getTopping(name, "meat", size);
        if (t == null || !isExtra) return 0.00;
        return t.getExtraPrice() * extraCount;
    }

    public static double getCheeseBasePrice(String name) {
        Topping t = getTopping(name);
        if (t == null || !isExtra) return 0.00;
        return t.getExtraPrice() * extraCount;
    }

    public static double getRegularToppingPrice(String name, String size) {
        Topping t = getTopping(name, "regular", size);
        return (t != null) ? t.getBasePrice() : 0.00;
    }

    public static double getSaucePrice(String name, String size) {
        Topping t = getTopping(name, "sauce", size);
        return (t != null) ? t.getBasePrice() : 0.00;
    }

    public static double getSidePrice(String name, String size) {
        Topping t = getTopping(name, "side", size);
        return (t != null) ? t.getBasePrice() : 0.00;
    }

    public static double getDrinkPrice(String size) {
        for (MenuItem item : drinkPrices) {
            if (item.getName().equals("Drink") && item.getSize().equals(size)) {
                return item.getPrice();
            }
        }
        return 0.00;
    }

    public static double getChipPrice() {
        return chipPrice;
    }

    public static double getExtraPriceForMeat(String size) {
        for (Topping t : toppingPrices) {
            if (t.getType().equals("meat") && t.getSize().equals(size)) {
                return t.getExtraPrice();
            }
        }
        return 0.00;
    }

    public static double getExtraPriceForCheese(String size) {
        for (Topping t : toppingPrices) {
            if (t.getType().equals("cheese") && t.getSize().equals(size)) {
                return t.getExtraPrice();
            }
        }
        return 0.00;
    }
}