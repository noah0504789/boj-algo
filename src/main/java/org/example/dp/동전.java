package org.example.dp;

import java.io.*;
import java.util.*;
import java.util.stream.*;

public class 동전 {

    static int N, tot, sum;
    static int[] sums, coins;
    static int[][] nums, dp;

    public static void main(String[] args) throws IOException {
        init();

        for (int i = 0; i < N; i++) {
            sum = sums[i];
            coins = nums[i];

            for (int coin : coins) {
                for (int j = coin; j <= sum; j++) {
                    dp[i][j] += dp[i][j-coin];
                }
            }

            System.out.println(dp[i][sum]);
        }
    }

    private static void init() throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        sums = new int[N];
        nums = new int[N][20];
        dp = new int[N][10001];

        for (int i = 0; i < N; i++) {
            tot = Integer.parseInt(br.readLine());
            nums[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            sums[i] = Integer.parseInt(br.readLine());
            dp[i][0] = 1;
        }
    }
}




