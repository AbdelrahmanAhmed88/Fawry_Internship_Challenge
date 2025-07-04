import java.time.LocalDate;

public class ExpiringShippableProduct extends ExpirableProduct implements Shippable {
    private double weight;

    public ExpiringShippableProduct(String name, double price, int quantity,LocalDate expireDate,double weight)
    {
        super(name, price, quantity, expireDate);
        this.weight = weight;
    }

    @Override
    public double getWeight(){
        return this.weight;
    }

    @Override
    public String getName()
    {
        return name;
    }
}
