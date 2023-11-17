package org.example.dp;

// 정수 n이 주어졌을 때 n을 1, 2, 3의 합으로 나타내는 방법의 수를 구하라

import java.io.*;
import java.util.*;

public class _1_2_3_더하기_3 {

    static int T, num;
    static int[] input;
    static long[] dp;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            if (!init()) continue;

            for (int i = 4; i <= num; i++) {
                dp[i] = (dp[i-1] + dp[i-2]+ dp[i-3]) % 1000000009;
            }

            System.out.println(dp[num]);
        }
    }

    private static boolean init() throws IOException {
        input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        num = input[0];

        if (num == 1) {
            System.out.println(1);
            return false;
        } else if (num == 2) {
            System.out.println(2);
            return false;
        } else if (num == 3) {
            System.out.println(4);
            return false;
        }

        dp = new long[num+1];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        return true;
    }
}
