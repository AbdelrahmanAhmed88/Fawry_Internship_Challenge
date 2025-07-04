import java.time.LocalDate;

public class ExpirableProduct extends Product {
    private LocalDate expireDate;

    public ExpirableProduct(String name, double price, int quantity,LocalDate expireDate){
        super(name, price, quantity);
        this.expireDate = expireDate;
    }
    public void setExpireDate(LocalDate expireDate)
    {
        this.expireDate = expireDate;
    }
    public LocalDate getExpireDate()
    {
        return this.expireDate;
    } 
    public boolean isExpired(){
        return LocalDate.now().isAfter(this.expireDate);
    }
}
