import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items = new ArrayList<>();

    public void add(Product product,int quantity) 
    {
        // if(quantity > product.getQuantity())
        // {
        //     throw new IllegalArgumentException(product.getName()+" don't have enough stock");
        // }
        items.add(new CartItem(product, quantity));
    }

    public List<CartItem> getItems()
    {
        return items;
    }

    public boolean isEmpty()
    {
        return items.isEmpty();
    }

    public double getTotalPrice(){
        double total = 0;
        for(CartItem item:items)
        {
            total = total + item.getPrice();
        }
        return total;
    }
}
