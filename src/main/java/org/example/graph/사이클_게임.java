package org.example.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author noah kim
 * @date 2024/02/11
 * @link https://www.acmicpc.net/problem/20040
 * @requirement 두 사람이 번갈아가며 그래프를 그릴 떄, 최소 몇번을 그려야 사이클이 발생하는지 출력하라. (사이클이 발생하지 않는다면 0)
 * @summary
 * @input
    - N: 점의 수(3<=N<=500_000), M: 진행된 차례 수(3<=N<=1_000_000)
    - 선분 정보: 시작 - 끝
 * @output
 * @time_complex O(MlogN)
 * @perf 120956kb / 552ms
 */
public class 사이클_게임 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static int N, M, src, dest, ans;

    static final class UnionFind {
        final int[] root;
        final int[] height;

        public UnionFind(int size) {
            this.root = new int[size];
            this.height = new int[size];
            for (int i = 0; i < size; i++) {
                root[i] = i;
                height[i] = 1;
            }
        }

        public int find(int x) {
            if (root[x] == x) return x;

            return root[x] = find(root[x]);
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY) return;

            if (height[rootX] > height[rootY]) root[rootY] = rootX;
            else root[rootX] = rootY;

            if (height[rootX] == height[rootY]) height[rootY]++;
        }

        public boolean isCycle(int x, int y) {
            return find(x) == find(y);
        }
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        UnionFind unionFind = new UnionFind(N);

        for (int turn = 1; turn <= M; turn++) {
            st = new StringTokenizer(br.readLine());
            src = Integer.parseInt(st.nextToken());
            dest = Integer.parseInt(st.nextToken());

            if (unionFind.isCycle(src, dest)) {
                ans = turn;
                break;
            }

            unionFind.union(src, dest);
        }

        System.out.println(ans);

        br.close();
    }
}
