import java.util.*;

public class MergeTokens {

    public static List<Integer> mergeTokens(List<Integer> a, List<Integer> b) {
        List<Integer> result = new ArrayList<>();
        int i = 0, j = 0;
        while (i < a.size() && j < b.size()) {
            if (a.get(i) <= b.get(j)) {
                result.add(a.get(i++));
            } else {
                result.add(b.get(j++));
            }
        }
        while (i < a.size()) {
            result.add(a.get(i++));
        }
        while (j < b.size()) {
            result.add(b.get(j++));
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(mergeTokens(Arrays.asList(3, 8, 15, 20), Arrays.asList(5, 8, 12)));
        System.out.println(mergeTokens(new ArrayList<>(), Arrays.asList(4, 9)));
    }
}
