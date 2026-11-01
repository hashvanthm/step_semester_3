import java.util.*;

public class MostPopularOrder {

    public static String[] mostPopular(List<String> orders) {
        Map<String, Integer> count = new HashMap<>();
        for (String item : orders) {
            count.put(item, count.getOrDefault(item, 0) + 1);
        }
        String best = null;
        int bestCount = 0;
        for (String item : orders) {
            if (count.get(item) > bestCount) {
                bestCount = count.get(item);
                best = item;
            }
        }
        return new String[]{best, String.valueOf(bestCount)};
    }

    public static void main(String[] args) {
        List<String> o1 = Arrays.asList("dosa", "idli", "vada", "dosa", "idli", "dosa", "tea");
        List<String> o2 = Arrays.asList("tea", "coffee", "coffee", "tea");
        System.out.println(Arrays.toString(mostPopular(o1)));
        System.out.println(Arrays.toString(mostPopular(o2)));
    }
}
