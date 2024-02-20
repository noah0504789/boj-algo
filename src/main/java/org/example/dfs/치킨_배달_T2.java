package org.example.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author noah kim
 * @date 2024-02-20
 * @link https://www.acmicpc.net/problem/15686
 * @requirement M개의 치킨을 선택할 때, 가장 최소거리의 치킨거리를 구하라.
 * @keyword
    [치킨거리]
    - 도시의 모든 집과 치킨집의 거리의 합
 * @input
 * @output
 * @time_complex
 * @perf 17284kb / 232ms
 */
public class 치킨_배달_T2 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int HOUSE_SIGNAL = 1;
    private static final int CHICKEN_SIGNAL = 2;
    private static StringTokenizer st;
    private static int[][] city;
    private static int[] selected;
    private static List<int[]> chickens = new ArrayList<>(), customers = new ArrayList<>();
    private static int N, M, ans = 100000;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        city = new int[N][N];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                int val = Integer.parseInt(st.nextToken());

                city[r][c] = val;

                if (val == HOUSE_SIGNAL) customers.add(new int[] {r, c});
                else if (val == CHICKEN_SIGNAL) chickens.add(new int[] {r, c});
            }
        }

        selected = new int[M];

        dfs(0, 0);

        System.out.println(ans);

        br.close();
    }

    private static void dfs(int start, int depth) {
        if (depth == M) {
            int minTotDist = 0;
            for (int[] customer : customers) {
                int minDist = 101;
                for (int idx : selected) {
                    minDist = Math.min(minDist, getDist(customer, chickens.get(idx)));
                }

                minTotDist += minDist;
            }

            ans = Math.min(ans, minTotDist);
            return;
        }

        for (int i = start; i < chickens.size(); i++) {
            selected[depth] = i;
            dfs(i+1, depth+1);
        }
    }

    private static int getDist(int[] customer, int[] chicken) {
        int ctr = customer[0], ctc = customer[1];
        int chr = chicken[0], chc = chicken[1];

        return Math.abs(ctr-chr) + Math.abs(ctc-chc);
    }
}
