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
 * @link
 * @requirement
 * @summary
 * @input
 * @output
 * @time_complex
 * @perf
 */
public class 택배_배송_T2 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final List<List<Node>> graph = new ArrayList<>();
    private static final Set<Integer> visited = new HashSet<>();
    private static final PriorityQueue<Node> neighbors = new PriorityQueue<>();
    private static final int INF = Integer.MAX_VALUE;
    private static final int SRC_NODE = 1;
    private static StringTokenizer st;
    private static int[] minDists;
    private static int N, M, src, dest, weight;

    static final class Node implements Comparable<Node> {
        final int dest, weight;

        public Node(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    private static void dijkstra(int start) {
        minDists[start] = 0;
        neighbors.offer(new Node(start, 0));

        while (!neighbors.isEmpty()) {
            Node curN = neighbors.poll();

            if (curN.weight > minDists[curN.dest]) continue;
            if (visited.contains(curN.dest)) continue;
            visited.add(curN.dest);

            for (Node neigh : graph.get(curN.dest)) {
                int minDistOfNeigh = minDists[neigh.dest];
                int newWeight = neigh.weight + minDists[curN.dest];

                if (newWeight >= minDistOfNeigh) continue;

                minDists[neigh.dest] = newWeight;
                neighbors.offer(new Node(neigh.dest, newWeight));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        minDists = new int[N+1];
        Arrays.fill(minDists, INF);

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            src = Integer.parseInt(st.nextToken());
            dest = Integer.parseInt(st.nextToken());
            weight = Integer.parseInt(st.nextToken());

            graph.get(src).add(new Node(dest, weight));
            graph.get(dest).add(new Node(src, weight));
        }

        dijkstra(SRC_NODE);

        System.out.println(minDists[N]);

        br.close();
    }
}
