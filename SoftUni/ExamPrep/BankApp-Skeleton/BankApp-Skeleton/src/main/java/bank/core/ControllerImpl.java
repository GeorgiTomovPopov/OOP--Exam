package bank.core;

import bank.entities.bank.Bank;
import bank.entities.bank.BranchBank;
import bank.entities.bank.CentralBank;
import bank.entities.client.Adult;
import bank.entities.client.Client;
import bank.entities.client.Student;
import bank.entities.loan.Loan;
import bank.entities.loan.MortgageLoan;
import bank.entities.loan.StudentLoan;
import bank.repositories.LoanRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static bank.common.ConstantMessages.*;
import static bank.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private LoanRepository loans = new LoanRepository();
    private Collection<Bank> banks = new ArrayList<>();

    @Override
    public String addBank(String type, String name) {


        switch (type) {
            case "CentralBank":
                CentralBank centralBank = new CentralBank(name);
                banks.add(centralBank);
                return String.format(SUCCESSFULLY_ADDED_BANK_OR_LOAN_TYPE, type);
            case "BranchBank":
                BranchBank branchBank = new BranchBank(name);
                banks.add(branchBank);
                return String.format(SUCCESSFULLY_ADDED_BANK_OR_LOAN_TYPE, type);
            default:
                throw new IllegalArgumentException(INVALID_BANK_TYPE);
        }
    }

    @Override
    public String addLoan(String type) {

        switch (type) {
            case "StudentLoan":
                StudentLoan studentLoan = new StudentLoan();
                loans.addLoan(studentLoan);
                return String.format(SUCCESSFULLY_ADDED_BANK_OR_LOAN_TYPE, type);
            case "MortgageLoan":
                MortgageLoan mortgageLoan = new MortgageLoan();
                loans.addLoan(mortgageLoan);
                return String.format(SUCCESSFULLY_ADDED_BANK_OR_LOAN_TYPE, type);
            default:
                throw new IllegalArgumentException(INVALID_LOAN_TYPE);
        }
    }

    @Override
    public String returnedLoan(String bankName, String loanType) {
        for (Bank bank : banks) {
            if (bank.getName().equals(bankName)) {
                Loan currentLoan = loans.findFirst(loanType);
                if (currentLoan == null) {
                    throw new IllegalArgumentException(String.format(NO_LOAN_FOUND, loanType));
                }
                bank.addLoan(currentLoan);
                loans.removeLoan(currentLoan);
                return String.format(SUCCESSFULLY_ADDED_CLIENT_OR_LOAN_TO_BANK, loanType, bank.getName());
            }
        }

        return null;
    }

    @Override
    public String addClient(String bankName, String clientType, String clientName, String clientID, double income) {
        if (!clientType.equals("Student") && !clientType.equals("Adult")) {
            throw new IllegalArgumentException(INVALID_CLIENT_TYPE);
        }

        Optional<Bank> bank = banks.stream().filter(b -> b.getName().equals(bankName)).findFirst();

        if (bank.isPresent()) {
            if (clientType.equals("Student") && bank.get().getClass().getSimpleName().equals("BranchBank")) {
                banks.remove(banks.stream().filter(b -> b.getName().equals(bankName)).findFirst().get());
                bank.get().addClient(new Student(clientName, clientID, income));
                banks.add(bank.get());

                return String.format(SUCCESSFULLY_ADDED_CLIENT_OR_LOAN_TO_BANK, clientType, bankName);

            } else if (clientType.equals("Adult") && bank.get().getClass().getSimpleName().equals("CentralBank")) {
                banks.remove(banks.stream().filter(b -> b.getName().equals(bankName)).findFirst().get());
                bank.get().addClient(new Adult(clientName, clientID, income));
                banks.add(bank.get());

                return String.format(SUCCESSFULLY_ADDED_CLIENT_OR_LOAN_TO_BANK, clientType, bankName);

            } else {
                return UNSUITABLE_BANK;
            }
        }

        return bankName;
    }

    @Override
    public String finalCalculation(String bankName) {

        Bank bank = null;

        for (Bank b : banks) {
            if (b.getName().equals(bankName)) {
                bank = b;
            }
        }
        double totalFunds = 0.00;

        Collection<Client> clients = bank.getClients();
        for (Client client : clients) {
            totalFunds += client.getIncome() * client.getInterest() / 100;
        }

        Collection<Loan> loanCollection = bank.getLoans();
        for (Loan loan : loanCollection) {
            totalFunds += loan.getAmount() * loan.getInterestRate() / 100;
        }


        return String.format(FUNDS_BANK, bankName, totalFunds);
    }

    @Override
    public String getStatistics() {
        for (Bank bank : banks) {
            return bank.getStatistics();
        }


        return null;
    }
}
