package org.example.greedy;

import java.io.*;

/**
 * @author noah kim
 * @date 2024-02-13
 * @link https://www.acmicpc.net/problem/2839
 * @requirement 배달할 때 필요한 최소 봉지수를 출력하라. (-1: 정확히 떨어지지 않을경우)
 * @keyword
    [봉지]
    - 종류: 3kg, 5kg
 * @input
    - N: 총 설탕량(3<=N<=5_000)
 * @output
 * @time_complex O(N)
 * @perf 14248kb / 124ms
 */
public class 설탕_배달 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int NOT_EXIST_SIGNAL = -1;
    private static final int THREE_BAG_IDX = 0;
    private static final int FIVE_BAG_IDX = 1;
    private static final int THREE_BAG_W = 3;
    private static final int FIVE_BAG_W = 5;
    private static int[][] dp;
    private static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        dp = new int[2][N+1]; // i 번째까지의 봉지로 j 무게를 옮기는데 최소로 사용하는 봉지 수

        for (int amt = 1; amt <= N; amt++) {
            if (amt % THREE_BAG_W == 0) dp[THREE_BAG_IDX][amt] = amt / THREE_BAG_W;
        }

        for (int amt = 1; amt <= N; amt++) {
            int restAfterThree = amt - THREE_BAG_W;

            if (amt % FIVE_BAG_W == 0) dp[FIVE_BAG_IDX][amt] = amt / FIVE_BAG_W;
            else if (restAfterThree > 0 && dp[FIVE_BAG_IDX][restAfterThree] > 0) dp[FIVE_BAG_IDX][amt] = dp[FIVE_BAG_IDX][restAfterThree] + 1;
            else if (restAfterThree % FIVE_BAG_W == 0) dp[FIVE_BAG_IDX][amt] = dp[THREE_BAG_IDX][restAfterThree] + 1;
            else if (dp[THREE_BAG_IDX][amt] > 0) dp[FIVE_BAG_IDX][amt] = dp[THREE_BAG_IDX][amt];
        }

        System.out.println(dp[FIVE_BAG_IDX][N] > 0 ? dp[FIVE_BAG_IDX][N] : NOT_EXIST_SIGNAL);
    }
}
