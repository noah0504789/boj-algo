package org.example.dp;

// 삼성 SW 역량테스트 기출
// 문제링크 : https://www.acmicpc.net/problem/14501

// 최대한 많은 이익 얻기

// 퇴사 : N+1 일째 되는 날
// 퇴사 전까지 일할 수 있음
// 상담을 동시에 할 수 없음

import java.io.*;
import java.util.*;
import java.util.stream.*;

public class 퇴사 {
    // 최대한 많은 이익 얻기

    // 퇴사 : N+1 일째 되는 날
    // 퇴사 전까지 일할 수 있음
    // 상담을 동시에 할 수 없음

    static int N;
    static int[] t, p;

    public static void main(String[] args) throws IOException {
        init();

        // dp[day] : day에서 시작해서 퇴사일까지 최대값
        int[] dp = new int[N+2];

        for (int i = N; i > 0; i--) {
            // 오늘의 스케쥴을 하는 경우 : dp[day + t[day]] + p[day]
            // 오늘의 스케쥴을 하지 않는 경우 : dp[day+1]
            if (i + t[i] > N+1) dp[i] = dp[i+1];
            else dp[i] = Math.max(dp[i+1], dp[i+t[i]]+p[i]);
        }

        System.out.println(dp[1]);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        t = new int[N+1];
        p = new int[N+1];

        for (int i = 1; i <= N; i++) {
            String[] input = br.readLine().split(" ");
            t[i] = Integer.parseInt(input[0]);
            p[i] = Integer.parseInt(input[1]);
        }
    }
}

/**
    static int N, ans, max;
    static List<Day> days;

    public static void main(String[] args) throws IOException {
        init();

        days = days.stream()
                .filter(d -> d.end <= N)
                .sorted()
                .collect(Collectors.toList());

        ans = 0;
        max = days.stream().mapToInt(d -> d.end).max().getAsInt();

        for (Day day : days) {
            if (day.end == max) dfs(day, day.profit);
            else break;
        }

        System.out.println(ans);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        days = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            String[] input = br.readLine().split(" ");
            days.add(new Day(i, i+Integer.parseInt(input[0])-1, Integer.parseInt(input[1])));
        }
    }

    private static void dfs(Day day, int cumulativeProfit) {
        for (Day curD : days) {
            if (curD == day) continue;
            if (curD.end >= day.start) continue;

            dfs(curD, cumulativeProfit + curD.profit);
        }

        ans = Math.max(ans, cumulativeProfit);
    }

static class Day implements Comparable<Day> {
    int start, end, profit;

    public Day(int start, int end, int profit) {
        this.start = start;
        this.end = end;
        this.profit = profit;
    }

    @Override
    public int compareTo(Day o) {
        return this.end == o.end ? o.start - this.start : o.end - this.end;
    }
}
*/
