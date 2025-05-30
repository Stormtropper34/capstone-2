package com.delicious.model;

import com.delicious.utility.DeliMenu;

public class Chips extends MenuItem{
    private String type;

    public Chips(String type) {
        super("Chips");
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public double getTotalPrice() {
        return DeliMenu.getChipPrice();
    }

    @Override
    public String getSummary() {
        return "Chips (" + type + ")";
    }
}
