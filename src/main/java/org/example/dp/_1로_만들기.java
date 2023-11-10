package org.example.dp;

import java.io.*;
import java.util.*;

public class _1로_만들기 {
    static int N;
    static int[] dp; // i를 1로 만들기 위해 필요한 연산 사용 횟수의 최솟값

    public static void main(String[] args) throws IOException {
        init();

        dp[1] = 0;

        for (int i = 2; i <= N; i++) {
            dp[i] = dp[i-1] + 1;
            if (i % 2 == 0) dp[i] = Math.min(dp[i], dp[i/2]+1);
            if (i % 3 == 0) dp[i] = Math.min(dp[i], dp[i/3]+1);
        }

        System.out.println(dp[N]);
    }

    private static void init() throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        dp = new int[N+1];
    }
}
