import java.util.*;

abstract class Staff {
    String name;

    Staff(String name) {
        this.name = name;
    }

    abstract long pay();
    abstract String type();

    public String toString() {
        return "Payslip[name=" + name + ", type=" + type() + ", pay=" + pay() + "]";
    }
}

class FullTime extends Staff {
    long salary;

    FullTime(String name, long salary) {
        super(name);
        this.salary = salary;
    }

    long pay() { return salary; }
    String type() { return "FullTime"; }
}

class PartTime extends Staff {
    long hours;
    long rate;

    PartTime(String name, long hours, long rate) {
        super(name);
        this.hours = hours;
        this.rate = rate;
    }

    long pay() { return hours * rate; }
    String type() { return "PartTime"; }
}

class Intern extends Staff {
    long stipend;

    Intern(String name, long stipend) {
        super(name);
        this.stipend = stipend;
    }

    long pay() { return stipend; }
    String type() { return "Intern"; }
}

public class PayrollRegister {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Staff> list = new ArrayList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;
            String[] p = line.split("\\s+");
            if (p[0].equals("FullTime")) {
                list.add(new FullTime(p[1], Long.parseLong(p[2])));
            } else if (p[0].equals("PartTime")) {
                list.add(new PartTime(p[1], Long.parseLong(p[2]), Long.parseLong(p[3])));
            } else if (p[0].equals("Intern")) {
                list.add(new Intern(p[1], Long.parseLong(p[2])));
            }
        }

        Staff[] staff = list.toArray(new Staff[0]);
        long total = 0;
        Staff top = staff[0];
        for (Staff s : staff) {
            System.out.println(s);
            total += s.pay();
            if (s.pay() > top.pay()) {
                top = s;
            }
        }
        System.out.println("Total " + total);
        System.out.println("top earner " + top.name);
    }
}
