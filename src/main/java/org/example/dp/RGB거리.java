package org.example.dp;

// N개의 집을 칠하는 최소비용을 구하라.
// 규칙
// - 집은 빨강, 초록, 파랑 중 하나의 색으로 칠해야 한다.
// - N번 집의 색은 N-1번 집의 색과 같지 않아야 한다.

import java.io.*;
import java.util.*;

public class RGB거리 {
    static int N;
    static int[][] costs, dp; // i번째 집을 j의 색으로 지었을 때 드는 최소비용

    public static void main(String[] args) throws IOException {
        init();

        dp[0][1] = costs[0][1];
        dp[1][1] = costs[1][1];
        dp[2][1] = costs[2][1];

        for (int i = 2; i <= N; i++) {
            dp[0][i] = Math.min(dp[1][i-1], dp[2][i-1]) + costs[0][i];
            dp[1][i] = Math.min(dp[0][i-1], dp[2][i-1]) + costs[1][i];
            dp[2][i] = Math.min(dp[0][i-1], dp[1][i-1]) + costs[2][i];
        }

        System.out.println(Math.min(Math.min(dp[0][N], dp[1][N]), dp[2][N]));
    }

    private static void init() throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        costs = new int[3][N+1];
        dp = new int[3][N+1];

        for (int i = 1; i <= N; i++) {
            String[] input = br.readLine().split(" ");
            costs[0][i] = Integer.parseInt(input[0]);
            costs[1][i] = Integer.parseInt(input[1]);
            costs[2][i] = Integer.parseInt(input[2]);
        }
    }
}
