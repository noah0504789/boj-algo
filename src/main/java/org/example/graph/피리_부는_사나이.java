package org.example.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * @author noah kim
 * @date 2024/02/11
 * @link https://www.acmicpc.net/problem/16724
 * @reference chatgpt
 * @requirement 모든 사람이 SAFE ZONE에 들어가도록 하는 최소개의 설치 수를 출력하라.
 * @summary
    [피리]
    - 피리를 불 경우, 제공된 방향으로 움직입니다.
 * @input
    - N: 행의 수(1<=N<=1_000), M: 열의 수(1<=M<=1_000)
    - 지도 정보
 * @output
 * @time_complex 67896kb / 508ms
 * @perf
 */
public class 피리_부는_사나이 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static UnionFind uf;
    private static char[][] board;
    private static int N, M;

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
            if (x == root[x]) return x;

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
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        uf = new UnionFind(N*M);

        for (int r = 0; r < N; r++) {
            String line = br.readLine();
            for (int c = 0; c < M; c++) {
                board[r][c] = line.charAt(c);
            }
        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                int curIdx = r * M + c;
                int nextIdx = getNextIdx(r, c);
                uf.union(curIdx, nextIdx);
            }
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < N*M; i++) {
            set.add(uf.find(i));
        }

        System.out.println(set.size());
    }

    private static int getNextIdx(int r, int c) {
        char dir = board[r][c];

        switch (dir) {
            case 'U': return (r-1)*M + c;
            case 'D': return (r+1)*M + c;
            case 'L': return r*M + (c-1);
            case 'R': return r*M + (c+1);
            default: return 0;
        }
    }
}
