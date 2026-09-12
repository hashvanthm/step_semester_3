import java.util.*;

public class ThreeSum {

    static int[][] threeSum(int[] nums) {

        Arrays.sort(nums);

        ArrayList<int[]> list = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {

            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {

                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {

                    list.add(new int[]{
                        nums[i], nums[left], nums[right]
                    });

                    while (left < right &&
                           nums[left] == nums[left + 1]) {
                        left++;
                    }

                    while (left < right &&
                           nums[right] == nums[right - 1]) {
                        right--;
                    }

                    left++;
                    right--;

                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        int[][] result = new int[list.size()][];

        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }

        return result;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter array size: ");
        int n = sc.nextInt();

        int[] nums = new int[n];

        System.out.println("Enter elements:");

        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        int[][] result = threeSum(nums);

        System.out.println("Triplets:");

        for (int[] triplet : result) {
            System.out.println(Arrays.toString(triplet));
        }
    }
}
