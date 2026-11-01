import java.util.*;

interface Chargeable {
    boolean canCharge();
}

abstract class Vehicle {
    String passNumber;
    String owner;

    Vehicle(String passNumber, String owner) {
        this.passNumber = passNumber;
        this.owner = owner;
    }

    abstract int passFee();
    abstract String type();
}

class Bike extends Vehicle {
    Bike(String p, String o) { super(p, o); }
    int passFee() { return 300; }
    String type() { return "Bike"; }
}

class Car extends Vehicle {
    Car(String p, String o) { super(p, o); }
    int passFee() { return 1000; }
    String type() { return "Car"; }
}

class EBike extends Vehicle implements Chargeable {
    EBike(String p, String o) { super(p, o); }
    int passFee() { return 300; }
    String type() { return "EBike"; }
    public boolean canCharge() { return true; }
}

class ECar extends Vehicle implements Chargeable {
    ECar(String p, String o) { super(p, o); }
    int passFee() { return 1000; }
    String type() { return "ECar"; }
    public boolean canCharge() { return true; }
}

public class CampusVehiclePass {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Vehicle> vehicles = new HashMap<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;
            String[] p = line.split("\\s+");
            if (p[0].equals("PASS")) {
                Vehicle v;
                switch (p[1]) {
                    case "Bike": v = new Bike(p[2], p[3]); break;
                    case "Car": v = new Car(p[2], p[3]); break;
                    case "EBike": v = new EBike(p[2], p[3]); break;
                    default: v = new ECar(p[2], p[3]); break;
                }
                vehicles.put(v.passNumber, v);
                System.out.println(v.passNumber + " (" + v.type() + ") pass fee " + v.passFee());
            } else if (p[0].equals("CHARGE")) {
                Vehicle v = vehicles.get(p[1]);
                if (v == null) {
                    System.out.println(p[1] + " not found");
                } else if (v instanceof Chargeable) {
                    System.out.println(v.passNumber + " charging bay allotted");
                } else {
                    System.out.println(v.passNumber + " rejected: charging unsupported");
                }
            }
        }
    }
}
