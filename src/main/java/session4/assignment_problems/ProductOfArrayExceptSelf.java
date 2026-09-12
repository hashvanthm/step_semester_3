import java.util.Scanner;
import java.util.Arrays;

public class ProductExceptSelf {

    static int[] productExceptSelf(int[] nums) {

        int n = nums.length;
        int[] result = new int[n];

        int product = 1;

        // Left products
        for (int i = 0; i < n; i++) {
            result[i] = product;
            product = product * nums[i];
        }

        product = 1;

        // Right products
        for (int i = n - 1; i >= 0; i--) {
            result[i] = result[i] * product;
            product = product * nums[i];
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

        int[] result = productExceptSelf(nums);

        System.out.println(Arrays.toString(result));
    }
}
