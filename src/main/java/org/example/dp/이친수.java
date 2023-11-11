package org.example.dp;

// N자리 이친수의 개수를 구하라

// 이친수
// - 2진수중 특별한 성질을 가진 수
// - 특징
//   - 0으로 시작하지 않는다.
//   - 1이 두번 연속 나타나지 않는다

import java.io.*;
        import java.util.*;

public class 이친수 {

    static int N;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        init();

        if (N == 1) {
            System.out.println(1);
            return;
        } else if (N == 2) {
            System.out.println(1);
            return;
        }

        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= N; i++) dp[i] = dp[i-1] + dp[i-2];

        System.out.println(dp[N]);
    }

    private static void init() throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N+1];
    }
}
