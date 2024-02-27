package org.example.dp;

import java.io.*;
import java.util.StringTokenizer;

/**
 * @author noah kim
 * @date 2024-02-27
 * @link https://www.acmicpc.net/problem/1149
 * @requirement 모든 집을 칠하는 비용의 최솟값을 출력하라.
 * @keyword
    [집]
    - 갯수: N
    - 연결: 연쇄적

    [색칠]
    - 색깔: 빨, 초, 파
    - 조건: 연속적으로 집 색깔 다름
    - 비용: 1_000보다 작음
 * @input
    - N: 집 갯수(2<=N<=1_000)
    - 색칠 비용 (빨-초-파 순)
 * @output
 * @time_complex O(N)
 * @perf 14460kb / 136ms
 * */
public class RGB거리_T2_숙제 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int RED = 0;
    private static final int GREEN = 1;
    private static final int BLUE = 2;
    private static StringTokenizer st;
    private static int[][] dp; // i: 색칠한 색깔, j: 1~j 집까지 색칠한 비용
    private static int N, r, g, b;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        dp = new int[3][N+1];

        for (int house = 1; house <= N; house++) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            dp[RED][house] = Math.min(dp[GREEN][house-1], dp[BLUE][house-1])+r;
            dp[GREEN][house] = Math.min(dp[RED][house-1], dp[BLUE][house-1])+g;
            dp[BLUE][house] = Math.min(dp[RED][house-1], dp[GREEN][house-1])+b;
        }

        System.out.print(Math.min(Math.min(dp[RED][N], dp[GREEN][N]), dp[BLUE][N]));

        br.close();
    }
}
