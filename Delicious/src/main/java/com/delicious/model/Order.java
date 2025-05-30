package com.delicious.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private LocalDateTime orderDateTime;
    private List<MenuItem> items;


    public Order() {
        this.orderDateTime = LocalDateTime.now();
        this.items = new ArrayList<>();
    }


    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public List<MenuItem> getItems() {
        return new ArrayList<>(items);
    }

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public double getTotalCost() {
        return items.stream()
                .mapToDouble(MenuItem::getTotalPrice)
                .sum();
    }

    public String getDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Date/Time: %s\n", orderDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
        sb.append("--- Items ---\n");
        if (items.isEmpty()) {
            sb.append("  No items in this order.\n");
        } else {
            for (int i = 0; i < items.size(); i++) {
                MenuItem item = items.get(i);
                sb.append(String.format("  %d. %s (Price: $%.2f)\n", (i + 1), item.getSummary().replace("\n", "\n      "), item.getTotalPrice()));
            }
        }
        sb.append("-------------\n");
        sb.append(String.format("Total: $%.2f\n", getTotalCost()));
        return sb.toString();
    }
}
