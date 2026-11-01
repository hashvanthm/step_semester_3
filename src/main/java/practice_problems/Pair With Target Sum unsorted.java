import java.util.*;

public class PairWithTargetSum {

    public static boolean bruteForce(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean usingHashSet(int[] nums, int target) {
        Set<Integer> seen = new HashSet<>();
        for (int x : nums) {
            if (seen.contains(target - x)) {
                return true;
            }
            seen.add(x);
        }
        return false;
    }

    public static void main(String[] args) {
        int[] a = {2, 7, 11, 15};
        int[] b = {3, 4, 6};
        System.out.println(usingHashSet(a, 9));
        System.out.println(usingHashSet(b, 20));
        System.out.println(bruteForce(a, 9));
        System.out.println(bruteForce(b, 20));
    }
}
