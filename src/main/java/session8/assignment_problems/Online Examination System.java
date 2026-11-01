
import java.util.*;

abstract class Question {
    String question;
    String answer;
    int marks;

    Question(String q, String a, int m) {
        question = q;
        answer = a;
        marks = m;
    }

    abstract int check(String userAnswer);
}

class MCQ extends Question {
    MCQ(String q, String a, int m) {
        super(q, a, m);
    }

    int check(String userAnswer) {
        return answer.equalsIgnoreCase(userAnswer) ? marks : 0;
    }
}

class TrueFalse extends Question {
    TrueFalse(String q, String a, int m) {
        super(q, a, m);
    }

    int check(String userAnswer) {
        return answer.equalsIgnoreCase(userAnswer) ? marks : 0;
    }
}

class Examination {
    ArrayList<Question> questions = new ArrayList<>();

    void addQuestion(Question q) {
        questions.add(q);
    }
}

class Attempt {
    Examination exam;
    ArrayList<String> answers = new ArrayList<>();
    boolean submitted = false;

    Attempt(Examination exam) {
        this.exam = exam;
    }

    void answer(String ans) {
        if (!submitted) {
            answers.add(ans);
            System.out.println("Answer recorded.");
        } else {
            System.out.println("Cannot change submitted answers.");
        }
    }

    void submit() {
        if (submitted) {
            System.out.println("Already submitted.");
            return;
        }

        submitted = true;
        int total = 0;
        int max = 0;

        for (int i = 0; i < exam.questions.size(); i++) {
            Question q = exam.questions.get(i);
            int score = q.check(answers.get(i));

            total += score;
            max += q.marks;

            System.out.println("Question " + (i + 1) +
                    ": " + score + " points");
        }

        System.out.println("Total score: " + total + "/" + max);
    }
}

public class Main {
    public static void main(String[] args) {
        Examination exam = new Examination();

        exam.addQuestion(new MCQ("Java answer?", "C", 5));
        exam.addQuestion(new TrueFalse("Java is easy?", "False", 5));

        Attempt a = new Attempt(exam);

        System.out.println("Exam started.");

        a.answer("C");
        a.answer("True");

        a.submit();
        a.answer("B");
    }
}
