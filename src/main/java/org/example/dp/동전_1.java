package org.example.dp;

import java.io.*;

public class 동전_1 {
    static String[] input = null;
    static int N, K;
    static int[] coins, dp;

    public static void main(String[] args) throws IOException {
        init();

        for (int coin : coins) {
            for (int i = coin; i <= K; i++) {
                dp[i] += dp[i-coin];
            }
        }

        System.out.println(dp[K]);
    }

    private static void init() throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);
        coins = new int[N];
        dp = new int[K+1];
        dp[0] = 1;

        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }
    }
}
