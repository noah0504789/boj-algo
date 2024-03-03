package org.example.dfs;

import java.io.IOException;

/**
 * @author noah kim
 * @date 2024/03/03
 * @link
 * @requirement
 * @summary
 * @input
 * @output
 * @time_complex
 * @perf 17072kb / 212ms
 */
public class 파이프_옮기기_1 {
    private static final int RIGHT = 0;
    private static final int DIAGONAL = 1;
    private static final int DOWN = 2;
    private static final int EMPTY = 0;

    private static int[][] board;
    private static int sr = 0, sc = 0, dtr, dtc;
    private static int N, ans = 0;

    public static void main(String[] args) throws IOException {
        N = readInt();
        dtr = dtc = N-1;
        board = new int[N][N];

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                board[r][c] = readInt();
            }
        }

        dfs(sr, sc+1, RIGHT);

        System.out.println(ans);
    }

    private static void dfs(int r, int c, int d) {
        if (r == dtr && c == dtc) {
            ans++;
            return;
        }

        if (d != DOWN && c+1 < N && board[r][c+1] == EMPTY) dfs(r, c+1, RIGHT);
        if (d != RIGHT && r+1 < N && board[r+1][c] == EMPTY) dfs(r+1, c, DOWN);
        if (r+1 < N && c+1 < N && board[r+1][c] == EMPTY && board[r][c+1] == EMPTY && board[r+1][c+1] == EMPTY) dfs(r+1, c+1, DIAGONAL);
    }

    private static int readInt() throws IOException {
        int c, n = System.in.read() & (1<<4)-1;
        while ((c = System.in.read()) > (1<<5)) {
            n = (n<<1) + (n<<3) + (c&(1<<4)-1);
        }
        return n;
    }
}
