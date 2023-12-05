package bank.entities.client;

public class Student extends BaseClient{

    private static  int interest = 2;
    public Student(String name, String ID, double income) {
        super(name, ID, interest, income);
    }

    @Override
    public void increase() {
        interest += 1;
    }
}
