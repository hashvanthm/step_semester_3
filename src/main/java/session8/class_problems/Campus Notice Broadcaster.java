
import java.util.*;

interface NotificationChannel {
    void send(String student, String notice);
}

class Email implements NotificationChannel {
    public void send(String student, String notice) {
        System.out.println("[Email -> " + student + "] " + notice);
    }
}

class SMS implements NotificationChannel {
    public void send(String student, String notice) {
        System.out.println("[SMS -> " + student + "] " + notice);
    }
}

class App implements NotificationChannel {
    public void send(String student, String notice) {
        System.out.println("[App -> " + student + "] " + notice);
    }
}

class Student {
    String name, department;
    ArrayList<NotificationChannel> channels = new ArrayList<>();

    Student(String name, String department) {
        this.name = name;
        this.department = department;
    }

    void addChannel(NotificationChannel c) {
        channels.add(c);
    }
}

class Notice {
    String title;
    ArrayList<String> departments;

    Notice(String title, ArrayList<String> departments) {
        this.title = title;
        this.departments = departments;
    }
}

class NoticeBoard {
    ArrayList<Student> students = new ArrayList<>();

    void addStudent(Student s) {
        students.add(s);
    }

    void post(Notice n) {
        if (n.title == null || n.title.trim().isEmpty()) {
            System.out.println("Cannot post: Title required.");
            return;
        }

        if (n.departments == null || n.departments.isEmpty()) {
            System.out.println("Cannot post: Department required.");
            return;
        }

        System.out.println("Notice posted: " + n.title);

        for (Student s : students) {
            if (n.departments.contains(s.department)) {
                for (NotificationChannel c : s.channels) {
                    c.send(s.name, n.title);
                }
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        NoticeBoard board = new NoticeBoard();

        Student s1 = new Student("Asha", "CSE");
        s1.addChannel(new Email());
        s1.addChannel(new App());

        Student s2 = new Student("Ravi", "ECE");
        s2.addChannel(new SMS());

        board.addStudent(s1);
        board.addStudent(s2);

        ArrayList<String> d1 = new ArrayList<>();
        d1.add("CSE");

        Notice n1 = new Notice("Lab Closed Tomorrow", d1);
        board.post(n1);

        ArrayList<String> d2 = new ArrayList<>();
        d2.add("CSE");
        d2.add("ECE");

        Notice n2 = new Notice("Fee Deadline Extended", d2);
        board.post(n2);

        ArrayList<String> d3 = new ArrayList<>();
        Notice n3 = new Notice("Sports Day", d3);
        board.post(n3);
    }
}
