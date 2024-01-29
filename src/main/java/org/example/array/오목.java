package org.example.array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @author noah kim
 * @date 2024-01-29
 * @link
 * @keyword_solution
 * @input
 * @output
 * @time_complex
 * @perf
 */

// 오목판
// - 검은 바둑알 / 흰 바둑알
// - 연속으로 5알이 놓인 경우 승리
public class 오목 {
    private static int ROW = 19, COL = 19;
    private static char[][] board;
    private static int[][] DIRS = {{-1,1},{0,1},{1,1},{1,0}};

    public static void main(String[] args) throws IOException {
        board = new char[ROW][COL];

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            for (int r = 0; r < ROW; r++) {
                String[] s = br.readLine().split(" ");
                for (int c = 0; c < COL; c++) board[r][c] = s[c].charAt(0);
            }
        }

        for (int c = 0; c < COL; c++) {
            for (int r = 0; r < ROW; r++) {
                char curC = board[r][c];
                if (curC == '0') continue;

                for (int dirIdx = 0; dirIdx < DIRS.length; dirIdx++) {
                    if (dfs(r, c, dirIdx, 1)) {
                        if (!isOverFiveInRow(r, c, dirIdx)) {
                            System.out.println(curC);
                            System.out.printf("%d %d", (r+1), (c+1));
                            return;
                        }
                    }
                }
            }
        }

        System.out.println(0);
    }

    private static boolean dfs(int r, int c, int dirIdx, int lineCnt) {
        if (lineCnt == 5) return true;

        int nr = r + DIRS[dirIdx][0], nc = c + DIRS[dirIdx][1];
        if (nr < 0 || nr >= ROW || nc < 0 || nc >= COL) return false;

        if (board[r][c] != board[nr][nc]) return false;

        return dfs(nr, nc, dirIdx, lineCnt+1);
    }

    private static boolean isOverFiveInRow(int r, int c, int dirIdx) {
        return isPrevInRow(r, c, dirIdx) || isNextAfterFiveInRow(r, c, dirIdx);
    }

    private static boolean isPrevInRow(int r, int c, int dirIdx) {
        int nr = r + DIRS[dirIdx][0] * -1, nc = c + DIRS[dirIdx][1] * -1;
        if (nr < 0 || nr >= ROW || nc < 0 || nc >= COL) return false;

        return board[r][c] == board[nr][nc];
    }

    private static boolean isNextAfterFiveInRow(int r, int c, int dirIdx) {
        int nr = r + DIRS[dirIdx][0] * 5, nc = c + DIRS[dirIdx][1] * 5;
        if (nr < 0 || nr >= ROW || nc < 0 || nc >= COL) return false;

        return board[r][c] == board[nr][nc];
    }
}
