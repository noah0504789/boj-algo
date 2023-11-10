package org.example.dp;

import java.io.*;
import java.util.*;

// 2*n 크기의 직사각형을 1*2, 2*1 타일로 채우는 방법의 수를 구하라
public class _2_n_타일링 {

    static int N;
    static int[] dp; // j열까지 최소 타월 수

    public static void main(String[] args) throws IOException {
        init();

        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= N; i++) {
            dp[i] = (dp[i-1] + dp[i-2])%10007;
        }

        System.out.println(dp[N]);
    }

    private static void init() throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        dp = new int[1001];
    }
}
