package org.example.dp;

// 최소 비용으로 계단 오르기
// 규칙
// - 한 번에 한 계단 씩 혹은 두 계단 씩 오를 수 있다
//   - 한 계단 or 한 계단 + 한 계단 or 한 계단 + 두 계단
// - 연속된 세 개의 계단을 모두 밟아서는 안된다
// - 마지막 도착 계단은 반드시 밟아야 한다

import java.io.*;
import java.util.*;

public class 계단_오르기 {

    static int N;
    static int[] nums;
    static int[][] dp; // dp[i][j] : i번째 계단을 밟고 j번째 계단에 오르는데 걸리는 최소 비용
    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        init();

        for (int k = 1; k <= N; k++) {
            nums[k] = Integer.parseInt(br.readLine());
        }

        dp[0][1] = nums[1];
        dp[1][1] = nums[1];

        for (int i = 2; i <= N; i++) {
            dp[0][i] = Math.max(dp[0][i-2], dp[1][i-2]) + nums[i];
            dp[1][i] = dp[0][i-1] + nums[i];
        }

        System.out.println(Math.max(dp[0][N], dp[1][N]));
    }

    private static void init() throws IOException {
        // 입력
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new int[N+1];
        dp = new int[2][N+1];
        nums[0] = 0;
    }
}




