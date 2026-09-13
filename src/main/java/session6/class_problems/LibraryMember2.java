public class LibraryMember {
    protected String memberId;
    protected int borrowLimit;
    protected int booksBorrowed;

    public LibraryMember(String memberId, int borrowLimit) {
        if (memberId == null || memberId.trim().isEmpty() || memberId.length() < 4) {
            throw new IllegalArgumentException("Invalid memberId");
        }
        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
    }

    public void borrowBook() { booksBorrowed++; }
    public int getBooksBorrowed() { return booksBorrowed; }

    public String displayInfo() {
        return "General Member | Books Borrowed: " + booksBorrowed;
    }
}

class StudentMember extends LibraryMember {
    protected String course;

    public StudentMember(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }

    @Override
    public String displayInfo() {
        return "Student Member | Course: " + course + " | Books Borrowed: " + booksBorrowed;
    }
}

// Multilevel inheritance: 3 generations deep
class HonorsStudentMember extends StudentMember {
    private int bonusLimit;

    public HonorsStudentMember(String memberId, int borrowLimit, String course, int bonusLimit) {
        super(memberId, borrowLimit, course);
        this.bonusLimit = bonusLimit;
    }

    @Override
    public String displayInfo() {
        return "Honors Student Member | Course: " + course + " | Bonus Limit: " + bonusLimit
                + " | Books Borrowed: " + booksBorrowed;
    }
}

// Hierarchical inheritance: independent branch from LibraryMember
class FacultyMember extends LibraryMember {
    private String department;

    public FacultyMember(String memberId, int borrowLimit, String department) {
        super(memberId, borrowLimit);
        this.department = department;
    }

    @Override
    public String displayInfo() {
        return "Faculty Member | Department: " + department + " | Books Borrowed: " + booksBorrowed;
    }
}

class MembershipUtil {
    static String classifyGeneration(LibraryMember member) {
        if (member instanceof HonorsStudentMember) {
            return "Multilevel descendant (3 generations deep)";
        } else if (member instanceof FacultyMember) {
            return "Hierarchical sibling (independent branch)";
        } else if (member instanceof StudentMember) {
            return "Direct subclass";
        } else {
            return "Base member";
        }
    }

    static int getTotalBooksBorrowed(LibraryMember[] members) {
        int total = 0;
        for (LibraryMember m : members) {
            total += m.getBooksBorrowed(); // polymorphic call
        }
        return total;
    }
}

class Main2 {
    public static void main(String[] args) {
        StudentMember studentMember = new StudentMember("STU2", 3, "CSE");
        studentMember.borrowBook(); studentMember.borrowBook();

        HonorsStudentMember honorsMember = new HonorsStudentMember("STU3", 3, "ECE", 2);
        honorsMember.borrowBook();

        FacultyMember facultyMember = new FacultyMember("STU4", 5, "Physics");
        facultyMember.borrowBook(); facultyMember.borrowBook(); facultyMember.borrowBook();

        System.out.println(MembershipUtil.classifyGeneration(honorsMember));
        System.out.println(MembershipUtil.classifyGeneration(facultyMember));

        System.out.println(MembershipUtil.getTotalBooksBorrowed(
            new LibraryMember[]{studentMember, honorsMember, facultyMember}
        )); // 6
    }
}
