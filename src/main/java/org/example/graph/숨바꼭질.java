package org.example.graph;

import java.util.*;

public class 숨바꼭질 {
    private static final Scanner sc = new Scanner(System.in);
    private static Queue<Data> queue;
    private static BitSet visited;
    private static Node[] places;
    private static int N, M, leastNum, maxDist, sameCnt;

    public static void main(String... args) {
        N = sc.nextInt();
        M = sc.nextInt();
        queue = new ArrayDeque<>();
        visited = new BitSet(N);
        places = new Node[N];

        for (int i = 0; i < M; i++) {
            int src = sc.nextInt()-1, dest = sc.nextInt()-1;
            places[src] = new Node(dest, places[src]);
            places[dest] = new Node(src, places[dest]);
        }

        bfs(0);

        System.out.print((leastNum+1) + " " + maxDist + " " + sameCnt);
    }

    private static void bfs(int start) {
        queue.offer(new Data(start, 0));
        visited.set(start);

        maxDist = 0;
        sameCnt = 0;
        leastNum = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            Data cur = queue.poll();
            int curPos = cur.dest, curDist = cur.dist;
            if (curDist > maxDist) {
                maxDist = curDist;
                sameCnt = 1;
                leastNum = curPos;
            } else if (curDist == maxDist) {
                sameCnt++;
                if (curPos < leastNum) leastNum = curPos;
            }

            for (Node neigh = places[curPos]; neigh != null; neigh = neigh.next) {
                int neighPos = neigh.dest;

                if (visited.get(neighPos)) continue;
                visited.set(neighPos);

                queue.offer(new Data(neighPos, curDist+1));
            }
        }

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
