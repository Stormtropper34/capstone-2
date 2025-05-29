package com.delicious.data;

import com.delicious.model.MenuItem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
    private int orderId;
    private LocalDateTime orderDateTime;
    private List<MenuItem> items;
    private int nextOrder;

    public Order() {
        this.nextOrder = nextOrder;
        this.orderDateTime = LocalDateTime.now();
        this.items = new ArrayList<>();
    }

    public int getOrderId() {
        return orderId;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public List<MenuItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public double getTotalCost() {
        return items.stream()
                .mapToDouble(MenuItem::calculatePrice)
                .sum();
    }

    public String getDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Order ID: %d\n", orderId));
        sb.append(String.format("Date/Time: %s\n", orderDateTime.format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
        sb.append("--- Items ---\n");
        if (items.isEmpty()) {
            sb.append("  No items in this order.\n");
        } else {
            for (int i = 0; i < items.size(); i++) {
                MenuItem item = items.get(i);
                sb.append(String.format("  %d. %s (Price: $%.2f)\n", (i + 1), item.getDescription().replace("\n", "\n     "), item.calculatePrice()));
            }
        }
        sb.append("-------------\n");
        sb.append(String.format("Total: $%.2f\n", getTotalCost()));
        return sb.toString();
    }
}
