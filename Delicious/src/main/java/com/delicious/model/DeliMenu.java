package com.delicious.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeliMenu {
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

    }
}
