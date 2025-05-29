package com.delicious.ui;

import com.delicious.data.Order;
import com.delicious.model.*;
import java.util.*;

public class Main {
    private final Scanner scanner = new Scanner(System.in);
    private Order currentOrder;

    public static void main(String[] args) {
        Main app = new Main();
        app.run();
    }

    public void run() {
        int choice;
        do {
            displayHomeScreen();
            choice = userChoice("Enter your choice 0-1: ");

            switch (choice) {
                case 1:
                    startNewOrder();
                    break;
                case 0:
                    System.out.println("Exiting application. Goodbye, have a nice day!");
                    break;
                default:
                    System.out.println("Please try again.");
            }
        } while (choice != 0);

        scanner.close();
    }

    private void displayHomeScreen() {
        System.out.println("------ HOME SCREEN ------");
        System.out.println("1) New Order");
        System.out.println("0) Exit");
    }

    private void startNewOrder() {
        currentOrder = new Order();
        displayOrderScreen();
    }

    private void displayOrderScreen() {
        int choice;
        do {
            System.out.println("\n----- ORDER SCREEN -----");
            displayOrderItemsSummary(currentOrder);

            System.out.println("1) Add Sandwich");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Chips");
            System.out.println("4) Checkout");
            System.out.println("0) Cancel Order");

            choice = userChoice("Enter your choice: ");

            switch (choice) {
                case 1:
                    addSandwich();
                    break;
                case 2:
                    addDrink();
                    break;
                case 3:
                    addChips();
                    break;
                case 4:
                    checkout();
                    return;
                case 0:
                    cancelOrder();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    private int userChoice(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. " + prompt);
            scanner.next();
        }
        return scanner.nextInt();
    }

    private String userChoice(String label, List<String> options, List<Double> prices) {
        System.out.println("Choose a " + label + ":");

        for (int i = 0; i < options.size(); i++) {
            if (prices != null && prices.size() > i) {
                System.out.println((i + 1) + ") " + options.get(i) + " ($" + prices.get(i) + ")");
            } else {
                System.out.println((i + 1) + ") " + options.get(i));
            }
        }
        int choice = 0;
        boolean valid = false;

        while (!valid) {
            System.out.print("Enter your choice (1 to " + options.size() + "): ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice >= 1 && choice <= options.size()) {
                    valid = true;
                } else {
                    System.out.println("That number is not in the list.");
                }
            } else {
                System.out.println("That's not a number.");
                scanner.next();
            }
        }

        return options.get(choice - 1);
    }


    private void displayOrderItemsSummary(Order order) {
        if (order.getItems().isEmpty()) {
            System.out.println("   No items in order yet. :(");
        } else {
            System.out.println("--- Current Order Items (Total: $" + String.format("%.2f", order.getTotalCost()) + ") ---");
            List<MenuItem> reversedItems = new ArrayList<>(order.getItems());
            Collections.reverse(reversedItems);
            for (MenuItem item : reversedItems) {
                System.out.println("  - " + item.getDescription() + " (Price: $" + String.format("%.2f", item.getTotalCost()) + ")");
            }
            System.out.println("--------------------------------");
        }
    }

    private void addSandwich() {
        System.out.println("\n----- ADD SANDWICH -----");

        String selectedSize = selectSandwichSize();
        String selectedBread = selectBreadType();

        Sandwich sandwich = new Sandwich(selectedSize, selectedBread);
        promptForToppings(sandwich);

        boolean wantsToasted = promptBoolean("Do you want your sandwich toasted? (+$" + String.format("%.2f", DeliMenu.toastedFee) + ")");
        sandwich.setToasted(wantsToasted);

        currentOrder.addItem(sandwich);
        System.out.println("Sandwich added to order!");
    }

    private String selectSandwichSize() {
        return userChoice("sandwich size", DeliMenu.sandwichSizes, DeliMenu::getBreadPrice);
    }

    private String selectBreadType() {
        return userChoice("bread", DeliMenu.typeOfBread, null);
    }

    private boolean promptBoolean(String message) {
        System.out.print(message + " (y/n): ");
        String input = scanner.next().trim().toLowerCase();
        return input.equals("y") || input.equals("yes");
    }

    private void promptForToppings(Sandwich sandwich) {
        System.out.println("\n--- Customize Toppings ---");

        addAdditionalToppings(sandwich, "Meat", DeliMenu.meats);
        addAdditionalToppings(sandwich, "Cheese", DeliMenu.cheeses);
        addRegularToppings(sandwich, "Regular Toppings", DeliMenu.regularTopping, RegularTopping.class);
        addRegularToppings(sandwich, "Sauces", DeliMenu.sauces, Sauce.class);
        addRegularToppings(sandwich, "Sides", DeliMenu.sides, Side.class);
    }

    private void addAdditionalToppings(Sandwich sandwich, String categoryName, List<String> toppings) {
        System.out.println("\n--- Add " + categoryName + " ---");
        for (String topping : toppings) {
            if (promptBoolean("  Add " + topping + "?")) {
                if (categoryName.equals("Meat")) {
                    sandwich.addMeat(topping);
                } else if (categoryName.equals("Cheese")) {
                    sandwich.addCheese(topping);
                }
            }
        }
    }

    private void addRegularToppings(Sandwich sandwich, String categoryName, List<String> toppings, Class<? extends Topping> clazz) {
        System.out.println("\n--- Add " + categoryName + " ---");
        for (String topping : toppings) {
            if (promptBoolean("  Add " + topping + "?")) {
                try {
                    Topping t = clazz.getConstructor(String.class).newInstance(topping);
                    sandwich.addTopping(t);
                } catch (Exception e) {
                    System.out.println("Error adding " + topping + ": " + e.getMessage());
                }
            }
        }
    }

    private void addDrink() {
        System.out.println("\n---- DRINK ----");
        String size = userChoice("drink size", DeliMenu.drinkSizes, DeliMenu::getDrinkPrice);
        String flavor = userChoice("drink flavor", DeliMenu.drinkFlavors, null);

        currentOrder.addItem(new Drink(flavor, size));
        System.out.println("Drink added!");
    }

    private void addChips() {
        System.out.println("\n---- CHIPS ----");
        String chipType = userChoice("chip type", DeliMenu.chipTypes, null);
        currentOrder.addItem(new Chips(chipType));
        System.out.println("Chips added!");
    }

    private void checkout() {
        System.out.println("--- CHECKOUT ---");
        System.out.println("Total Order Cost: $" + String.format("%.2f", currentOrder.getTotalCost()));
    }

    private void cancelOrder() {
        currentOrder = null;
        System.out.println("Order canceled :(");
    }
}


