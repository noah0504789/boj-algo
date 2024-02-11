package org.example.graph;

import java.io.*;
import java.util.*;

/**
 * @author noah kim
 * @date 2024/02/11
 * @link https://www.acmicpc.net/problem/17396
 * @requirement 도착지로 이동하는데 걸리는 최소시간을 출력하라.
 * @summary
    [노드]
    - 접근 가능 여부 존재

    [도착지]
    - 노드 N

    [간선]
    - 양방향
 * @input
    - N: 노드 수(1<=N<=100_000), M: 간선 수(1<=M<=300_000)
    - 노드 접근가능 여부
    - 간선 정보: 시작, 끝, 소요시간(1<=T<=100_000)
 * @output
 * @time_complex 157308kb / 1224ms
 * @perf
 */
public class 백도어 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final PriorityQueue<Edge> neighborEdges = new PriorityQueue<>();
    private static final List<List<Edge>> graph = new ArrayList<>();
    private static final Set<Integer> visited = new HashSet<>();
    private static final long INF = Long.MAX_VALUE;
    private static final int SRC_NODE = 0;
    private static final int AVAILABLE_TO_MOVE = 0;
    private static final int UNAVAILABLE_TO_MOVE = 1;
    private static final int NOT_EXIST_PATH = -1;
    private static StringTokenizer st;
    private static long[] minTimes;
    private static int[] isAvailable;
    private static int N, M, src, dest, time;
    private static int DEST_NODE;
    static final class Edge implements Comparable<Edge> {
        final int dest;
        final long time;

        public Edge(int dest, long time) {
            this.dest = dest;
            this.time = time;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(this.time, o.time);
        }
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        DEST_NODE = N-1;

        isAvailable = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        isAvailable[DEST_NODE] = AVAILABLE_TO_MOVE;

        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            src = Integer.parseInt(st.nextToken());
            dest = Integer.parseInt(st.nextToken());
            time = Integer.parseInt(st.nextToken());

            if (isAvailable[src] == UNAVAILABLE_TO_MOVE || isAvailable[dest] == UNAVAILABLE_TO_MOVE) continue;

            graph.get(src).add(new Edge(dest, time));
            graph.get(dest).add(new Edge(src, time));
        }

        dijkstr(SRC_NODE);

        System.out.println(minTimes[DEST_NODE] == INF ? NOT_EXIST_PATH : minTimes[DEST_NODE]);
    }

    private static void dijkstr(int start) {
        minTimes = new long[N];
        Arrays.fill(minTimes, INF);

        minTimes[start] = 0;
        neighborEdges.offer(new Edge(start, 0));

        while (!neighborEdges.isEmpty()) {
            Edge toDest = neighborEdges.poll();
            int dest = toDest.dest;
            long timeToDest = toDest.time;
            long minTimeOfDest = minTimes[dest];

            if (timeToDest > minTimeOfDest) continue;
            if (visited.contains(dest)) continue;
            visited.add(dest);

            for (Edge toNeigh : graph.get(dest)) {
                long timeToNeigh = toNeigh.time;
                long newTime = minTimeOfDest + timeToNeigh;
                int destOfNeigh = toNeigh.dest;
                long minNeigh = minTimes[destOfNeigh];

                if (newTime >= minNeigh) continue;

                neighborEdges.offer(new Edge(destOfNeigh, newTime));
                minTimes[destOfNeigh] = newTime;
            }
        }
    }
}
