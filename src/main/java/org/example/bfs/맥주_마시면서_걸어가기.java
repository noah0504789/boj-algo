package org.example.bfs;

import java.io.*;
import java.util.*;

/**
 * @author noah kim
 * @date 2024-03-28
 * @link https://www.acmicpc.net/problem/9205
 * @requirement 목적지에 도착할 수 있는지 여부를 출력하라.
 * @keyword
    [map]
    [이동]
    - 단위: 50m
    - 조건: 이동 전 맥주 마셔야 함

    [맥주]
    - capacity: 최대 20개

    [편의점]
    - 충전: 빈 병 갯수만큼
 * @input TC: input (<=50)
    - N: 편의점 갯수(0<=N<=100)
    - start
    - (편의점)*N
    - dest
 * @output
 * @time_complex
 * @perf
 */
public class 맥주_마시면서_걸어가기 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final Queue<Point> queue = new ArrayDeque<>();
    private static final StringBuilder sb = new StringBuilder();
    private static final String POSSIBLE = "happy";
    private static final String IMPOSSIBLE = "sad";
    private static final int cap = 20;
    private static final int perDist = 50;
    private static final int dist = cap*perDist;
    private static Point[] nodes;
    private static BitSet visBit = new BitSet();
    private static int tc, n;

    static class Point {
        final int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public long getManhattenDist(Point o) {
            return Math.abs(o.x-x)+Math.abs(o.y-y);
        }
    }

    public static void main(String[] args) throws IOException {
        tc = Integer.parseInt(br.readLine());

        for (int t = 0; t < tc; t++) {
            n = Integer.parseInt(br.readLine());

            nodes = new Point[n+2];

            for (int i = 0; i < n+2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine().trim());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                nodes[i] = new Point(x, y);
            }

            if (bfs()) sb.append(POSSIBLE);
            else sb.append(IMPOSSIBLE);

            sb.append("\n");
        }

        System.out.println(sb.toString().trim());
    }

    private static boolean bfs() {
        Point src = nodes[0], dest = nodes[n+1];
        visBit.clear();
        visBit.set(0);

        queue.clear();
        queue.offer(src);

        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            if (cur.getManhattenDist(dest) <= dist) return true;

            for (int i = 1; i < n+1; i++) {
                if (visBit.get(i)) continue;

                if (cur.getManhattenDist(nodes[i]) > dist) continue;
                visBit.set(i);
                queue.offer(nodes[i]);
            }
        }

        return false;
    }
}
