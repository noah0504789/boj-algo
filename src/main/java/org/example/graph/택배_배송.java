package org.example.graph;

import java.io.*;
import java.util.*;

/**
 * @author noah kim
 * @date 2024/02/10
 * @link https://www.acmicpc.net/problem/5972
 * @requirement 가장 적은 소를 만나며 갈 수 있는 경로로 이동할 때, 만나는 소의 수를 출력하라.
 * @summary
    [초기 위치]
    - deliver: 1
    - receiver: N

    [Node]
    - 범위: 1<=N<=50_000
    - 서로 연결될 수 있음

    [Edge]
    - 양방향

    [Weight]
    - 범위: 1<=C<=1_000
 * @input
    - N: Node 수, M: Edge 수
    - [Edge info] src dest weight
 * @output
 * @time_complex
 * @perf
 */
public class 택배_배송 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final Map<Integer, Node> nodeInfo = new HashMap<>();;
    private static final Set<Integer> visited = new HashSet<>();
    private static final Map<Integer, Integer> minWeights = new HashMap<>();;
    private static final PriorityQueue<Edge> neighborEdges = new PriorityQueue<>();
    private static final int INF = Integer.MAX_VALUE;
    private static final int SRC_NODE = 1;
    private static StringTokenizer st;
    private static int N, M, src, dest, weight;

    static final class Node {
        final int idx;
        final Map<Node, Integer> neighbors;

        public Node(int idx) {
            this.idx = idx;
            this.neighbors = new HashMap<>();
        }

        public void addNeighbors(Node to, int weight) {
            this.neighbors.put(to, weight);
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
            return Integer.compare(this.weight, o.weight);
        }
    }

    private static void init() throws IOException {
        for (int startIdx = 1; startIdx <= N; startIdx++) {
            nodeInfo.put(startIdx, new Node(startIdx));
        }

        for (int edge = 0; edge < M; edge++) {
            st = new StringTokenizer(br.readLine());
            src = Integer.parseInt(st.nextToken());
            dest = Integer.parseInt(st.nextToken());
            weight = Integer.parseInt(st.nextToken());

            Node srcNode = nodeInfo.get(src);
            Node destNode = nodeInfo.get(dest);
            srcNode.addNeighbors(destNode, weight);
            destNode.addNeighbors(srcNode, weight);
        }

        for (var entry : nodeInfo.entrySet()) {
            int nodeIdx = entry.getKey();
            minWeights.put(nodeIdx, INF);
        }
    }

    static final void dijkstra() {
        neighborEdges.offer(new Edge(nodeInfo.get(SRC_NODE), 0));
        minWeights.put(SRC_NODE, 0);

        while (!neighborEdges.isEmpty()) {
            Edge toNeigh = neighborEdges.poll();
            Node dest = toNeigh.dest;

            int destIdx = dest.idx;

            if (visited.contains(destIdx)) continue;
            visited.add(destIdx);

            int minWeightOfDest = minWeights.get(destIdx);
            for (var entry : dest.neighbors.entrySet()) {
                Node neigh = entry.getKey();
                int neighIdx = neigh.idx;

                int weight = entry.getValue();
                int newWeight = minWeightOfDest + weight;
                int minWeightOfNeigh = minWeights.get(neighIdx);

                if (newWeight >= minWeightOfNeigh) continue;

                neighborEdges.offer(new Edge(neigh, newWeight));
                minWeights.put(neighIdx, newWeight);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        init();

        dijkstra();

        System.out.println(minWeights.get(N));

        br.close();
    }
}
