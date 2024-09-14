package org.example.math;

import java.util.*;

public class GCD합 {

    private static final Scanner sc = new Scanner(System.in);
    private static final StringBuffer sb = new StringBuffer();
    private static int[] nums, comb;
    private static int t, n, ans;

    public static void main(String... args) {
        t = sc.nextInt();

        for (int i = 0; i < t; i++) {
            n = sc.nextInt();
            nums = new int[n];
            comb = new int[2];

            for (int j = 0; j < n; j++) nums[j] = sc.nextInt();

            ans = 0;

            comb(0, 0);

            sb.append(ans).append('\n');
        }

        System.out.print(sb);
    }

    private static void comb(int depth, int start) {
        if (depth == 2) {
            ans += gcd(comb[0], comb[1]);
            return;
        }

        for (int i = start; i < n; i++) {
            comb[depth] = nums[i];
            comb(depth+1, i+1);
        }
    }

    private static int gcd(int a, int b) {
        return a == 0 ? b : gcd(b%a, a);
    }
}
