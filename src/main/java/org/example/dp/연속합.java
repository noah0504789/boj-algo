package org.example.dp;

import java.io.*;
import java.util.*;

public class 연속합 {

    static int N;
    static int[] nums, dp;

    public static void main(String[] args) throws IOException {
        init();

        for (int i = 1; i <= N; i++) {
            dp[i] = Math.max(0, dp[i-1] + nums[i-1]);
        }

        int min = Arrays.stream(nums).max().getAsInt();

        if (min < 0) System.out.println(min);
        else System.out.println(Arrays.stream(dp).max().getAsInt());
    }

    private static void init() throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        dp = new int[N+1];
        nums = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
