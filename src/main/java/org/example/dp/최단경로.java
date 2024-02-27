package org.example.dp;

import java.io.*;
import java.util.*;

/**
 * @author noah kim
 * @date 2024-02-27
 * @link https://www.acmicpc.net/problem/1753
 * @requirement 방향그래프에서, 주어진 시작점에서 다른 모든 정점으로의 최단경로를 출력하라
 * @keyword
 * @input
    - V:정점 갯수 (1<=V<=20_000), E:간선 갯수 (1<=E<=300_000)
    - 시작점 정점
    - 간선 정보: src, dest, weight(자연수. 10이하)
 * @output
 * @time_complex
 * @perf 36400kb / 764ms
 */
public class 최단경로 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int INF = Integer.MAX_VALUE;
    private static final int NOT_EXIST = 0;
    private static PriorityQueue<Edge> pq = new PriorityQueue<>();
    private static List<List<Edge>> graph = new ArrayList<>();
    private static int[] minDist;
    private static int V, E, src, dest, dist, start;
    private static BitSet bitSet;

    static class Edge implements Comparable<Edge> {
        final int dest, dist;

        public Edge(int dest, int dist) {
            this.dest = dest;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.dist, o.dist);
        }
    }

    public static void main(String[] args) throws Exception {
        V = read();
        E = read();

        for (int i = 0; i <= V; i++) graph.add(new ArrayList<>());
        minDist = new int[V+1];
        bitSet = new BitSet(V+1);

        start = read();

        for (int i = 0; i < E; i++) {
            src = read();
            dest = read();
            dist = read();

            graph.get(src).add(new Edge(dest, dist));
        }

        dijkstra(start);

        for (int i = 1; i <= V; i++) {
            if (minDist[i] == INF) System.out.println("INF");
            else System.out.println(minDist[i]);
        }

        br.close();
    }

    private static void dijkstra(int start) {
        Arrays.fill(minDist, INF);
        minDist[start] = NOT_EXIST;
        pq.offer(new Edge(start, NOT_EXIST));

        while (!pq.isEmpty()) {
            Edge curE = pq.poll();
            int stopover = curE.dest, toStopOverD = curE.dist;

            if (bitSet.get(stopover)) continue;
            bitSet.set(stopover);

            int minStopOverD = minDist[stopover];
            if (toStopOverD > minStopOverD) continue;

            for (Edge neighE : graph.get(stopover)) {
                int dest = neighE.dest, toDistD = neighE.dist;
                int minDestD = minDist[dest];

                int newD = minStopOverD + toDistD;
                if (newD >= minDestD) continue;

                minDist[dest] = newD;
                pq.offer(new Edge(dest, newD));
            }
        }
    }

    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }
}
