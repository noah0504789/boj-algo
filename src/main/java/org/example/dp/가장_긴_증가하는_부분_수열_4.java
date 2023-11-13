package org.example.dp;

import java.io.*;
import java.util.*;

public class 가장_긴_증가하는_부분_수열_4 {

    static int N;
    static int[] input, nums, dp, path;

    public static void main(String[] args) throws IOException {
        init();

        if (N == 1) {
            System.out.println(1);
            System.out.println(nums[0]);
            return;
        }

        for (int i = 0; i < N; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    path[i] = j;
                }
            }
        }

        int maxIdx = 0, maxVal = dp[0];
        for (int k = 1; k < N; k++) {
            if (dp[k] > dp[k-1]) {
                maxVal = dp[k];
                maxIdx = k;
            }
        }

        List<Integer> list = new ArrayList<>();
        while (maxIdx != -1) {
            list.add(nums[maxIdx]);
            maxIdx = path[maxIdx];
        }

        System.out.println(maxVal);

        StringBuffer sb = new StringBuffer();
        list.stream().sorted().forEach(n -> sb.append(n).append(' '));

        System.out.println(sb.toString());
    }

    private static void init() throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = input[0];

        nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        dp = new int[N];
        path = new int[N];

        Arrays.fill(path, -1);
    }
}
