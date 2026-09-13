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

    public String displayInfo() {
        return "General | Books: " + booksBorrowed;
    }
}

class StudentMember extends LibraryMember {
    private String course;

    public StudentMember(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }

    public String getCourse() { return course; }

    @Override
    public String displayInfo() {
        return "Student | Course: " + course + " | Books: " + booksBorrowed;
    }
}

class ReportUtil {
    static String batchPrint(LibraryMember[] members) {
        StringBuilder sb = new StringBuilder();
        for (LibraryMember m : members) {
            sb.append(m.displayInfo()); // polymorphic call, no instanceof chain here

            if (m instanceof StudentMember) {
                StudentMember sm = (StudentMember) m; // guarded downcast
                sb.append(" [Course via downcast: ").append(sm.getCourse()).append("]");
            }
            sb.append(" | ");
        }
        return sb.toString();
    }
}

class Main4 {
    public static void main(String[] args) {
        LibraryMember[] members = {
            new LibraryMember("LB5", 3),
            new StudentMember("STU6", 3, "ECE")
        };
        System.out.println(ReportUtil.batchPrint(members));

        // Demonstrates the ClassCastException case:
        LibraryMember plain = new LibraryMember("LB6", 3);
        try {
            StudentMember bad = (StudentMember) plain; // compiles fine, fails at runtime
        } catch (ClassCastException e) {
            System.out.println("ClassCastException at runtime");
        }
    }
}
