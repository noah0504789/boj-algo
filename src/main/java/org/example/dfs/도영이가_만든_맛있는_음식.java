package org.example.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author noah kim
 * @date 2024/02/04
 * @link https://www.acmicpc.net/problem/2961
 * @keyword_solution
    Requirements: 신맛과 쓴맛의 차이가 가장 작은 요리를 만들고 그 요리의 신맛과 쓴맛의 차이를 출력하라.

    [음식]
    - 신맛: 모든 신맛의 곱
    - 쓴맛: 모든 쓴맛의 합
    - 재료: 하나 이상 사용
 * @input
    - N(재료개수.1<=N<=10)
    - 재료별 S(신맛지수),B(쓴맛지수) (1<=S,B<=1_000_000_000)
 * @output
    - 요리의 신맛과 쓴맛의 차이
 * @time_complex
 * @perf
 */
public class 도영이가_만든_맛있는_음식 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int SOUR_IDX = 0, BITTER_IDX = 1;
    private static StringTokenizer st;
    private static int[][] ingredients;
    private static int N, sour, bitter, ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        ingredients = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            sour = Integer.parseInt(st.nextToken());

            bitter = Integer.parseInt(st.nextToken());

            ingredients[i][SOUR_IDX] = sour;
            ingredients[i][BITTER_IDX] = bitter;
        }

        for (int height = 1; height <= N; height++) {
            dfs(0, 1, 0, height);
        }

        System.out.println(ans);

        br.close();
    }

    private static void dfs(int start, int preS, int preB, int height) {
        if (start == height) {
            ans = Math.min(ans, Math.abs(preS-preB));
            return;
        }

        for (int i = start; i < N; i++) {
            int curS = ingredients[i][SOUR_IDX];
            int curB = ingredients[i][BITTER_IDX];
            dfs(i+1, preS*curS, preB+curB, height);
        }
    }
}
