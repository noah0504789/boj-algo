package org.example.math;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 소수의_연속합 {
    private static final Scanner sc = new Scanner(System.in);
    private static List<Integer> list = new ArrayList<>();;
    private static boolean[] nonPrime;
    private static int n, ans;

    public static void main(String... args) {
        n = sc.nextInt();
        ans = 0;

        nonPrime = new boolean[n+1];

        for (int i = 2; i*i <= n; i++) {
            if (nonPrime[i]) continue;

            for (int j = i+i; j <= n; j+=i) nonPrime[j] = true;
        }

        for (int i = 2; i <= n; i++) {
            if (!nonPrime[i]) list.add(i);
        }

        int start = 0, end = 0, sum = 0;
        while (true) {
            if (end == list.size() && sum < n) break;
            if (sum == n) ans++;

            if (sum>=n) {
                sum -= list.get(start);
                start++;
            } else {
                sum += list.get(end);
                end++;
            }
        }

        System.out.println(ans);
    }
}
