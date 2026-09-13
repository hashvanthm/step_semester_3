public class LibraryMember {
    protected String memberId;
    protected int borrowLimit;
    private int[] fineHistory = new int[10];
    private int fineCount = 0;

    public LibraryMember(String memberId, int borrowLimit) {
        if (memberId == null || memberId.trim().isEmpty() || memberId.length() < 4) {
            throw new IllegalArgumentException("Invalid memberId");
        }
        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
    }

    protected void chargeFine(int amount) {
        fineHistory[fineCount++] = amount;
    }

    int[] getFineHistory() {
        int[] copy = new int[fineCount];
        System.arraycopy(fineHistory, 0, copy, 0, fineCount); // defensive copy
        return copy;
    }

    int getTotalFine() {
        int total = 0;
        for (int i = 0; i < fineCount; i++) total += fineHistory[i];
        return total;
    }
}

class StudentMember extends LibraryMember {
    private String course;

    public StudentMember(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }

    @Override
    protected void chargeFine(int amount) {
        super.chargeFine(amount / 2); // reuse parent's deduction + recording logic
    }
}

class Main3 {
    public static void main(String[] args) {
        StudentMember s = new StudentMember("STU5", 3, "CSE");
        s.chargeFine(100);
        System.out.println(s.getTotalFine()); // 50

        int[] history = s.getFineHistory();
        history[0] = 999; // tampering with returned array
        System.out.println(java.util.Arrays.toString(s.getFineHistory())); // [50]
    }
}
