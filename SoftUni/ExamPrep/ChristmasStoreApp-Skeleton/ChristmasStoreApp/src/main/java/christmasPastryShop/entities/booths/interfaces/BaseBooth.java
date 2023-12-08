package christmasPastryShop.entities.booths.interfaces;

import christmasPastryShop.entities.cocktails.interfaces.Cocktail;
import christmasPastryShop.entities.delicacies.interfaces.Delicacy;

import java.util.ArrayList;
import java.util.Collection;

import static christmasPastryShop.common.ExceptionMessages.*;

public class BaseBooth implements Booth {

    private Collection<Delicacy> delicacyOrders;
    private Collection<Cocktail> cocktailOrders;
    private int boothNumber;
    private int capacity;
    private int numberOfPeople;
    private double pricePerPerson;
    private boolean isReserved;
    private double price;

    protected BaseBooth(int boothNumber, int capacity, double pricePerPerson) {
        this.boothNumber = boothNumber;

        if (capacity <= 0) {
            throw new IllegalArgumentException(INVALID_TABLE_CAPACITY);
        }
        this.capacity = capacity;
        numberOfPeople = 0;
        this.price = 0;
        this.pricePerPerson = pricePerPerson;
        this.isReserved = false;
        this.delicacyOrders = new ArrayList<>();
        this.cocktailOrders = new ArrayList<>();
    }


    @Override
    public int getBoothNumber() {
        return this.boothNumber;
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public boolean isReserved() {
        return this.isReserved;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public void reserve(int numberOfPeople) {
        if (numberOfPeople <= 0) {
            throw new IllegalArgumentException(INVALID_NUMBER_OF_PEOPLE);
        }

        isReserved = true;
        this.numberOfPeople = numberOfPeople;

        this.price = numberOfPeople * pricePerPerson;

    }

    @Override
    public double getBill() {
        double bill = getPrice();
        for (Delicacy delicacy :
                delicacyOrders) {
            bill += delicacy.getPrice();
        }

        for (Cocktail cocktail :
                cocktailOrders) {
            bill += cocktail.getPrice();
        }

        return bill;
    }

    @Override
    public void clear() {
        this.cocktailOrders = new ArrayList<>();
        this.delicacyOrders = new ArrayList<>();
        this.price = 0;
        this.numberOfPeople = 0;
        isReserved = false;
    }
}
