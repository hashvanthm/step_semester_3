
abstract class Employee {
    String name;

    Employee(String name) {
        this.name = name;
    }

    abstract boolean canTakeLeave(int days);
}

class FullTimeEmployee extends Employee {
    FullTimeEmployee(String name) {
        super(name);
    }

    boolean canTakeLeave(int days) {
        return days <= 20;
    }
}

class PartTimeEmployee extends Employee {
    PartTimeEmployee(String name) {
        super(name);
    }

    boolean canTakeLeave(int days) {
        return days <= 10;
    }
}

class LeaveRequest {
    Employee employee;
    String dates;
    private String status = "Pending";

    LeaveRequest(Employee e, String dates, int days) {
        employee = e;
        this.dates = dates;

        if (e.canTakeLeave(days))
            System.out.println("Leave request submitted for " + e.name);
        else {
            status = "Rejected";
            System.out.println("Leave limit exceeded.");
        }
    }

    void review(boolean approve) {
        if (!status.equals("Pending")) {
            System.out.println("Request already reviewed.");
            return;
        }

        if (approve)
            status = "Approved";
        else
            status = "Rejected";

        System.out.println(employee.name + "'s leave " + status);
    }

    void changeStatus(String newStatus) {
        if (!status.equals("Pending"))
            System.out.println("Cannot change status from " + status);
        else
            status = newStatus;
    }
}

public class Main {
    public static void main(String[] args) {
        Employee e1 = new FullTimeEmployee("John");
        Employee e2 = new PartTimeEmployee("Jane");

        LeaveRequest l1 = new LeaveRequest(e1, "Jan 1-5", 5);
        l1.review(true);

        LeaveRequest l2 = new LeaveRequest(e2, "Feb 10-11", 2);
        l2.review(false);

        l1.changeStatus("Pending");
    }
}
