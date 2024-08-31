package org.example.graph;


import java.util.*;

public class 회장뽑기 {
    private static final Scanner sc = new Scanner(System.in);
    private static final StringBuffer sb = new StringBuffer();
    private static final int INF = Integer.MAX_VALUE;

    private static Node[] friends;
    private static Queue<Data> queue;
    private static List<Integer> candidates;
    private static int[] minDists;
    private static int N, minPos;

    public static void main(String... args) {
        N = sc.nextInt();

        friends = new Node[N];

        while (true) {
            int src = sc.nextInt(), dest = sc.nextInt();
            if (src == -1) break;

            src--;
            dest--;

            friends[src] = new Node(dest, friends[src]);
            friends[dest] = new Node(src, friends[dest]);
        }

        queue = new ArrayDeque<>();
        minDists = new int[N];

        minPos = N+1;
        candidates = new ArrayList<>();

        for (int start = 0; start < N; start++) {
            int point = bfs(start);
            if (minPos > point) {
                minPos = point;
                candidates.clear();
                candidates.add(start+1);
            } else if (minPos == point) {
                candidates.add(start+1);
            }
        }

        System.out.println(minPos + " " + candidates.size());
        for (int c : candidates) sb.append(c).append(" ");

        System.out.println(sb);
    }

    private static int bfs(int start) {
        Arrays.fill(minDists, INF);

        queue.offer(new Data(start, 0));
        minDists[start] = 0;

        while (!queue.isEmpty()) {
            Data cur = queue.poll();
            int dest = cur.dest, dist = cur.dist;

            for (Node neigh = friends[dest]; neigh != null; neigh = neigh.next) {
                int neighDest = neigh.dest;

                if (minDists[neighDest] < dist+1) continue;
                minDists[neighDest] = dist+1;

                queue.offer(new Data(neighDest, minDists[neighDest]));
            }
        }

        int point = 0;

        for (int i = 0; i < N; i++) {
            if (i == start) continue;

            if (point < minDists[i]) point = minDists[i];
        }

        return point;
    }

    static class Node {
        final int dest;
        final Node next;

        Node(int dest, Node next) {
            this.dest = dest;
            this.next = next;
        }
    }

    static class Data {
        final int dest, dist;

        Data(int dest, int dist) {
            this.dest = dest;
            this.dist = dist;
        }
    }
}
