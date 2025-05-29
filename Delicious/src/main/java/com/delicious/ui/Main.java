package com.delicious.ui;

import com.delicious.data.Order;
import com.delicious.model.*;

import java.io.IOException;
import java.util.*;

public class Main {
    private Scanner scanner = new Scanner(System.in);
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
                    System.out.println("Exiting application. Goodbye have a nice day!");
                    break;
                default:
                    System.out.println("Please try again.");
            }
        } while (choice != 0);

        scanner.close();
    }

    private void displayHomeScreen() {
        System.out.println("------HOME SCREEN----");
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
            System.out.println("\n-----ORDER SCREEN-----");
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
        return scanner.nextInt();
    }

    private void displayOrderItemsSummary(Order order) {
        if (order.getItems().isEmpty()) {
            System.out.println("   No items in order yet. :(");
        } else {
            System.out.println("--- Current Order Items (Total: $" + String.format("%.2f", order.getTotalCost()) + ") ---");
            List<Topping> reversedItems = new ArrayList<>(order.getItems());
            Collections.reverse(reversedItems);
            reversedItems.forEach(item -> System.out.println("  - " + item.getDescription() + " (Total: $" + String.format("%.2f", item.calculatePrice()) + ")"));
            System.out.println("--------------------------------");
        }
    }

    private void addSandwich() {
        System.out.println("\n-----ADD SANDWICH-----");

        String selectedSize = selectSandwichSize();
        String selectedBread = selectBreadType();

        Sandwich sandwich = new Sandwich(selectedSize, selectedBread);

        promptForToppings(sandwich);

        boolean wantsToasted = promptBoolean("Would you like the sandwich toasted? (+$" + String.format("%.2f", DeliMenu.TOASTING_FEE) + ")");
        sandwich.setToasted(wantsToasted);

        currentOrder.addItem(sandwich);
        System.out.println("Sandwich added to order!");
    }
    private boolean promptBoolean(String message) {
        System.out.print(message + " (y/n): ");
        String input = scanner.next().trim().toLowerCase();
        return input.equals("y") || input.equals("yes");
    }

    private String selectSandwichSize() {
        return userChoice("size", DeliMenu.SANDWICH_SIZES, DeliMenu::getBreadPrice);
    }

    private String selectBreadType() {
        return userChoice("bread", DeliMenu.BREAD_TYPES, null);
    }


    private void promptForToppings(Sandwich sandwich) {
        System.out.println("\n--- Customize Toppings ---");

        addAdditionalToppings(sandwich, "Meat", DeliMenu.MEATS, DeliMenu.getMeatExtraPrice(sandwich.getSize()));
        addAdditionalToppings(sandwich, "Cheese", DeliMenu.CHEESES, DeliMenu.getCheeseExtraPrice(sandwich.getSize()));
        addRegularToppings(sandwich, "Regular Toppings", DeliMenu.REGULAR_TOPPINGS, RegularTopping.class);
        addRegularToppings(sandwich, "Sauces", DeliMenu.SAUCES, Sauce.class);
        addRegularToppings(sandwich, "Sides", DeliMenu.SIDES, Side.class);
    }

    private void addAdditionalToppings(Sandwich sandwich, String categoryName, List<String> availableToppings, double extraPrice) {
        System.out.println("\n--- Add " + categoryName + " ---");
        for (String toppingName : availableToppings) {
            if (promptBoolean("  Add " + toppingName + "?")) {
                if (categoryName.equals("Meat")) {
                    sandwich.addMeat(toppingName);
                } else if (categoryName.equals("Cheese")) {
                    sandwich.addCheese(toppingName);
                }
            }
        }
    }

    private void addRegularToppings(Sandwich sandwich, String categoryName, List<String> availableToppings, Class<? extends Topping> toppingClass) {
        System.out.println("\n--- Add " + categoryName + " (Included) ---");
        for (String toppingName : availableToppings) {
            if (promptBoolean("  Add " + toppingName)) {
                try {
                    Topping topping = toppingClass.getConstructor(String.class).newInstance(toppingName);
                    sandwich.addTopping(topping);
                } catch (Exception e) {
                    System.err.println("Error adding topping " + toppingName + ": " + e.getMessage());
                }
            }
        }
    }

    private void addDrink() {
        System.out.println("\n----Drink----");

        String selectedSize = userChoice("drink size", DeliMenu.DRINK_SIZES, DeliMenu::getDrinkPrice);
        String selectedFlavor = userChoice("drink flavor", DeliMenu.DRINK_FLAVORS, null);

        Drink drink = new Drink(selectedFlavor, selectedSize);
        currentOrder.addItem(drink);
        System.out.println("Drink!");
    }

    private void addChips() {
        System.out.println("\n----Chips-----");
        String selectedChipType = userChoice("chip type", DeliMenu.CHIP_TYPES, DeliMenu::getChipPrice);

        Chips chips = new Chips(selectedChipType);
        currentOrder.addItem(chips);
        System.out.println("Chips added!");
    }
    private void checkout() {
        System.out.println("--- Checkout---");
    }
    private void cancelOrder() {
        currentOrder = null;
        System.out.println("Order canceled :p.");
    }
}

