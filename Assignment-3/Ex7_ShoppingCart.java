import java.util.ArrayList;
import java.util.List;

class ApplicationException extends Exception {
    public ApplicationException(String message) {
        super(message);
    }
}

class ProductException extends ApplicationException {
    public ProductException(String message) {
        super(message);
    }
}

class ProductNotFoundException extends ProductException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}

class OutOfStockException extends ProductException {
    public OutOfStockException(String message) {
        super(message);
    }
}

class PaymentException extends ApplicationException {
    public PaymentException(String message) {
        super(message);
    }
}

class InvalidPaymentException extends PaymentException {
    public InvalidPaymentException(String message) {
        super(message);
    }
}

class InsufficientFundsException extends PaymentException {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

class OrderException extends ApplicationException {
    public OrderException(String message) {
        super(message);
    }
}

class EmptyCartException extends OrderException {
    public EmptyCartException(String message) {
        super(message);
    }
}

class Product {
    private String name;
    private double price;
    private int stock;

    public Product(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void reduceStock(int quantity) {
        stock -= quantity;
    }
}

class Cart {
    private Product[] catalog;
    private List<Product> items = new ArrayList<>();

    public Cart(Product[] catalog) {
        this.catalog = catalog;
    }

    public Product findProduct(String name) throws ProductNotFoundException {
        for (Product p : catalog) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        throw new ProductNotFoundException("Product not found: " + name);
    }

    public void addToCart(String name, int quantity) throws ProductNotFoundException, OutOfStockException {
        Product p = findProduct(name);
        if (p.getStock() < quantity) {
            throw new OutOfStockException("Not enough stock for: " + name);
        }
        for (int i = 0; i < quantity; i++) {
            items.add(p);
        }
    }

    public void removeFromCart(String name) throws ProductNotFoundException {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equalsIgnoreCase(name)) {
                items.remove(i);
                return;
            }
        }
        throw new ProductNotFoundException("Product not in cart: " + name);
    }

    public double getTotal() {
        double total = 0;
        for (Product p : items) {
            total += p.getPrice();
        }
        return total;
    }

    public void checkout(double paymentAmount)
            throws EmptyCartException, InvalidPaymentException, InsufficientFundsException {
        if (items.isEmpty()) {
            throw new EmptyCartException("Cart is empty, cannot checkout");
        }
        if (paymentAmount <= 0) {
            throw new InvalidPaymentException("Payment amount must be positive");
        }
        double total = getTotal();
        if (paymentAmount < total) {
            throw new InsufficientFundsException("Insufficient funds: total is " + total);
        }
        for (Product p : items) {
            p.reduceStock(1);
        }
        System.out.println("Order placed successfully. Total: " + total + ", Change: " + (paymentAmount - total));
        items.clear();
    }
}

public class Ex7_ShoppingCart {
    public static void main(String[] args) {
        Product[] catalog = {
            new Product("Laptop", 50000, 2),
            new Product("Mouse", 500, 0),
            new Product("Keyboard", 1500, 5)
        };
        Cart cart = new Cart(catalog);

        try {
            cart.addToCart("Laptop", 1);
            cart.addToCart("Keyboard", 1);
        } catch (ProductNotFoundException | OutOfStockException e) {
            System.out.println(e.getMessage());
        }

        try {
            cart.addToCart("Mouse", 1);
        } catch (ProductNotFoundException | OutOfStockException e) {
            System.out.println(e.getMessage());
        }

        try {
            cart.addToCart("Monitor", 1);
        } catch (ProductNotFoundException | OutOfStockException e) {
            System.out.println(e.getMessage());
        }

        try {
            cart.checkout(40000);
        } catch (EmptyCartException | InvalidPaymentException | InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }

        try {
            cart.checkout(60000);
        } catch (EmptyCartException | InvalidPaymentException | InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }

        try {
            cart.checkout(10000);
        } catch (EmptyCartException | InvalidPaymentException | InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }
    }
}
