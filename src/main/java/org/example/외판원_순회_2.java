package org.example;

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
 * @perf
 */
public class 외판원_순회_2 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final PriorityQueue<Edge> pq = new PriorityQueue<>();
    private static final int INF = Integer.MAX_VALUE;
    private static final int NON_EXIST = 0;
    private static StringTokenizer st;
    private static int[][] adjMatrix;
    private static boolean[] treeVertices;
    private static long ans = Long.MAX_VALUE;
    private static int N;

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

//    static class UnionFind {
//        final int size;
//        int[] root;
//
//        public UnionFind(int size) {
//            this.size = size;
//            this.root = new int[size];
//            makeSet();
//        }
//
//        private void makeSet() {
//            for (int i = 0; i < size; i++) {
//                this.root[i] = i;
//            }
//        }
//
//        private int find(int x) {
//            if (x == root[x]) return x;
//
//            return root[x] = find(root[x]);
//        }
//
//        public boolean union(int x, int y) {
//            int rootX = find(x), rootY = find(y);
//
//            if (rootX == rootY) return false;
//
//            root[rootX] = root[rootY];
//
//            return true;
//        }
//    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        adjMatrix = new int[N][N];
//        minDists = new int[N];
        treeVertices = new boolean[N];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                adjMatrix[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        for (int start = 0; start < N; start++) prim(start);

        System.out.println(ans);

        br.close();
    }

    private static void prim(int start) {
        Arrays.fill(treeVertices, false);
        pq.offer(new Edge(start, NON_EXIST));
        long result = 0;
        int c = 0, end = 0;

        while (!pq.isEmpty()) {
            Edge curE = pq.poll();
            int source = curE.dest, toSourceD = curE.dist;
            if (treeVertices[source]) continue;
            treeVertices[source] = true;

            result += toSourceD;
            if (++c==N) {
                end = source;
                break;
            }

            for (int dest = 0; dest < N; dest++) {
                if (adjMatrix[source][dest]==0) continue;

                pq.offer(new Edge(dest, adjMatrix[source][dest]));
            }
        }

        ans = Math.min(ans, result+adjMatrix[end][start]);
    }
}
