package christmasPastryShop.entities.delicacies.interfaces;

import static christmasPastryShop.common.ExceptionMessages.*;

public class BaseDelicacy implements Delicacy{

    private String name;
    private double portion;
    private double price;

    protected BaseDelicacy(String name, double portion, double price) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(INVALID_NAME);
        }
        this.name = name;

        if (portion <= 0) {
            throw new IllegalArgumentException(INVALID_PORTION);
        }
        this.portion = portion;

        if (price <= 0) {
            throw new IllegalArgumentException(INVALID_PRICE);
        }
        this.price = price;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getPortion() {
        return this.portion;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public String toString() {
        return String.format("%s: %.2fg - %.2f", getName(), getPortion(), getPrice());
    }
}
