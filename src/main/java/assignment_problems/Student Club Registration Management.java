import java.util.*;

class Student {
    String rollNumber;
    String name;

    Student(String rollNumber, String name) {
        this.rollNumber = rollNumber;
        this.name = name;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student other = (Student) o;
        return rollNumber.equals(other.rollNumber);
    }

    public int hashCode() {
        return rollNumber.hashCode();
    }
}

public class ClubRegistration {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Set<Student> members = new HashSet<>();
        boolean countPrinted = false;
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;
            String[] p = line.split("\\s+");
            Student s = new Student(p[1], p[2]);
            if (p[0].equals("ADD")) {
                if (members.add(s)) {
                    System.out.println("Added");
                } else {
                    System.out.println("duplicate rejected");
                }
            } else if (p[0].equals("CONTAINS")) {
                if (!countPrinted) {
                    System.out.println("member count " + members.size());
                    countPrinted = true;
                }
                System.out.println("contains: " + members.contains(s));
            }
        }
        if (!countPrinted) {
            System.out.println("member count " + members.size());
        }
    }
}
