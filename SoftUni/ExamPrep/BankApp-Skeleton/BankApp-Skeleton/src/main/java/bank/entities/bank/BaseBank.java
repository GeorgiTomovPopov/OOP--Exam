package bank.entities.bank;

import bank.entities.client.Client;
import bank.entities.loan.Loan;

import java.util.ArrayList;
import java.util.Collection;

import static bank.common.ExceptionMessages.BANK_NAME_CANNOT_BE_NULL_OR_EMPTY;
import static bank.common.ExceptionMessages.NOT_ENOUGH_CAPACITY_FOR_CLIENT;

public class BaseBank implements Bank{

    private String name;
    private int capacity;
    private Collection<Loan> loans;
    private Collection<Client> clients;


    public BaseBank(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.loans = new ArrayList<>();
        this.clients = new ArrayList<>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(BANK_NAME_CANNOT_BE_NULL_OR_EMPTY);
        }

        this.name = name;
    }

    @Override
    public Collection<Client> getClients() {
        return this.clients;
    }

    @Override
    public Collection<Loan> getLoans() {
        return this.loans;
    }

    @Override
    public void addClient(Client client) {
        if (clients.size() >= capacity) {
            throw new IllegalStateException(NOT_ENOUGH_CAPACITY_FOR_CLIENT);
        }
        clients.add(client);
    }

    @Override
    public void removeClient(Client client) {
        clients.remove(client);
    }

    @Override
    public void addLoan(Loan loan) {
        loans.add(loan);
    }

    @Override
    public int sumOfInterestRates() {
        int totalInterest = 0;
        for (Loan loan : loans) {
            totalInterest += loan.getInterestRate();
        }

        return totalInterest;
    }

    @Override
    public String getStatistics() {
        //"Name: {bankName},
        // Type: {bankType}
        // Clients: {clientName1}, {clientName2} ... /
        // Clients: none
        // Loans: {loansCount},
        // Sum of interest rates: {sumOfInterestRates}"

        StringBuilder sb = new StringBuilder();


        sb.append("Name: ")
                .append(this.name).append(", ")
                .append("Type: ")
                .append(Bank.class.getSimpleName())
                .append(System.lineSeparator())
                .append("Clients: ");

        String clientsListToString = getClients().stream().toString().join(", ");
        sb.append(clientsListToString);
        sb.append(System.lineSeparator());
        sb.append("Loans: ");
        sb.append(getLoans().size());
        sb.append(", ");
        sb.append("Sum of interest rates: ");
        sb.append(sumOfInterestRates());
        sb.append(System.lineSeparator());


        return sb.toString();
    }
}
