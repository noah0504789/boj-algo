package org.example.dp;

// N일까지 일하고, N+1일 째 퇴사
// 최대 수익을 구하라

import java.io.*;
import java.util.*;

public class 퇴사_2 {

    static int N, T_IDX = 0, P_IDX = 1;
    static int[] input, dp; // i번째까지 일했을 때 최대 이익
    static Work[] table;

    public static void main(String[] args) throws IOException {
        init();

        for (int i = 1; i <= N; i++) {
            if (i > table.length) break;
            Work work = table[i-1];

            if (work.end <= N) dp[work.end] = Math.max(dp[work.end], dp[work.start] + work.profit);
            dp[work.start+1] = Math.max(dp[work.start+1], dp[work.start]);
        }

        System.out.println(dp[N+1]);
    }

    private static void init() throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        table = new Work[N];
        dp = new int[N+2];

        for (int i = 0; i < N; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int start = i+1, end = input[T_IDX]+i+1, profit = input[P_IDX];
            table[i] = new Work(start, end, profit);
        }
    }

    static class Work implements Comparable<Work> {
        int start, end, profit;

        public Work(int start, int end, int profit) {
            this.start = start;
            this.end = end;
            this.profit = profit;
        }

        @Override
        public int compareTo(Work o) {
            return this.start - o.start;
        }
    }
}
