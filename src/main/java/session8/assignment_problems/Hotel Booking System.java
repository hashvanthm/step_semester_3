
import java.util.*;
import java.time.*;

abstract class Room {
    int number;
    boolean booked = false;

    Room(int number) {
        this.number = number;
    }

    abstract int getPrice();
}

class StandardRoom extends Room {
    StandardRoom(int n) {
        super(n);
    }

    int getPrice() {
        return 1000;
    }
}

class DeluxeRoom extends Room {
    DeluxeRoom(int n) {
        super(n);
    }

    int getPrice() {
        return 2000;
    }
}

class Reservation {
    String customer;
    Room room;
    LocalDate start, end, deadline;
    boolean active = true;

    Reservation(String c, Room r, LocalDate s, LocalDate e) {
        customer = c;
        room = r;
        start = s;
        end = e;
        deadline = s.minusDays(1);
    }
}

class Hotel {
    ArrayList<Reservation> list = new ArrayList<>();

    boolean available(Room room, LocalDate s, LocalDate e) {
        for (Reservation r : list) {
            if (r.room == room && r.active &&
                s.isBefore(r.end) && e.isAfter(r.start)) {
                return false;
            }
        }
        return true;
    }

    void book(String name, Room room, LocalDate s, LocalDate e) {
        if (available(room, s, e)) {
            Reservation r = new Reservation(name, room, s, e);
            list.add(r);

            long days = java.time.temporal.ChronoUnit.DAYS
                    .between(s, e);

            System.out.println("Booking confirmed for " + name);
            System.out.println("Price: Rs." + days * room.getPrice());
        } else {
            System.out.println("Room " + room.number + " is not available.");
        }
    }

    void cancel(Reservation r, LocalDate today) {
        if (r.active && today.isBefore(r.deadline)) {
            r.active = false;
            System.out.println("Booking cancelled successfully.");
        } else {
            System.out.println("Cancellation deadline passed.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Hotel h = new Hotel();

        Room r1 = new StandardRoom(101);
        Room r2 = new DeluxeRoom(201);

        LocalDate s = LocalDate.of(2026, 1, 1);
        LocalDate e = LocalDate.of(2026, 1, 5);

        h.book("Customer A", r1, s, e);
        h.book("Customer B", r1,
                LocalDate.of(2026, 1, 3),
                LocalDate.of(2026, 1, 7));

        h.book("Customer C", r2,
                LocalDate.of(2026, 2, 10),
                LocalDate.of(2026, 2, 12));
    }
}
