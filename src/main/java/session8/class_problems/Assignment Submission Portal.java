
abstract class Assignment {
    String title;
    int maxMarks;

    Assignment(String title, int maxMarks) {
        this.title = title;
        this.maxMarks = maxMarks;
    }

    abstract double getPenalty(int days);
}

class Coding extends Assignment {
    Coding(String title, int marks) {
        super(title, marks);
    }

    double getPenalty(int days) {
        return days * 10;
    }
}

class Written extends Assignment {
    Written(String title, int marks) {
        super(title, marks);
    }

    double getPenalty(int days) {
        return days * 20;
    }
}

class Student {
    String name;

    Student(String name) {
        this.name = name;
    }
}

class Submission {
    Student student;
    Assignment assignment;
    private String status = "Submitted";

    Submission(Student s, Assignment a) {
        student = s;
        assignment = a;
        System.out.println(student.name + " submitted " + assignment.title);
    }

    void grade(int marks, int lateDays) {
        if (!status.equals("Submitted")) {
            System.out.println("Cannot resubmit: Already graded.");
            return;
        }

        double penalty = assignment.getPenalty(lateDays);
        double finalMarks = marks - (marks * penalty / 100);

        if (finalMarks < 0)
            finalMarks = 0;

        status = "Graded";

        System.out.println(student.name + " graded: "
                + finalMarks + "/" + assignment.maxMarks);
        System.out.println("Status: " + status);
    }

    void resubmit() {
        if (status.equals("Graded"))
            System.out.println("Cannot resubmit: Already graded.");
        else
            System.out.println("Resubmission accepted.");
    }
}

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student("Asha");
        Student s2 = new Student("Ravi");

        Assignment a1 = new Coding("Linked List Lab", 50);
        Assignment a2 = new Written("Design Essay", 50);

        Submission sub1 = new Submission(s1, a1);
        Submission sub2 = new Submission(s2, a2);

        sub1.grade(45, 0);
        sub2.grade(40, 2);

        sub1.resubmit();
    }
}
