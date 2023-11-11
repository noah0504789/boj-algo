package org.example.dp;

import java.io.*;
import java.util.*;

public class _1로_만들기_2 {

    static int N;
    static int[] pre, dp; // i의 수에서 1를 만들었을 때 최소 연산 횟수
    static StringBuffer ways = new StringBuffer();

    public static void main(String[] args) throws IOException {
        init();

        dp[1] = 0;
        pre[1] = 0;

        dp[2] = 1;
        pre[2] = 1;

        dp[3] = 1;
        pre[3] = 1;

        for (int i = 4; i <= N; i++) {
            dp[i] = dp[i-1]+1;
            pre[i] = i-1;

            if (i%3 == 0 && dp[i] > dp[i/3]+1) {
                dp[i] = dp[i/3]+1;
                pre[i] = i/3;
            }

            if (i%2 == 0 && dp[i] > dp[i/2]+1) {
                dp[i] = dp[i/2]+1;
                pre[i] = i/2;
            }
        }

        System.out.println(dp[N]);

        while (N > 0) {
            ways.append(N + " ");
            N = pre[N];
        }

        System.out.println(ways.toString().trim());
    }

    private static void init() throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N+1];
        pre = new int[N+1];
        Arrays.fill(dp, N);
    }
}
