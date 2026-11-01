
import java.util.*;

abstract class Seat {
    String id;

    Seat(String id) {
        this.id = id;
    }

    abstract int getPrice();
}

class Regular extends Seat {
    Regular(String id) {
        super(id);
    }

    int getPrice() {
        return 150;
    }
}

class Premium extends Seat {
    Premium(String id) {
        super(id);
    }

    int getPrice() {
        return 250;
    }
}

class Recliner extends Seat {
    Recliner(String id) {
        super(id);
    }

    int getPrice() {
        return 400;
    }
}

class Show {
    HashSet<String> booked = new HashSet<>();

    boolean available(String id) {
        return !booked.contains(id);
    }

    void book(Booking b) {
        if (b.seats.size() > 6) {
            System.out.println("Maximum 6 seats allowed.");
            return;
        }

        for (Seat s : b.seats) {
            if (!available(s.id)) {
                System.out.println("Seat " + s.id + " is already booked.");
                return;
            }
        }

        int total = 0;

        for (Seat s : b.seats) {
            booked.add(s.id);
            total += s.getPrice();
        }

        b.total = total;
        System.out.println("Booking confirmed for " + b.customer);
        System.out.println("Total: Rs." + total);
    }

    void cancel(Booking b, boolean showStarted) {
        if (showStarted) {
            System.out.println("Cannot cancel after show starts.");
            return;
        }

        for (Seat s : b.seats) {
            booked.remove(s.id);
        }

        System.out.println(b.customer + "'s booking cancelled.");
    }
}

class Booking {
    String customer;
    ArrayList<Seat> seats = new ArrayList<>();
    int total;

    Booking(String customer) {
        this.customer = customer;
    }

    void addSeat(Seat s) {
        seats.add(s);
    }
}

public class Main {
    public static void main(String[] args) {
        Show show = new Show();

        Booking b1 = new Booking("Asha");
        b1.addSeat(new Regular("A1"));
        b1.addSeat(new Regular("A2"));
        b1.addSeat(new Premium("F5"));

        show.book(b1);

        Booking b2 = new Booking("Ravi");
        b2.addSeat(new Regular("A2"));
        show.book(b2);

        Booking b3 = new Booking("Ravi");
        b3.addSeat(new Recliner("R1"));
        show.book(b3);

        show.cancel(b1, false);

        Booking b4 = new Booking("Neha");
        b4.addSeat(new Regular("A2"));
        show.book(b4);
    }
}
