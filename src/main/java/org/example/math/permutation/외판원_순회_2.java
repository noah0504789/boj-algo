package org.example.math.permutation;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @author noah kim
 * @date 2024-02-29
 * @link https://www.acmicpc.net/problem/10971
 * @requirement 가장 적은 비용으로 모든 도시를 순회할 때의 최소비용을 출력하라.
 * @keyword
    [순회]
    - 출발지에서 N개의 도시를 모두 거쳐 다시 출발지로 돌아옴.
    - 재방문 불가
    - 방문
      - 단방향 (거리. 양의 정수)
 * @input
    - N: 도시 수(2<=N<=10)
    - 비용 행렬(성분: 1_000_000 이하)
 * @output
 * @time_complex
 * @perf 15572kb / 244ms
 */
public class 외판원_순회_2 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static int[][] dists;
    private static int[] route;
    private static int sum;
    private static long ans = Long.MAX_VALUE;
    private static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        dists = new int[N][N];
        route = new int[N];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                dists[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            route[i] = i;
        }

        do {
            ans = Math.min(ans, cal());
        } while(np());

        System.out.println(ans);

        br.close();
    }

    private static long cal() {
        sum = 0;

        for (int i = 0; i < N-1; i++) {
            if (dists[route[i]][route[i+1]] == 0) return Long.MAX_VALUE;
            sum += dists[route[i]][route[i+1]];
        }

        if (dists[route[route.length-1]][route[0]] == 0) return Long.MAX_VALUE;
        sum += dists[route[route.length-1]][route[0]];

        return sum;
    }

    private static boolean np() {
        int inflectIdx = route.length-1;
        while (inflectIdx > 0 && route[inflectIdx-1] > route[inflectIdx]) inflectIdx--;

        if (inflectIdx == 0) return false;

        int swapIdx = route.length-1;
        while (swapIdx > inflectIdx-1 && route[inflectIdx-1] > route[swapIdx]) swapIdx--;

        swap(inflectIdx-1, swapIdx);

        reverse(inflectIdx, route.length-1);

        return true;
    }

    private static void swap(int srcIdx, int destIdx) {
        int tmp = route[srcIdx];
        route[srcIdx] = route[destIdx];
        route[destIdx] = tmp;
    }

    private static void reverse(int left, int right) {
        while (left < right) {
            swap(left++, right--);
        }
    }
}
