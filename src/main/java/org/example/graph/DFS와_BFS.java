package org.example.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.*;

/**
 * @author noah kim
 * @date 2024-02-16
 * @link https://www.acmicpc.net/problem/1260
 * @requirement
 * @keyword
 * @input
 * @output
 * @time_complex
 * @perf 20752kb / 292ms
 */
public class DFS와_BFS {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuffer sb = new StringBuffer();
    private static List<List<Integer>> graph = new ArrayList<>();
    private static StringTokenizer st;
    private static boolean[] visited;
    private static int N, M, V, src, dest;
    private static class Node {
        final int idx;
        final Node prev;

        public Node(int idx, Node prev) {
            this.idx = idx;
            this.prev = prev;
        }
    }

    public static void main(String... args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            src = Integer.parseInt(st.nextToken());
            dest = Integer.parseInt(st.nextToken());
            graph.get(src).add(dest);
            graph.get(dest).add(src);
        }

        for (List<Integer> l : graph) {
            Collections.sort(l);
        }

        visited = new boolean[N+1];

        dfs(V);

        sb.append("\n");

        bfs(V);

        System.out.println(sb);

        br.close();
    }

    private static void dfs(int cur) {
        visited[cur] = true;
        sb.append(cur + " ");

        for (int neigh : graph.get(cur)) {
            if (visited[neigh]) continue;
            visited[neigh] = true;
            dfs(neigh);
        }
    }

    private static void bfs(int start) {
        visited = new boolean[N+1];
        visited[start] = true;

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            sb.append(cur + " ");

            for (int neigh : graph.get(cur)) {
                if (visited[neigh]) continue;
                visited[neigh] = true;
                queue.offer(neigh);
            }
        }
    }

    private static void bfsPath(int destIdx) {
        Queue<Node> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N];

        queue.offer(new Node(V, null));
        visited[V] = true;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int curIdx = cur.idx;

            if (curIdx == destIdx) {
                StringBuffer path = new StringBuffer(" "+curIdx);
                for (Node prev = cur.prev; prev != null; prev = prev.prev) {
                    path.insert(0, " " + prev.idx);
                }
                System.out.printf("도착지 \'%d\'에 도달하는 경로: %s", destIdx, path.toString().trim());
                return;
            }

            for (int neighIdx : graph.get(curIdx)) {
                if (visited[neighIdx]) continue;
                visited[neighIdx] = true;
                queue.offer(new Node(neighIdx, cur));
            }
        }
    }
}
