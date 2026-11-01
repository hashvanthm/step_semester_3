
abstract class WashType {
    abstract int getTime();
    abstract int getCharge();
}

class Quick extends WashType {
    int getTime() { return 30; }
    int getCharge() { return 20; }
}

class Normal extends WashType {
    int getTime() { return 45; }
    int getCharge() { return 30; }
}

class Heavy extends WashType {
    int getTime() { return 60; }
    int getCharge() { return 45; }
}

class WashingMachine {
    String name;
    boolean busy = false;

    WashingMachine(String name) {
        this.name = name;
    }

    void start(String student, WashType wash) {
        if (busy) {
            System.out.println(name + " is currently busy.");
        } else {
            busy = true;
            System.out.println(wash.getClass().getSimpleName()
                    + " wash started on " + name + " for " + student);
            System.out.println("Time: " + wash.getTime() + " min");
            System.out.println("Charge: Rs." + wash.getCharge());
        }
    }

    void complete() {
        busy = false;
        System.out.println(name + " cycle completed. Machine is free.");
    }
}

public class Main {
    public static void main(String[] args) {
        WashingMachine m1 = new WashingMachine("M1");
        WashingMachine m2 = new WashingMachine("M2");

        m1.start("Asha", new Quick());
        m1.start("Ravi", new Heavy());
        m2.start("Ravi", new Heavy());
        m1.complete();
        m1.start("Neha", new Normal());
    }
}
