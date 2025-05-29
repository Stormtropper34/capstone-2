package com.delicious.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeliMenu {
  feature/sandwich-builder
    public static final List<Item> breadPrices = new ArrayList<>();
    public static final List<PriceTopping> toppingPrices = new ArrayList<>();
    public static final List<Item> drinkPrices = new ArrayList<>();
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
        breadPrices.add(new Item("Bread", "4", 5.50));
        breadPrices.add(new Item("Bread", "8", 7.00));
        breadPrices.add(new Item("Bread", "12", 8.50));

        for (String meat : meats) {
            toppingPrices.add(new PriceTopping(meat, "meat", "4", 1.00, 0.50));
            toppingPrices.add(new PriceTopping(meat, "meat", "8", 2.00, 1.00));
            toppingPrices.add(new PriceTopping(meat, "meat", "12", 3.00, 1.50));
        }

        for (String cheese : cheeses) {
            toppingPrices.add(new PriceTopping(cheese, "cheese", "4", 0.75, 0.30));
            toppingPrices.add(new PriceTopping(cheese, "cheese", "8", 1.50, 0.60));
            toppingPrices.add(new PriceTopping(cheese, "cheese", "12", 2.25, 0.90));
        }

        for (String topping : regularTopping) {
            toppingPrices.add(new PriceTopping(topping, "regular", "4", 0.00, 0.00));
            toppingPrices.add(new PriceTopping(topping, "regular", "8", 0.00, 0.00));
            toppingPrices.add(new PriceTopping(topping, "regular", "12", 0.00, 0.00));
        }

        for (String sauce : sauces) {
            toppingPrices.add(new PriceTopping(sauce, "sauce", "4", 0.00, 0.00));
            toppingPrices.add(new PriceTopping(sauce, "sauce", "8", 0.00, 0.00));
            toppingPrices.add(new PriceTopping(sauce, "sauce", "12", 0.00, 0.00));
        }

        for (String side : sides) {
            toppingPrices.add(new PriceTopping(side, "side", "4", 0.00, 0.00));
            toppingPrices.add(new PriceTopping(side, "side", "8", 0.00, 0.00));
            toppingPrices.add(new PriceTopping(side, "side", "12", 0.00, 0.00));
        }

        drinkPrices.add(new Item("Drink", "Small", 2.00));
        drinkPrices.add(new Item("Drink", "Medium", 2.50));
        drinkPrices.add(new Item("Drink", "Large", 3.00));
    }

    public static double getBreadPrice(String size) {
        for (Item item : breadPrices) {
            if (item.getName().equals("Bread") && item.getSize().equals(size)) {
                return item.getPrice();
            }
        }
        return 0.00;
    }

    private static PriceTopping getTopping(String name, String type, String size) {
        for (PriceTopping t : toppingPrices) {
            if (t.getName().equalsIgnoreCase(name)
                    && t.getType().equalsIgnoreCase(type)
                    && t.getSandwichSize().equals(size)) {
                return t;
            }
        }
        return null;
    }

    public static double getMeatPrice(String name, String size, boolean isExtra, int extraCount) {
        PriceTopping t = getTopping(name, "meat", size);
        if (t == null) return 0.00;
        return isExtra ? t.getExtraPrice() * extraCount : t.getBasePrice();
    }

    public static double getCheesePrice(String name, String size, boolean isExtra, int extraCount) {
        PriceTopping t = getTopping(name, "cheese", size);
        if (t == null) return 0.00;
        return isExtra ? t.getExtraPrice() * extraCount : t.getBasePrice();
    }

    public static double getRegularToppingPrice(String name, String size) {
        PriceTopping t = getTopping(name, "regular", size);
        return (t != null) ? t.getBasePrice() : 0.00;
    }

    public static double getSaucePrice(String name, String size) {
        PriceTopping t = getTopping(name, "sauce", size);
        return (t != null) ? t.getBasePrice() : 0.00;
    }

    public static double getSidePrice(String name, String size) {
        PriceTopping t = getTopping(name, "side", size);
        return (t != null) ? t.getBasePrice() : 0.00;
    }

    public static double getDrinkPrice(String size) {
        for (Item item : drinkPrices) {
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
        for (PriceTopping t : toppingPrices) {
            if (t.getType().equals("meat") && t.getSandwichSize().equals(size)) {
                return t.getExtraPrice();
            }
        }
        return 0.00;
    }

    public static double getExtraPriceForCheese(String size) {
        for (PriceTopping t : toppingPrices) {
            if (t.getType().equals("cheese") && t.getSandwichSize().equals(size)) {
                return t.getExtraPrice();
            }
        }
        return 0.00;
    }

    public static double getToastedFee() {
        return toastedFee;
=======
    public static final double TOASTING_FEE = 0.00;
    public static final List<String> SANDWICH_SIZES = Arrays.asList("4", "8", "12");
    public static final List<String> BREAD_TYPES = Arrays.asList("White", "Wheat", "Rye", "Wrap");

    public static final List<String> MEATS = Arrays.asList("Steak", "Ham", "Salami", "Roast Beef", "Chicken", "Bacon");
    public static final List<String> CHEESES = Arrays.asList("American", "Provolone", "Cheddar", "Swiss");
    public static final List<String> REGULAR_TOPPINGS = Arrays.asList(
            "Lettuce", "Peppers", "Onions", "Tomatoes", "Jalapeños", "Cucumbers", "Pickles", "Guacamole", "Mushrooms"
    );
    public static final List<String> SAUCES = Arrays.asList(
            "Mayo", "Mustard", "Ketchup", "Ranch", "Thousand Islands", "Vinaigrette"
    );
    public static final List<String> SIDES = Arrays.asList(
            "Au Jus", "Sauce"
    );

    public static final List<String> DRINK_SIZES = Arrays.asList("Small", "Medium", "Large");
    public static final List<String> DRINK_FLAVORS = Arrays.asList("Coke", "Pepsi", "Sprite", "Water");
    public static final List<String> CHIP_TYPES = Arrays.asList("Lay's", "Doritos", "Cheetos", "Pringles");



    public static double getBreadPrice(String size) {
        switch (size) {
            case "4": return 5.50;
            case "8": return 7.00;
            case "12": return 8.50;
            default: return 0.00;
        }
    }

    public static double getMeatBasePrice(String sandwichSize) {
        switch (sandwichSize) {
            case "4": return 1.00;
            case "8": return 2.00;
            case "12": return 3.00;
            default: return 0.00;
        }
    }

    public static double getMeatExtraPrice(String sandwichSize) {
        switch (sandwichSize) {
            case "4": return 0.50;
            case "8": return 1.00;
            case "12": return 1.50;
            default: return 0.00;
        }
    }

    public static double getCheeseBasePrice(String sandwichSize) {
        switch (sandwichSize) {
            case "4": return 0.75;
            case "8": return 1.50;
            case "12": return 2.25;
            default: return 0.00;
        }
    }

    public static double getCheeseExtraPrice(String sandwichSize) {
        switch (sandwichSize) {
            case "4": return 0.30;
            case "8": return 0.60;
            case "12": return 0.90;
            default: return 0.00;
        }
    }


    public static double getDrinkPrice(String size) {
        switch (size) {
            case "Small": return 2.00;
            case "Medium": return 2.50;
            case "Large": return 3.00;
            default: return 0.00;
        }
    }

    public static double getChipPrice(String chipType) {
        return 1.50;

 main
    }
}