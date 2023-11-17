package org.example.dp;

import java.io.*;
import java.util.*;

// 가장 많은 포도주 마시기
// - 연속된 3잔의 포도주는 마실 수 없음
public class 포도주_시식 {

    static int N;
    static int[] nums;
    static long[] dp; // i 잔까지 마셨을 때 최대 양

    public static void main(String[] args) throws IOException {
        init();

        //    6  10  13  9  8  1

        //    0    1     2       3         4         5
//1     // 0  6   10    6+13   6+10+9   10+13+8    6+13+9+1
//2     // 1  6  6+10  10+13   6+13+9   6+10+9+8   10+13+8+1

        for (int i = 3; i < N; i++) {
            dp[i] = Math.max(dp[i-1], Math.max(dp[i-2], dp[i-3]+nums[i-1])+nums[i]);
        }

        System.out.println(dp[N-1]);
    }

    private static void init() throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        dp = new long[N];

        for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(br.readLine());

        dp[0] = nums[0];
        if (N > 1) dp[1] = nums[0]+nums[1];
        if (N > 2) dp[2] = Math.max(dp[1], Math.max(nums[0]+nums[2], nums[1]+nums[2]));
    }
}
