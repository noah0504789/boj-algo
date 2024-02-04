package org.example.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author noah kim
 * @date 2024/02/04
 * @link https://www.acmicpc.net/problem/15686
 * @reference https://steady-coding.tistory.com/23
 * @keyword_solution
    Requirements: 최대 M개의 치킨집만 운영하였을 떄, 도시의 최소 치킨거리 값을 구하라.

    [도시]
    - 크기 : N * N
    - 칸 : 빈칸(0), 집(1), 치킨집(2)

    [좌표와의 거리]
    - |r1-r2| + |c1-c2|

    [치킨 거리]
    - 집과 가장 가까운 치킨집 사이의 거리
    - 도시의 치킨거리 : 도시에 있는 집들의 치킨거리 합
 * @input
    - N(너비.2<=N<=50), M(운영할 치킨집.1<=M<=13)
    - 도시 보드
 * @output
    - 도시의 최소 치킨거리 값
 * @time_complex
 * @perf
 */
public class 치킨_배달 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static List<Point> person = new ArrayList<>(), chicken = new ArrayList<>();
    private static StringTokenizer st;
    private static int[][] city;
    private static boolean[] open;
    private static int N, M, ans = Integer.MAX_VALUE;
    static class Point {
        private final int r, c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        int getChichenDist(Point o) {
            return Math.abs(this.r - o.r) + Math.abs(this.c - o.c);
        }
    }


    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        city = new int[N][N];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                city[r][c] = Integer.parseInt(st.nextToken());

                if (city[r][c] == 1) {
                    person.add(new Point(r, c));
                } else if (city[r][c] == 2) {
                    chicken.add(new Point(r, c));
                }
            }
        }

        open = new boolean[chicken.size()];

        dfs(0, 0);

        System.out.println(ans);

        br.close();
    }

    private static void dfs(int start, int cnt) {
        if (cnt == M) {
            int res = 0;
            for (int i = 0; i < person.size(); i++) {
                int temp = Integer.MAX_VALUE;
                for (int j = 0; j < chicken.size(); j++) {
                    if (open[j]) {
                        Point pPoint = person.get(i);
                        Point cPoint = chicken.get(j);
                        temp = Math.min(temp, pPoint.getChichenDist(cPoint));
                    }
                }
                res += temp;
            }

            ans = Math.min(ans, res);
            return;
        }

        for (int i = start; i < chicken.size(); i++) {
            open[i] = true;
            dfs(i+1,cnt+1);
            open[i] = false;
        }
    }
}
