package org.example.graph;

import java.util.*;

public class 효율적인_해킹 {
    private static final Scanner sc = new Scanner(System.in);
    private static final StringBuilder sb = new StringBuilder();
    private static final Queue<Integer> queue = new ArrayDeque<>();
    private static Node[] computers;
    private static BitSet visited;
    private static int N, M, maxCnt;

    public static void main(String... args) {
        N = sc.nextInt();
        M = sc.nextInt();

        computers = new Node[N];
        visited = new BitSet(N);

        for (int i = 0; i < M; i++) {
            int src = sc.nextInt()-1, dest = sc.nextInt()-1;

            computers[dest] = new Node(src, computers[dest]);
        }

        maxCnt = 0;
        List<Integer> result = new ArrayList<>();

        for (int start = 0; start < N; start++) {
            int cnt = bfs(start);
            if (cnt > maxCnt) {
                maxCnt = cnt;
                result.clear();
                result.add(start+1);
            } else if (cnt == maxCnt) {
                result.add(start+1);
            }
        }

        for (int start : result) sb.append(start).append(" ");

        System.out.println(sb.toString().trim());
    }

    private static int bfs(int start) {
        queue.clear();
        visited.clear();

        queue.offer(start);
        visited.set(start);

        int cnt = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (Node neigh = computers[cur]; neigh != null; neigh = neigh.next) {
                if (visited.get(neigh.dest)) continue;

                visited.set(neigh.dest);
                cnt++;

                queue.offer(neigh.dest);
            }
        }

        return cnt;
    }

    static class Node {
        final int dest;
        final Node next;

        Node(int dest, Node next) {
            this.dest = dest;
            this.next = next;
        }
    }
}
