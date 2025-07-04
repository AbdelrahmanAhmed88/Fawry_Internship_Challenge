public abstract class Product {
    protected String name;
    protected double price;
    protected int quantity;
    
    public Product(String name, double price, int quantity)
    {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    public String getName()
    {
        return this.name;
    }   

    public void setPrice(double price)
    {
        this.price = price;
    }
    public double getPrice()
    {
        return this.price;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
    public void reduceQuantity(int num)
    {
        this.quantity -= num;
    }
    public int getQuantity()
    {
        return this.quantity;
    }
}

