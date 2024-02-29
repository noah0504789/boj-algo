package org.example.simulation;

import java.io.*;
import java.util.*;

/**
     [초기셋팅]
     1. swan
     - swan arr에 저장
     - ground 변환

     2. ground
     - 주변 ground와 union-find로 서로소 집합을 만든다.
     - 주변에 ice가 있는 땅을 큐에 집어넣는다. (녹이기 위해)

     [빙하 녹이기]
     - 서로 만날 수 있는지 확인 (서로소 집합을 활용하여 사이클 체크)
     - ice들을 꺼내서 하나씩 녹이고, 나이를 적는다.
 */

public class 백조의_호수 {
    // 지구온난화
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final Queue<int[]> queue = new ArrayDeque<>();
    private static final int[][] DIRS = {{0,1},{0,-1},{1,0},{-1,0}};
    private static final char GROUND = '.';
    private static final char SWAN = 'L';
    private static final int ICE_INIT_NUM = -1;
    private static final int GROUND_NUM = 0;
    private static StringTokenizer st;
    private static UnionFind unionFind;
    private static int[][] board;
    private static int[] swan = new int[2];
    private static int R, C, swanIdx;
    private static boolean isSurroundedByIce;
    static class UnionFind {
        final int size;
        int[] root, rank;

        public UnionFind(int size) {
            this.size = size;
            this.root = new int[size+1];
            this.rank = new int[size+1];
            makeSet();
        }

        private void makeSet() {
            for (int i = 1; i <= size; i++) {
                root[i] = i;
                rank[i] = 1;
            }
        }

        private int find(int x) {
            if (x == root[x]) return x;

            return root[x] = find(root[x]);
        }

        public boolean union(int x, int y) {
            int rootX = find(x), rootY = find(y);

            if (rootX == rootY) return false;

            if (rank[rootX] > rank[rootY]) root[rootY] = root[rootX];
            else root[rootX] = root[rootY];

            if (rank[rootX] == rank[rootY]) rank[rootY]++;

            return true;
        }

        public boolean isCycle(int num1, int num2) {
            return find(num1) == find(num2);
        }
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new int[R][C];
        unionFind = new UnionFind(R*C);

        for (int r = 0; r < R; r++) {
            String line = br.readLine();
            for (int c = 0; c < C; c++) {
                char curC = line.charAt(c);
                if (curC == GROUND) {
                    board[r][c] = GROUND_NUM;
                } else if (curC == SWAN) {
                    swan[swanIdx++] = pointToNum(r, c);
                    board[r][c] = GROUND_NUM;
                } else {
                    board[r][c] = ICE_INIT_NUM;
                }
            }
        }

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (board[r][c] == ICE_INIT_NUM) continue;

                isSurroundedByIce = false;

                for (int[] dir : DIRS) {
                    int nr = r+dir[0], nc = c+dir[1];
                    if (nr<0 || nr>=R || nc<0 || nc>=C) continue;
                    if (board[nr][nc] == GROUND_NUM) unionFind.union(pointToNum(r,c), pointToNum(nr,nc));
                    else isSurroundedByIce = true;
                }

                if (isSurroundedByIce) queue.add(new int[] {r, c, GROUND_NUM});
            }
        }

        if (canMeet()) {
            System.out.println(0);
            br.close();
            return;
        }

        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int r=point[0], c=point[1], age=point[2];

            for (int[] dir : DIRS) {
                int nr = r+dir[0], nc = c+dir[1];
                if (nr<0 || nr>=R || nc<0 || nc>=C) continue;
                unionFind.union(pointToNum(r,c), pointToNum(nr,nc));

                if (canMeet()) {
                    System.out.println(board[nr][nc]);
                    br.close();
                    return;
                }

                if (board[nr][nc] != ICE_INIT_NUM) continue;
                board[nr][nc] = age+1;
                queue.add(new int[]{nr, nc, age+1});
            }
        }
    }

    private static boolean canMeet() {
        return unionFind.isCycle(swan[0], swan[1]);
    }

    private static int pointToNum(int r, int c) {
        return r*C+c;
    }
}
