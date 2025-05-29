package com.delicious.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeliMenu {
        public static final List<MenuItem> BREAD_PRICES = new ArrayList<>();
        public static final List<Topping> TOPPING_PRICES = new ArrayList<>();
        public static final List<MenuItem> DRINK_PRICES = new ArrayList<>();
        public static final double CHIP_PRICE = 1.50;
        public static final double TOASTING_FEE = 0.50;

        public static final List<String> BREAD_TYPES = Arrays.asList("White", "Wheat", "Rye", "Wrap");
        public static final List<String> SANDWICH_SIZES = Arrays.asList("4", "8", "12");

        public static final List<String> MEATS = Arrays.asList("Steak", "Ham", "Salami", "Roast Beef", "Chicken", "Bacon");
        public static final List<String> CHEESES = Arrays.asList("American", "Provolone", "Cheddar", "Swiss");
        public static final List<String> REGULAR_TOPPINGS = Arrays.asList(
                "Lettuce", "Peppers", "Onions", "Tomatoes", "Jalapeños",
                "Cucumbers", "Pickles", "Guacamole", "Mushrooms"
        );
        public static final List<String> SAUCES = Arrays.asList(
                "Mayo", "Mustard", "Ketchup", "Ranch", "Thousand Islands", "Vinaigrette"
        );
        public static final List<String> SIDES = Arrays.asList(
                "Au Jus", "Sauce"
        );

        public static final List<String> DRINK_SIZES = Arrays.asList("Small", "Medium", "Large");
        public static final List<String> DRINK_FLAVORS = Arrays.asList("Coke", "Pepsi", "Sprite", "Orange Juice", "Water");
        public static final List<String> CHIP_TYPES = Arrays.asList("BBQ", "Salt & Vinegar", "Original", "Cheddar & Sour Cream");

        static {

            BREAD_PRICES.add(new MenuItem("Bread", "4", 5.50) {});
            BREAD_PRICES.add(new MenuItem("Bread", "8", 7.00) {});
            BREAD_PRICES.add(new MenuItem("Bread", "12", 8.50) {});

            TOPPING_PRICES.add(new Topping("Steak", "meat", "4", 1.00, 0.50));
            TOPPING_PRICES.add(new Topping("Steak", "meat", "8", 2.00, 1.00));
            TOPPING_PRICES.add(new Topping("Steak", "meat", "12", 3.00, 1.50));
            TOPPING_PRICES.add(new Topping("Ham", "meat", "4", 1.00, 0.50));
            TOPPING_PRICES.add(new Topping("Ham", "meat", "8", 2.00, 1.00));
            TOPPING_PRICES.add(new Topping("Ham", "meat", "12", 3.00, 1.50));
            TOPPING_PRICES.add(new Topping("Salami", "meat", "4", 1.00, 0.50));
            TOPPING_PRICES.add(new Topping("Salami", "meat", "8", 2.00, 1.00));
            TOPPING_PRICES.add(new Topping("Salami", "meat", "12", 3.00, 1.50));
            TOPPING_PRICES.add(new Topping("Roast Beef", "meat", "4", 1.00, 0.50));
            TOPPING_PRICES.add(new Topping("Roast Beef", "meat", "8", 2.00, 1.00));
            TOPPING_PRICES.add(new Topping("Roast Beef", "meat", "12", 3.00, 1.50));
            TOPPING_PRICES.add(new Topping("Chicken", "meat", "4", 1.00, 0.50));
            TOPPING_PRICES.add(new Topping("Chicken", "meat", "8", 2.00, 1.00));
            TOPPING_PRICES.add(new Topping("Chicken", "meat", "12", 3.00, 1.50));
            TOPPING_PRICES.add(new Topping("Bacon", "meat", "4", 1.00, 0.50));
            TOPPING_PRICES.add(new Topping("Bacon", "meat", "8", 2.00, 1.00));
            TOPPING_PRICES.add(new Topping("Bacon", "meat", "12", 3.00, 1.50));


            TOPPING_PRICES.add(new Topping("American", "cheese", "4", 0.75, 0.30));
            TOPPING_PRICES.add(new Topping("American", "cheese", "8", 1.50, 0.60));
            TOPPING_PRICES.add(new Topping("American", "cheese", "12", 2.25, 0.90));
            TOPPING_PRICES.add(new Topping("Provolone", "cheese", "4", 0.75, 0.30));
            TOPPING_PRICES.add(new Topping("Provolone", "cheese", "8", 1.50, 0.60));
            TOPPING_PRICES.add(new Topping("Provolone", "cheese", "12", 2.25, 0.90));
            TOPPING_PRICES.add(new Topping("Cheddar", "cheese", "4", 0.75, 0.30));
            TOPPING_PRICES.add(new Topping("Cheddar", "cheese", "8", 1.50, 0.60));
            TOPPING_PRICES.add(new Topping("Cheddar", "cheese", "12", 2.25, 0.90));
            TOPPING_PRICES.add(new Topping("Swiss", "cheese", "4", 0.75, 0.30));
            TOPPING_PRICES.add(new Topping("Swiss", "cheese", "8", 1.50, 0.60));
            TOPPING_PRICES.add(new Topping("Swiss", "cheese", "12", 2.25, 0.90));


            for (String topping : REGULAR_TOPPINGS) {
                TOPPING_PRICES.add(new Topping(topping, "regular", "4", 0.00, 0.00));
                TOPPING_PRICES.add(new Topping(topping, "regular", "8", 0.00, 0.00));
                TOPPING_PRICES.add(new Topping(topping, "regular", "12", 0.00, 0.00));
            }
            for (String sauce : SAUCES) {
                TOPPING_PRICES.add(new Topping(sauce, "sauce", "4", 0.00, 0.00));
                TOPPING_PRICES.add(new Topping(sauce, "sauce", "8", 0.00, 0.00));
                TOPPING_PRICES.add(new Topping(sauce, "sauce", "12", 0.00, 0.00));
            }
            for (String side : SIDES) {
                TOPPING_PRICES.add(new Topping(side, "side", "4", 0.00, 0.00));
                TOPPING_PRICES.add(new Topping(side, "side", "8", 0.00, 0.00));
                TOPPING_PRICES.add(new Topping(side, "side", "12", 0.00, 0.00));
            }



            DRINK_PRICES.add(new MenuItem("Drink", "Small", 2.00));
            DRINK_PRICES.add(new MenuItem("Drink", "Medium", 2.50));
            DRINK_PRICES.add(new MenuItem("Drink", "Large", 3.00));
        }
        public static double getBreadPrice(String size) {
            for (MenuItem menuItem: BREAD_PRICES) {
                if (menuItem.getName().equals("Bread") && menuItem.getSize().equals(size)) {
                    return menuItem.getPrice();
                }
            }
            return 0.00;
        }

        private static Topping getTopping(String name, String type, String size) {
            for (Topping toppings : TOPPING_PRICES) {
                if (toppings.getName().equalsIgnoreCase(name) && toppings.getType().equalsIgnoreCase(type) && toppings.getSize().equals(size)) {
                    return toppings;
                }
            }
            return null;
        }

        public static double getMeatPrice(String name, String size, boolean isExtra, int extraCount) {
            Topping toppings = getTopping(name, "meat", size);
            if (toppings == null) {
                return 0.00;
            }
            if (!isExtra) {
                return 0.00;
            }
            return toppings.getExtraPrice() * extraCount;
        }

        public static double getCheesePrice(String name, String size, boolean isExtra, int extraCount) {
            Topping toppings = getTopping(name, "cheese", size);
            if (toppings == null) {
                return 0.00;
            }
            if (!isExtra) {
                return 0.00;
            }
            return toppings.getExtraPrice() * extraCount;
        }

        public static double getRegularToppingPrice(String name, String size) {
            Topping data = getTopping(name, "regular", size);
            return (data != null) ? data.getBasePrice() : 0.00;
        }

        public static double getSaucePrice(String name, String size) {
            Topping toppings = getTopping(name, "sauce", size);
            return (toppings != null) ? toppings.getBasePrice() : 0.00;
        }

        public static double getSidePrice(String name, String size) {
            Topping toppings = getTopping(name, "side", size);
            return (toppings != null) ? toppings.getBasePrice() : 0.00;
        }

        public static double getDrinkPrice(String size) {
            for (MenuItem menuItem: DRINK_PRICES) {
                if (menuItem.getName().equals("Drink") && menuItem.getSize().equals(size)) {
                    return menuItem.getPrice();
                }
            }
            return 0.00;
        }

        public static double getChipPrice() {
            return CHIP_PRICE;
        }

        public static double getMeatExtraPriceForDisplay(String size) {
            for (Topping toppings : TOPPING_PRICES) {
                if (toppings.getType().equals("meat") && toppings.getSize().equals(size)) {
                    return toppings.getExtraPrice();
                }
            }
            return 0.00;
        }

        public static double getCheeseExtraPriceForDisplay(String size) {
            for (Topping toppings: TOPPING_PRICES) {
                if (toppings.getType().equals("cheese") && toppings.getSize().equals(size)) {
                    return toppings.getExtraPrice();
                }
            }
            return 0.00;
        }
    }
}
