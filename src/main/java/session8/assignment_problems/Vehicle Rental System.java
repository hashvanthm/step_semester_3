
abstract class Vehicle {
    String name;
    boolean available = true;

    Vehicle(String name) {
        this.name = name;
    }

    abstract int getRate();
}

class Sedan extends Vehicle {
    Sedan(String name) {
        super(name);
    }

    int getRate() {
        return 100;
    }
}

class SUV extends Vehicle {
    SUV(String name) {
        super(name);
    }

    int getRate() {
        return 200;
    }
}

class Rental {
    void rent(Vehicle v, String customer, int days) {
        if (v.available) {
            v.available = false;
            System.out.println(v.name + " rented by " + customer);
            System.out.println("Charge: Rs." + v.getRate() * days);
        } else {
            System.out.println(v.name + " is unavailable.");
        }
    }

    void returnVehicle(Vehicle v, String customer) {
        v.available = true;
        System.out.println(v.name + " returned by " + customer);
    }
}

public class Main {
    public static void main(String[] args) {
        Vehicle v1 = new Sedan("Sedan A");
        Vehicle v2 = new SUV("SUV B");

        Rental r = new Rental();

        r.rent(v1, "Customer 1", 3);
        r.rent(v1, "Customer 2", 2);
        r.returnVehicle(v1, "Customer 1");
        r.rent(v2, "Customer 3", 5);
    }
}
