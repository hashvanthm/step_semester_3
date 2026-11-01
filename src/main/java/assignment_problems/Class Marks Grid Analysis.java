import java.util.*;

public class MarksGridAnalysis {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> names = new ArrayList<>();
        List<int[]> rows = new ArrayList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;
            int open = line.indexOf('[');
            int close = line.indexOf(']');
            String name = line.substring(0, open).trim();
            String[] parts = line.substring(open + 1, close).split(",");
            int[] marks = new int[3];
            for (int i = 0; i < 3; i++) {
                marks[i] = Integer.parseInt(parts[i].trim());
            }
            names.add(name);
            rows.add(marks);
        }

        int n = names.size();
        int[] totals = new int[n];
        int[] subjectSum = new int[3];
        int topIndex = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                totals[i] += rows.get(i)[j];
                subjectSum[j] += rows.get(i)[j];
            }
            if (totals[i] > totals[topIndex]) {
                topIndex = i;
            }
        }

        StringBuilder sb = new StringBuilder("Totals ");
        for (int i = 0; i < n; i++) {
            sb.append(names.get(i)).append(" ").append(totals[i]);
            if (i < n - 1) sb.append(", ");
        }
        System.out.println(sb);

        StringBuilder avg = new StringBuilder("averages ");
        for (int j = 0; j < 3; j++) {
            avg.append(String.format(Locale.US, "%.2f", (double) subjectSum[j] / n));
            if (j < 2) avg.append(", ");
        }
        System.out.println(avg);

        System.out.println("topper " + names.get(topIndex) + " (" + totals[topIndex] + ")");
    }
}
