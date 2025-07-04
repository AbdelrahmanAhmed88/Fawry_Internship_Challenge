public class Customer {
    private int id;
    private String name;
    private double balance;

    public Customer(int id,String name,double balance)
    {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public String getName()
    {
        return this.name;
    }

    public double getBalance()
    {
        return this.balance;
    }
    public boolean haveEnoughMoney(double amount)
    {
        return amount <= this.balance;
    }
    public void subtractPrice(double amount)
    {
        this.balance = balance - amount;
    }
}
