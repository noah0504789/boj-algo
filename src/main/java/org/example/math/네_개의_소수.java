package org.example.math;

import java.util.*;

public class 네_개의_소수 {
    private static final Scanner sc = new Scanner(System.in);
    private static final int[] nums = new int[4];
    private static boolean[] check;
    private static final List<Integer> list = new ArrayList<>();
    private static int n;

    public static void main(String... args) {
        n = sc.nextInt();

        check = new boolean[n+1];
        Arrays.fill(check, true);

        int limit = (int) Math.sqrt(n);
        for (int i = 2; i <= limit; i++) {
            if (!check[i]) continue;

            for (int j = i+i; j <= n; j += i) check[j] = false;
        }

        for (int i = 2; i <= n; i++) {
            if (check[i]) list.add(i);
        }

        if (n % 2 == 0) {
            nums[0] = nums[1] = 2;
            n -= 4;
        } else {
            nums[0] = 2;
            nums[1] = 3;
            n -= 5;
        }

//        if (dfs(0, 0)) {
        if (twoPointer()) {
            for (int i = 0; i < 4; i++) System.out.printf(nums[i] + " ");
            return;
        }

        System.out.print(-1);
    }

    private static boolean twoPointer() {
        int left = 0, right = list.size()-1;

        while (left <= right) {
            int sum = list.get(left) + list.get(right);

            if (sum == n) {
                nums[2] = list.get(left);
                nums[3] = list.get(right);
                return true;
            } else if (sum < n) {
                left++;
            } else {
                right--;
            }
        }

        return false;
    }

    private static boolean dfs(int depth, int sum) {
        if (depth == 2) return sum == n ? true : false;

        for (int num : list) {
            nums[depth+2] = num;
            if (dfs(depth+1, sum+num)) return true;
        }

        return false;
    }
}
