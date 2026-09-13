public class LibraryMember {
    protected String memberId;
    protected int borrowLimit;
    protected int booksBorrowed;

    public LibraryMember(String memberId, int borrowLimit) {
        if (memberId == null || memberId.trim().isEmpty() || memberId.length() < 4) {
            throw new IllegalArgumentException("Invalid memberId: " + memberId);
        }
        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
        this.booksBorrowed = 0;
    }

    public void borrowBook() {
        booksBorrowed++;
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    static String enrollBatch(String[] memberIds, int borrowLimit) {
        int enrolled = 0, rejected = 0;
        for (String id : memberIds) {
            try {
                new LibraryMember(id, borrowLimit); // just to validate/construct
                enrolled++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }
        return "Enrolled: " + enrolled + " | Rejected: " + rejected;
    }
}

class StudentMember extends LibraryMember {
    private String course;

    public StudentMember(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit); // forward shared fields, don't duplicate
        this.course = course;
    }
}

class Main1 {
    public static void main(String[] args) {
        StudentMember s = new StudentMember("STU10", 3, "CSE");
        s.borrowBook();
        s.borrowBook();
        System.out.println(s.getBooksBorrowed()); // 2

        System.out.println(LibraryMember.enrollBatch(
            new String[]{"STU1", "LB1", "STU2", " ", "STU3"}, 3
        )); // Enrolled: 3 | Rejected: 2
    }
}
