package org.example.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * @author noah kim
 * @date 2024/02/11
 * @link https://www.acmicpc.net/problem/1238
 * @requirement 각 마을의 거주자들이 X번 마을에서 파티를 한다. 파티를 마치고 집에 돌아갈 때, 총 이동거리가 가장 큰 사람의 거리를 출력하라.
 * @summary
    [마을]
    - 갯수: N개
    - 거주자: 각 1명

    [도로]
    - 단방향
    - 소요시간 존재
 * @input
    - N: 마을 수(1<=N<=1_000), M: 도로 수(1<=M<=10_000), X: 파티할 마을
    - 도로 정보: 시작지 도착지 소요시간
 * @output
 * @time_complex 257132kb / 860ms
 * @perf
 */
public class 파티 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final List<List<Edge>> graph = new ArrayList<>();
    private static final PriorityQueue<Edge> neighborEdges = new PriorityQueue<>();
    private static final Set<Integer> visited = new HashSet<>();
    private static final int INF = Integer.MAX_VALUE;
    private static StringTokenizer st;
    private static int[] minDists;
    private static int N, M, X, src, dest, dist;
    private static int ans;

    static final class Edge implements Comparable<Edge> {
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

    private static void dijkstra(int start) {
        minDists = new int[N+1];
        Arrays.fill(minDists, INF);
        visited.clear();

        minDists[start] = 0;
        neighborEdges.offer(new Edge(start, 0));

        while (!neighborEdges.isEmpty()) {
            Edge destE = neighborEdges.poll();
            int dest = destE.dest;
            int distToDest = destE.dist;
            int minDOfDest = minDists[dest];
            if (distToDest > minDOfDest) continue;

            if (visited.contains(dest)) continue;
            visited.add(dest);

            for (Edge neigh : graph.get(dest)) {
                int destToNeigh = neigh.dest;
                int distFromNeigh = neigh.dist;
                int newDist = minDOfDest + distFromNeigh;

                if (newDist >= minDists[destToNeigh]) continue;

                minDists[destToNeigh] = newDist;
                neighborEdges.offer(new Edge(destToNeigh, newDist));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            src = Integer.parseInt(st.nextToken());
            dest = Integer.parseInt(st.nextToken());
            dist = Integer.parseInt(st.nextToken());

            graph.get(src).add(new Edge(dest, dist));
        }

        for (int start = 1; start <= N; start++) {
            dijkstra(start);
            int outingDist = minDists[X];

            dijkstra(X);
            int homeDist = minDists[start];

            ans = Math.max(ans, outingDist + homeDist);
        }

        System.out.println(ans);

        br.close();
    }
}
