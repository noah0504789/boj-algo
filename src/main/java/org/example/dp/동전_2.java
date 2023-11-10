package org.example.dp;

import java.util.*;

public class 동전_2 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        int[] coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = sc.nextInt();
        }

        int[] dp = new int[k + 1];
        Arrays.fill(dp, 100001);
        dp[0] = 0;

        for (int coin : coins) {
            for (int i = coin; i <= k; i++) {
                dp[i] = Math.min(dp[i], dp[i-coin] + 1);
            }
        }

        System.out.println(dp[k] == 100001 ? -1 : dp[k]);
        sc.close();
    }
}


