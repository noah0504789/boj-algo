package org.example.math;

import java.util.*;

public class 소수의_연속합_시간초과 {
    private static final Scanner sc = new Scanner(System.in);
    private static List<Integer> list = new ArrayList<>();;
    private static int[] primes, prefixSum;
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

        primes = list.stream().mapToInt(Integer::intValue).toArray();
        prefixSum = new int[primes.length];
        prefixSum[0] = primes[0];

        for (int i = 1; i < primes.length; i++) prefixSum[i] = primes[i] + prefixSum[i-1];

        for (int i = 0; i < primes.length; i++) {
            for (int j = i; j < primes.length; j++) {
                if (partialSum(i, j) == n) {
                    System.out.println("start: " + i + " end: " + j);
                    ans++;
                    break;
                }
            }
        }

        System.out.println(ans);
    }

    private static int partialSum(int start, int end) {
        if (start == 0) return prefixSum[end];
        if (start == end) return primes[start];

        return prefixSum[end] - prefixSum[start-1];
    }
}
