package org.example.simulation;

import java.io.*;

/**
 * @author noah kim
 * @date 2024/03/02
 * @link https://www.acmicpc.net/problem/15683
 * @requirement
 * @summary
 * @input
 * @output
 * @time_complex
 * @perf 16396kb / 156ms
 */
public class 감시 {
    private static final int UP = 0;
    private static final int RIGHT = 1;
    private static final int DOWN = 2;
    private static final int LEFT = 3;
    private static final int EMPTY = 0;
    private static final int UNDIR = 1;
    private static final int BIDIR_STRAIGHT = 2;
    private static final int BIDIR_ANGLE = 3;
    private static final int TRIDIR = 4;
    private static final int QUADIR = 5;
    private static final int WALL = 6;
    private static final int[] dr = {-1,0,1,0};
    private static final int[] dc = {0,1,0,-1};
    private static final int[][] UNDIR_DIR = {{UP}, {RIGHT}, {DOWN}, {LEFT}};
    private static final int[][] BIDIR_STRAIGHT_DIR = {{UP, DOWN}, {RIGHT, LEFT}};
    private static final int[][] BIDIR_ANGLE_DIR = {{UP, RIGHT}, {RIGHT, DOWN}, {DOWN, LEFT}, {LEFT, UP}};
    private static final int[][] TRIDIR_DIR = {{UP, RIGHT, DOWN}, {RIGHT, DOWN, LEFT}, {DOWN, LEFT, UP}, {LEFT, UP, RIGHT}};
    private static final int[][] QUADIR_DIR = {{UP, RIGHT, DOWN, LEFT}};
    private static boolean flag;
    private static int N, M, cctvIdx, cctvCnt, wallCnt, ans = 64;
    private static int[][] board, surveillanceCnt, curDirs, cctvs;

    public static void main(String[] args) throws Exception {
        N = readInt();
        M = readInt();
        
        board = new int[N][M];
        surveillanceCnt = new int[N][M];
        cctvs = new int[8][2];

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                board[r][c] = readInt();

                if (board[r][c]>=UNDIR && board[r][c]<=QUADIR) {
                    cctvs[cctvIdx][0] = r;
                    cctvs[cctvIdx++][1] = c;
                } else if (board[r][c] == WALL) {
                    wallCnt++;
                }
            }
        }
        
        cctvCnt = cctvIdx;
        
        dfs(0, 0);

        System.out.println(ans);
    }

    private static void dfs(int depth, int mCnt) {
        if (depth == cctvIdx) {
            if (N*M-wallCnt-cctvCnt-mCnt == 0) {
                ans = 0;
                flag = true;
            } else {
                ans = Math.min(ans, N*M-wallCnt-cctvCnt-mCnt);
            }
            return;
        }

        int r = cctvs[depth][0], c = cctvs[depth][1];
        int type = board[r][c];

        if (type == UNDIR) curDirs = UNDIR_DIR;
        else if (type == BIDIR_STRAIGHT) curDirs = BIDIR_STRAIGHT_DIR;
        else if (type == BIDIR_ANGLE) curDirs = BIDIR_ANGLE_DIR;
        else if (type == TRIDIR) curDirs = TRIDIR_DIR;
        else if (type == QUADIR) curDirs = QUADIR_DIR;

        for (int[] dirs : curDirs) {
            if (flag) break;
            int cnt = 0;

            for (int d : dirs) cnt += monitor(r, c, d);
            dfs(depth+1, mCnt+cnt);
            for (int d : dirs) rollback(r, c, d);
        }
    }

    private static int monitor(int r, int c, int d) {
        int nr = r, nc = c, cnt = 0;
        while (true) {
            nr += dr[d];
            nc += dc[d];
            if (nr < 0 || nr >= N || nc < 0 || nc >= M) break;

            if (board[nr][nc] == WALL) break;
            if (board[nr][nc]>=UNDIR && board[nr][nc]<=QUADIR) continue;

            surveillanceCnt[nr][nc]++;
            if (surveillanceCnt[nr][nc] == 1) cnt++;
        }

        return cnt;
    }

    private static void rollback(int r, int c, int d) {
        int nr = r, nc = c;
        while (true) {
            nr += dr[d];
            nc += dc[d];
            if (nr < 0 || nr >= N || nc < 0 || nc >= M) break;

            if (board[nr][nc] == WALL) break;
            if (board[nr][nc]>=UNDIR && board[nr][nc]<=QUADIR) continue;

            surveillanceCnt[nr][nc]--;
        }
    }

    private static int readInt() throws IOException {
        int c, n = System.in.read() & (1<<4)-1;
        while ((c = System.in.read()) > (1<<5)) {
            n = (n<<1) + (n<<3) + c&(1<<4)-1;
        }
        return n;
    }
}
