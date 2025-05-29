package com.delicious.model;

public class PriceTopping {
        private String name;
        private String type;
        private String sandwichSize;
        private double basePrice;
        private double extraPrice;

        public PriceTopping(String name, String type, String sandwichSize, double basePrice, double extraPrice) {
            this.name = name;
            this.type = type;
            this.sandwichSize = sandwichSize;
            this.basePrice = basePrice;
            this.extraPrice = extraPrice;
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }

        public String getSandwichSize() {
            return sandwichSize;
        }

        public double getBasePrice() {
            return basePrice;
        }

        public double getExtraPrice() {
            return extraPrice;
        }
    }
}
