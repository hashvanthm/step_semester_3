
import java.util.*;

class Product {
    String name;
    int price, quantity;

    Product(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    int getTotal() {
        return price * quantity;
    }
}

interface PaymentMethod {
    boolean processPayment();
}

class CreditCard implements PaymentMethod {
    public boolean processPayment() {
        System.out.println("Credit Card payment successful.");
        return true;
    }
}

class PayPal implements PaymentMethod {
    public boolean processPayment() {
        System.out.println("PayPal payment failed.");
        return false;
    }
}

class BankTransfer implements PaymentMethod {
    public boolean processPayment() {
        System.out.println("Bank Transfer successful.");
        return true;
    }
}

class Order {
    String customer;
    ArrayList<Product> products = new ArrayList<>();
    String status = "Pending";

    Order(String customer) {
        this.customer = customer;
        System.out.println("Order created for " + customer);
    }

    void addProduct(Product p) {
        products.add(p);
    }

    void pay(PaymentMethod method) {
        if (products.isEmpty()) {
            System.out.println("Cannot pay for empty order.");
            return;
        }

        System.out.println("Payment initiated for " + customer);

        if (method.processPayment())
            status = "Paid";

        System.out.println("Order status: " + status);
    }

    void displayTotal() {
        int total = 0;

        for (Product p : products)
            total += p.getTotal();

        System.out.println("Total amount: Rs." + total);
    }
}

public class Main {
    public static void main(String[] args) {
        Order o1 = new Order("Customer X");

        o1.addProduct(new Product("Product A", 100, 2));
        o1.addProduct(new Product("Product B", 200, 1));

        o1.displayTotal();
        o1.pay(new CreditCard());

        Order o2 = new Order("Customer Y");
        o2.pay(new CreditCard());

        Order o3 = new Order("Customer Z");
        o3.addProduct(new Product("Product C", 300, 1));
        o3.pay(new PayPal());
    }
}
