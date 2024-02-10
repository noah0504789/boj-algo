package org.example.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * @author noah kim
 * @date 2024/02/10
 * @link https://www.acmicpc.net/problem/11403
 * @requirement 방향 그래프의 인접 행렬이 주어질 떄, 모든 정점에 대하여 탐색이 가능한지 출력하라.
 * @summary
 * @input
 * @output
 * @time_complex
 * @perf 21816kb / 348ms
 */
public class 경로_찾기 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int INF = Integer.MAX_VALUE;
    private static final int PATH_NON_EXIST = 0;
    private static StringTokenizer st;
    private static StringBuffer sb = new StringBuffer();
    private static Map<Integer, Node> nodes = new HashMap<>();
    private static Set<Integer> visited;
    private static PriorityQueue<Edge> neighborEdges;
    private static int N;

    static final class Node {
        private final int idx;
        private final Map<Node, Integer> neighbors;

        public Node(int idx) {
            this.idx = idx;
            this.neighbors = new HashMap<>();
        }

        public void addNeighbor(Node to, int dist) {
            this.neighbors.put(to, dist);
        }
    }

    static final class Edge implements Comparable<Edge> {
        final Node dest;
        final int weight;

        public Edge(Node dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    private static void init(Map<Integer, Integer> minWeights) {
        visited = new HashSet<>();
        neighborEdges = new PriorityQueue<>();

        for (var entry : nodes.entrySet()) {
            int idx = entry.getKey();
            minWeights.put(idx, INF);
        }
    }

    static Map<Integer, Integer> dijkstra(int startIdx) {
        Map<Integer, Integer> minWeights = new HashMap<>();
        init(minWeights);

        neighborEdges.offer(new Edge(nodes.get(startIdx), PATH_NON_EXIST));

        while (!neighborEdges.isEmpty()) {
            Edge toNeigh = neighborEdges.poll();
            int weightOfDest = toNeigh.weight;
            Node dest = toNeigh.dest;

            int destIdx = dest.idx;
            if (visited.contains(destIdx)) continue;

            visited.add(destIdx);

            int minWeightOfDest = minWeights.get(destIdx);
            if (minWeightOfDest < weightOfDest) continue;

            for (var entry : dest.neighbors.entrySet()) {
                Node neigh = entry.getKey();
                int neighIdx = neigh.idx;
                if (minWeights.get(neighIdx) < INF) continue;

                int weightOfNeigh = entry.getValue();
                int minDistOfNeigh = minWeights.get(neighIdx);
                int newWeight = weightOfDest+weightOfNeigh;

                if (newWeight >= minDistOfNeigh) continue;

                neighborEdges.offer(new Edge(nodes.get(neighIdx), newWeight));
                minWeights.put(neighIdx, newWeight);
            }
        }

        return minWeights;
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        for (int startIdx = 0; startIdx < N; startIdx++) {
            nodes.put(startIdx, new Node(startIdx));
        }

        for (int startIdx = 0; startIdx < N; startIdx++) {
            st = new StringTokenizer(br.readLine());
            Node start = nodes.get(startIdx);
            for (int endIdx = 0; endIdx < N; endIdx++) {
                int isPath = Integer.parseInt(st.nextToken());
                if (isPath == 1) start.addNeighbor(nodes.get(endIdx), 1);
            }
        }

        for (int startIdx = 0; startIdx < N; startIdx++) {
            Map<Integer, Integer> minWeights = dijkstra(startIdx);
            minWeights.entrySet().stream().sorted(Comparator.comparingInt(Map.Entry::getKey)).forEach(entry -> sb.append((entry.getValue() < Integer.MAX_VALUE  ? 1 : 0) + " "));
            sb.append("\n");
        }

        System.out.println(sb);

        br.close();
    }
}
