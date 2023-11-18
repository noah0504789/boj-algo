package org.example.dp;

// 증가하는 부분 수열 중 합이 가장 큰 것을 리턴하시오.

import java.io.*;
import java.util.*;

public class 가장_큰_증가하는_부분_수열 {

    static int N;
    static int[] nums;
    static int[] prev, dp;

    public static void main(String[] args) throws IOException {
        init();

        dp[0] = nums[0];

        for (int i = 1; i < N; i++) {
            dp[i] = nums[i];
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) dp[i] = Math.max(dp[i], dp[j]+nums[i]);
            }
        }

        System.out.println(Arrays.stream(dp).max().getAsInt());
    }

    private static void init() throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        dp = new int[N];
        prev = new int[N];
    }
}
