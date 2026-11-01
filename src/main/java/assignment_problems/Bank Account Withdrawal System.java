import java.util.*;

abstract class Account {
    String id;
    long balance;

    Account(String id, long balance) {
        this.id = id;
        this.balance = balance;
    }

    abstract boolean withdraw(long amount);
    abstract String rejectMessage();
}

class Savings extends Account {
    Savings(String id, long balance) {
        super(id, balance);
    }

    boolean withdraw(long amount) {
        if (balance - amount < 1000) {
            return false;
        }
        balance -= amount;
        return true;
    }

    String rejectMessage() {
        return id + " rejected: minimum balance 1000";
    }
}

class Current extends Account {
    Current(String id, long balance) {
        super(id, balance);
    }

    boolean withdraw(long amount) {
        if (balance - amount < -5000) {
            return false;
        }
        balance -= amount;
        return true;
    }

    String rejectMessage() {
        return id + " rejected: overdraft limit 5000";
    }
}

public class BankSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Account> accounts = new HashMap<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;
            String[] p = line.split("\\s+");
            if (p[0].equals("Savings")) {
                accounts.put(p[1], new Savings(p[1], Long.parseLong(p[2])));
            } else if (p[0].equals("Current")) {
                accounts.put(p[1], new Current(p[1], Long.parseLong(p[2])));
            } else if (p[0].equals("WITHDRAW")) {
                Account a = accounts.get(p[1]);
                if (a == null) {
                    System.out.println("Account not found");
                } else if (a.withdraw(Long.parseLong(p[2]))) {
                    System.out.println(a.id + " balance " + a.balance);
                } else {
                    System.out.println(a.rejectMessage());
                }
            }
        }
    }
}
