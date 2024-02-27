package org.example.dp;

import java.io.*;

/**
 * @author noah kim
 * @date 2024-02-27
 * @link https://www.acmicpc.net/problem/1463
 * @requirement 주어진 정수를 최소횟수의 연산을 사용하여 1을 만들고, 그 연산횟수를 출력하라.
 * @keyword
    [연산]
    1. 3의 배수이면 3으로 나눔
    2. 2의 배수이면 2로 나눔
    3. 1을 뻄
 * @input
    - N: 1로 만들어야 할 정수(1<=N<=1_000_000)
 * @output
 * @time_complex
 * @perf 18336kb / 144ms
 */
public class _1로_만들기_T2 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[] dp;
    private static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        dp = new int[N+1];

        for (int i = 2; i <= N; i++) {
            dp[i] = dp[i-1]+1;
            if (i%2==0) dp[i] = Math.min(dp[i],dp[i/2]+1);
            if (i%3==0) dp[i] = Math.min(dp[i],dp[i/3]+1);
        }

        System.out.println(dp[N]);

        br.close();
    }
}
