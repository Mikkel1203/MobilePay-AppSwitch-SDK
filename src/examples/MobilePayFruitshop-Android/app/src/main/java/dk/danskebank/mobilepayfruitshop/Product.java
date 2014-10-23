package dk.danskebank.mobilepayfruitshop;

import java.math.BigDecimal;

class Product {
    private final int icon;
    private final String name;
    private final BigDecimal price;

    public Product(String name, BigDecimal price, int icon) {
        this.icon = icon;
        this.name = name;
        this.price = price;
    }

    public int getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
