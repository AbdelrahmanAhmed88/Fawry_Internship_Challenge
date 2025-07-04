import java.util.*;
import java.time.LocalDate;

public class App {

    public static void checkout(Customer customer, Cart cart)
    {
        if(cart.isEmpty())
        {
            throw new IllegalArgumentException("Cart is empty.");
        }

        //check if customer has enough money
        double Totalprice = cart.getTotalPrice();
        if(!customer.haveEnoughMoney(Totalprice))
        {
            throw new IllegalArgumentException("Customer don't has enough money");
        }
        
        //check for out of stock product
        for(CartItem item :cart.getItems())
        {
            if(item.quantity > item.product.getQuantity())
            {
                throw new IllegalArgumentException(item.product.getName() + " stock is not enough");
            }
        }

        //Check for expired product
        for(CartItem item :cart.getItems())
        {
            if(item.product instanceof ExpirableProduct)
            {
                ExpirableProduct expirableItem = (ExpirableProduct) item.product;
                if(expirableItem.isExpired()){
                    throw new IllegalArgumentException(expirableItem.getName() + " is expired");
                }
            }
        }

        //check for shipable items
        System.out.println("** Shipment notice ** ");
        double TotalWeight = 0.0;
        for(CartItem item : cart.getItems())
        {
            if(item.product instanceof Shippable){
                Shippable shippableItem = (Shippable) item.product;
                System.out.printf("%dx %-12s %4.0fg%n", item.quantity, shippableItem.getName(), shippableItem.getWeight()*item.quantity);
                TotalWeight = TotalWeight + shippableItem.getWeight()*item.quantity;
            }
        }

        //print total weight
        System.out.printf("Total package weight %.1fkg %n%n",TotalWeight/1000);

        //Checkout receipt part
        

        System.out.println("** Checkout receipt ** ");
        double SubTotal = 0.0;
        for(CartItem item : cart.getItems())
        {
            System.out.printf("%dx %-12s %4.0f %n", item.quantity, item.product.getName(), item.product.getPrice()*item.quantity);
            SubTotal = SubTotal + item.product.getPrice()*item.quantity;
        }
        System.out.println("----------------------");

        // Shipping cost
        //Assume for each kilo shipping cost 30
        //the value is ceiled so 1.2kg is considered 2kg 
        double shippingCost = Math.ceil(TotalWeight / 1000.0) * 30.0;
        double totalAmount = SubTotal + shippingCost;

        System.out.printf("Subtotal %11.0f %n",SubTotal);
        System.out.printf("Shipping %11.0f %n",shippingCost);
        System.out.printf("Amount   %11.0f %n",totalAmount);
        
        customer.subtractPrice(totalAmount);
        System.out.println("----------------------");
        System.out.printf("%s new Balance : %11.0f %n",customer.getName(),customer.getBalance());

        //remove items 
        for(CartItem item :cart.getItems()){
            item.product.reduceQuantity(item.quantity);
        }
    }
    public static void main(String[] args) throws Exception {
        ExpiringShippableProduct cheese = new ExpiringShippableProduct("Cheese", 100.0, 3, LocalDate.of(2025, 7, 10), 200);
        ExpiringShippableProduct biscuits = new ExpiringShippableProduct("Biscuits", 150.0, 3, LocalDate.of(2025, 7, 10), 700);
        ShippableProduct tv = new ShippableProduct("tv", 10000, 5, 5000);
        nonExpirableProduct scratchCard = new nonExpirableProduct("Scratch Card", 100, 10);
        Customer customer = new Customer(0, "Abdelrahman", 20000);

        Cart cart = new Cart();
        cart.add(cheese, 2);
        cart.add(biscuits, 2);
        cart.add(scratchCard, 1);
        try {
            checkout(customer, cart);
        } catch (Exception e) {
            System.out.println("----------------------------------------------");
            System.out.println("Checkout failed: " + e.getMessage());
            System.out.println("----------------------------------------------");
        }
    }
}
