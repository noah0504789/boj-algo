package org.example.dp;

// 길이가 N인 계단 수 구하기

import java.io.*;
        import java.util.*;

public class 쉬운_계단_수 {

    static int N;
    static long ans = 0;
    static int[] input;
    static long[][] dp;

    public static void main(String[] args) throws IOException {
        init();

        if (N == 1) {
            System.out.println(9);
            return;
        }

        if (N == 2) {
            System.out.println(17);
            return;
        }

        for (int i = 2; i <= N; i++) {
            dp[i][0] = dp[i-1][1] % 1000000000;
            dp[i][9] = dp[i-1][8] % 1000000000;
            for (int j = 1; j <= 8; j++) {
                dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1]) % 1000000000;
            }
        }

        for (int k = 0; k <= 9; k++) {
            ans += dp[N][k];
        }

        System.out.println(ans % 1000000000);
    }

    private static void init() throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = input[0];

        dp = new long[N+1][10];

        for (int i = 1; i <= 9; i++) dp[1][i] = 1;
    }}
