package org.example.dp;

import java.io.*;
import java.util.*;

public class 파도반_수열 {

    static BufferedReader br;
    static int T;
    static int[] input;
    static long[] dp;

    public static void main(String[] args) throws IOException {
        init();

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());

            if (N <= 3) {
                System.out.println(1);
                continue;
            } else if (N <= 5) {
                System.out.println(2);
                continue;
            }

            dp = new long[N+1];
            dp[1] = 1;
            dp[2] = 1;
            dp[3] = 1;
            dp[4] = 2;
            dp[5] = 2;

            int subIdx = 1;
            long pre = 2;
            for (int i = 6; i <= N; i++) {
                dp[i] = pre + dp[subIdx++];
                pre =  dp[i];
            }

            System.out.println(dp[N]);
        }
    }

    private static void init() throws IOException {
        // 입력
        br = new BufferedReader(new InputStreamReader(System.in));

        input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        T = input[0];
    }
}
