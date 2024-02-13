package org.example.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author noah kim
 * @date 2024-02-13
 * @link https://www.acmicpc.net/problem/16435
 * @requirement 늘릴 수 있는 스네이크버드의 최대 길이를 출력하라.
 * @keyword
    [과일 먹기]
    - 길이+1
    - 나무 높이
    - 자신의 길이보다 작거나 같은 나무의 과일만 먹을 수 있음.
 * @input
    - N: 과일 수(1<=N<=1_000), L: 스네이크버드 초기 길이(1<=L<=10_000)
    - 나무들의 높이 (1<=h_i<=10_000)
 * @output
 * @time_complex O(NlogN)
 * @perf 14632kb / 136ms
 */
public class 스네이크버드 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static int[] heights;
    private static int N, L;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        heights = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();

        for (int i = 0; i < heights.length; i++) {
            if (L < heights[i]) break;

            L++;
        }

        System.out.println(L);
    }
}
