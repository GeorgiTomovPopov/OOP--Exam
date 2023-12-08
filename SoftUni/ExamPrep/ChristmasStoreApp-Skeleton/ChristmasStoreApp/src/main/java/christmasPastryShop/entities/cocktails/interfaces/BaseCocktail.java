package christmasPastryShop.entities.cocktails.interfaces;

import static christmasPastryShop.common.ExceptionMessages.*;

public class BaseCocktail implements Cocktail {
    private String name;
    private int size;
    private double price;
    private String brand;

    protected BaseCocktail(String name, int size, double price, String brand) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(INVALID_NAME);
        }
        this.name = name;

        if (size <= 0) {
            throw new IllegalArgumentException(INVALID_SIZE);
        }
        this.size = size;

        if (price <= 0) {
            throw new IllegalArgumentException(INVALID_PRICE);
        }
        this.price = price;

        if (brand == null || brand.trim().isEmpty()) {
            throw new IllegalArgumentException(INVALID_BRAND);
        }
        this.brand = brand;
    }


    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public String getBrand() {
        return this.brand;
    }

    @Override
    public String toString() {
        return String.format("%s %s - %dml - %.2flv", getName(), getBrand(), getSize(), getPrice());
    }
}
