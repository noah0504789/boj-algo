package org.example.math.permutation;

import java.util.*;

public class 수들의_합_6 {
    private static Scanner sc = new Scanner(System.in);
    private static StringBuilder sb = new StringBuilder();
    private static int[] nums, mul;
    private static int n, f;

    public static void main(String... args) {
        n = sc.nextInt();
        f = sc.nextInt();

        nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = i+1;

        mul = new int[n];
        mul[0] = 1;
        for (int r = 1; r < n; r++) mul[r] = mul[r-1] * (n-r) / r;

        do {
            if (sum() == f) {
                for (int num : nums) sb.append(num).append(' ');

                System.out.print(sb.toString().trim());
                return;
            }
        } while(np());
    }

    private static int sum() {
        int sum = 0;

        for (int i = 0; i < n; i++) sum += nums[i] * mul[i];

        return sum;
    }

    private static boolean np() {
        int i = nums.length-1;

        while (i > 0 && nums[i-1] >= nums[i]) i--;

        if (i == 0) return false;

        int pivot = i-1;

        int j = nums.length-1;

        while (nums[pivot] >= nums[j]) j--;

        swap(pivot, j);

        reverse(pivot+1, nums.length-1);

        return true;
    }

    private static void swap(int start, int end) {
        int temp = nums[start];
        nums[start] = nums[end];
        nums[end] = temp;
    }

    private static void reverse(int start, int end) {
        while (start < end) swap(start++, end--);
    }
}
