package com.delicious.ui;

import com.delicious.model.Order;
import com.delicious.model.*;
import com.delicious.utility.DeliMenu;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;


public class Main {
    private final Scanner scanner = new Scanner(System.in);
    private Order currentOrder;
    private static final DateTimeFormatter FILE_NAME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");

    public static void main(String[] args) {
        Main app = new Main();
        app.run();
    }

    public void run() {
        System.out.println("Welcome to Delicious sandwiches!!");
        int choice;
        do {
            displayHomeScreen();
            choice = userChoice("Enter your choice (0-1): ");

            switch (choice) {
                case 1:
                    startNewOrder();
                    break;
                case 0:
                    System.out.println("Exiting application. Goodbye, have a nice day!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
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
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
            System.out.print(prompt);
        }
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    private String userChoice(String label, List<String> options, List<Double> prices) {
        System.out.println("Choose a " + label + ":");

        for (int i = 0; i < options.size(); i++) {
            if (prices != null && prices.size() > i) {
                System.out.println((i + 1) + ") " + options.get(i) + " ($" + String.format("%.2f", prices.get(i)) + ")");
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
                scanner.nextLine();
                if (choice >= 1 && choice <= options.size()) {
                    valid = true;
                } else {
                    System.out.println("That number is not in the list. Please try again.");
                }
            } else {
                System.out.println("That's not a number. Please try again.");
                scanner.next();
                scanner.nextLine();
            }
        }
        return options.get(choice - 1);
    }

    private String userChoice(String label, List<String> options, Function<String, Double> priceFunction) {
        System.out.println("Choose a " + label + ":");

        for (int i = 0; i < options.size(); i++) {
            String option = options.get(i);
            double price = priceFunction.apply(option);
            System.out.println((i + 1) + ") " + option + " ($" + String.format("%.2f", price) + ")");
        }

        int choice = 0;
        boolean valid = false;

        while (!valid) {
            System.out.print("Enter your choice (1 to " + options.size() + "): ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice >= 1 && choice <= options.size()) {
                    valid = true;
                } else {
                    System.out.println("That number is not in the list. Please try again.");
                }
            } else {
                System.out.println("That's not a number. Please try again.");
                scanner.next();
                scanner.nextLine();
            }
        }
        return options.get(choice - 1);
    }

    private boolean promptBoolean(String message) {
        System.out.print(message + " (y/n): ");
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("y") || input.equals("yes");
    }


    private void displayOrderItemsSummary(Order order) {
        if (order.getItems().isEmpty()) {
            System.out.println("   No items in order yet. :(");
        } else {
            System.out.println("--- Current Order Items (Subtotal: $" + String.format("%.2f", order.getTotalCost()) + ") ---");
            List<MenuItem> reversedItems = new ArrayList<>(order.getItems());
            Collections.reverse(reversedItems);
            for (MenuItem item : reversedItems) {
                System.out.println("  - " + item.getSummary() + " (Price: $" + String.format("%.2f", item.getTotalPrice()) + ")");
            }
            System.out.println("--------------------------------");
        }
    }


    private void addSandwich() {
        System.out.println("\n----- ADD SANDWICH -----");

        String selectedSize = selectedSize();
        String selectedBread = selectBreadType();

        Sandwich sandwich = new Sandwich(selectedSize, selectedBread);
        promptForToppings(sandwich);

        boolean wantsToasted = promptBoolean("Do you want your sandwich toasted? (+$" + String.format("%.2f", DeliMenu.toastedFee) + ")");
        sandwich.setToasted(wantsToasted);

        currentOrder.addItem(sandwich);
        System.out.println("Sandwich added to order!");
    }

    private String selectedSize() {
        return userChoice("sandwich size: ", DeliMenu.sandwichSizes, DeliMenu::getSandwichSizeBasePrice);
    }

    private String selectBreadType() {
        return userChoice("bread", DeliMenu.typeOfBread, DeliMenu::getBreadPrice);
    }

    private void promptForToppings(Sandwich sandwich) {
        System.out.println("\n--- Customize Toppings ---");

        addToppingSelections(sandwich, "Meat", DeliMenu.meats);
        addToppingSelections(sandwich, "Cheese", DeliMenu.cheeses);
        addToppingSelections(sandwich, "Regular Toppings", DeliMenu.regularToppings);
        addToppingSelections(sandwich, "Sauces", DeliMenu.sauces);
        addToppingSelections(sandwich, "Sides", DeliMenu.sides);
    }

    private void addToppingSelections(Sandwich sandwich, String categoryName, List<String> toppings) {
        System.out.println("\n--- Add " + categoryName + " ---");
        if (toppings.isEmpty()) {
            System.out.println("No " + categoryName.toLowerCase() + " available.");
            return;
        }

        for (String toppingName : toppings) {
            if (promptBoolean("  Add " + toppingName + "? (+$" + String.format("%.2f", DeliMenu.getSandwichSizeBasePrice(toppingName)) + ")")) {
                Topping topping = null;
                if (categoryName.equals("Meat")) {
                    topping = new Meat(toppingName);
                } else if (categoryName.equals("Cheese")) {
                    topping = new Cheese(toppingName);
                } else if (categoryName.equals("Regular Toppings")) {
                    topping = new RegularTopping(toppingName);
                } else if (categoryName.equals("Sauces")) {
                    topping = new Sauce(toppingName);
                } else if (categoryName.equals("Sides")) {
                    topping = new Side(toppingName);
                } else {
                    System.err.println("Error: Unknown topping category: " + categoryName);
                    continue;
                }

                if (topping != null) {
                    sandwich.addTopping(topping);
                    System.out.println("  Added " + toppingName + ".");
                }
            }
        }
    }


    private void addDrink() {
        System.out.println("\n---- ADD DRINK ----");
        List<Double> drinkSizePrices = new ArrayList<>();
        for (String size : DeliMenu.drinkSizes) {
            drinkSizePrices.add(DeliMenu.getDrinkPrice(size));
        }
        String size = userChoice("drink size", DeliMenu.drinkSizes, drinkSizePrices);
        String flavor = userChoice("drink flavor", DeliMenu.drinkFlavors, (List<Double>) null);

        currentOrder.addItem(new Drink(flavor, size));
        System.out.println("Drink added!");
    }

    private void addChips() {
        System.out.println("\n---- ADD CHIPS ----");
        List<Double> chipPrices = new ArrayList<>();
        for (String type : DeliMenu.chipTypes) {
            chipPrices.add(DeliMenu.getChipPrice());
        }
        String chipType = userChoice("chip type", DeliMenu.chipTypes, chipPrices);
        currentOrder.addItem(new Chips(chipType));
        System.out.println("Chips added!");
    }



    private void checkout() {
        if (currentOrder == null || currentOrder.getItems().isEmpty()) {
            System.out.println("Cannot confirm, order is empty.");
        }

        System.out.println("\n--- CONFIRMING ORDER ---");
        String orderDetails = currentOrder.getDetails();

        System.out.println(orderDetails);
        String filename = "receipt.txt";

        try (FileWriter fileWriter = new FileWriter("receipt.txt", false);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {

            printWriter.println("-----  RECEIPT -----");
            printWriter.println(orderDetails);
            printWriter.println("----------------------------------------");
            printWriter.println(String.format("Order Total: $%.2f", currentOrder.getTotalCost()));
            printWriter.println("----------------------------------------");

            System.out.println("Receipt is saved!!");

        } catch (IOException e) {
            System.err.println("Error saving receipt to file: " + e.getMessage());
        }

        double finalTotal = currentOrder.getTotalCost();
        System.out.println("Your total is: $" + String.format("%.2f", finalTotal));
        System.out.println("Thank you for your order!");

        currentOrder = null;

    }

    private void cancelOrder() {
        currentOrder = null;
        System.out.println("Order canceled :(. Back to home screen");
    }
}


