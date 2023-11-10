package org.example.dp;

// 정수 n이 주어졌을 때 n을 1, 2, 3의 합으로 나타내는 방법의 수를 구하라

import java.io.*;

public class _1_2_3_더하기 {

    static int N, tot;
    static int[] dp;
    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        init();

        for (int i = 0; i < tot; i++) {
            N = Integer.parseInt(br.readLine());

            if (N == 1) {
                System.out.println(1);
                continue;
            }

            if (N == 2) {
                System.out.println(2);
                continue;
            }

            if (N == 3) {
                System.out.println(4);
                continue;
            }

            dp = new int[N+1];

            dp[1] = 1;
            dp[2] = 2;
            dp[3] = 4;

            for (int j = 4; j <= N; j++) {
                dp[j] = dp[j-3] + dp[j-2] + dp[j-1];
            }

            System.out.println(dp[N]);
        }
    }

    private static void init() throws IOException {
        // 입력
        br = new BufferedReader(new InputStreamReader(System.in));
        tot = Integer.parseInt(br.readLine());
    }
}

