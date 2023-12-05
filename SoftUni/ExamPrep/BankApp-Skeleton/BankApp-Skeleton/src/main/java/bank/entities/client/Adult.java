package bank.entities.client;

public class Adult extends BaseClient{

    private static int interest = 4;
    public Adult(String name, String ID, double income) {
        super(name, ID, interest, income);

    }

    @Override
    public void increase() {
        interest += 2;
    }
}

