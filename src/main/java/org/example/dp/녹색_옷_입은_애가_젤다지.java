package org.example.dp;

import java.io.IOException;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author noah kim
 * @date 2024-03-26
 * @link https://www.acmicpc.net/problem/4485
 * @requirement 최소 비용으로 오른쪽 하단까지 이동할 때의 비용을 출력하라.
 * @keyword
 * @input
    - N: board edge (2<=N<=125)(0일 경우, 프로그램 종료)
    - board info
 * @output
 * @time_complex
 * @perf
 */
public class 녹색_옷_입은_애가_젤다지 {
    private static final PriorityQueue<Edge> edges = new PriorityQueue<>();
    private static final StringBuilder sb = new StringBuilder();
    private static final String ansMsg = "Problem %d: %d";
    private static final int[][] DIRS = {{0,1}, {1,0}, {-1,0}, {0,-1}};
    private static int[][] map, minDists;
    private static boolean[][] visited;
    private static int N;

    private static class Edge implements Comparable<Edge> {
        final int r, c, dist;

        public Edge(int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.dist, o.dist);
        }
    }

    public static void main(String... args) throws IOException {
        int tc = 1;
        while ((N = readInt()) > 1) {
            visited = new boolean[N][N];
            minDists = new int[N][N];
            map = new int[N][N];

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    map[r][c] = readInt();
                }
            }

            dijkstra(0, 0);

            sb.append(String.format(ansMsg, tc++, minDists[N-1][N-1]));
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void dijkstra(int r, int c) {
        for (int i = 0; i < N; i++) Arrays.fill(visited[i], false);
        for (int i = 0; i < N; i++) Arrays.fill(minDists[i], Integer.MAX_VALUE);

        minDists[r][c] = map[r][c];
        edges.offer(new Edge(r, c, minDists[r][c]));

        while (!edges.isEmpty()) {
            Edge curE = edges.poll();
            int sr = curE.r, sc = curE.c, toStopoverD = curE.dist;

            if (toStopoverD > minDists[sr][sc]) continue;
            if (visited[sr][sc]) continue;
            visited[sr][sc] = true;

            for (int[] dir : DIRS) {
                int nr = sr + dir[0], nc = sc + dir[1];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;

                int newD = toStopoverD + map[nr][nc];
                if (newD >= minDists[nr][nc]) continue;

                minDists[nr][nc] = newD;
                edges.offer(new Edge(nr, nc, newD));
            }
        }
    }

    public static int readInt() throws IOException {
        int c, n = System.in.read() & (1<<4)-1;
        while ((c = System.in.read()) > (1<<5)) {
            n = (n<<1) + (n<<3) + (c&(1<<4)-1);
        }
        return n;
    }
}
