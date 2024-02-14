package org.example.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author noah kim
 * @date 2024/02/14
 * @link
 * @requirement 연결할 수 있는 최대 파이프라인의 개수를 구하라
 * @summary
    [지도]
    - 크기 R*C
    - 첫째 열 : 가스관(빈칸)
    - 마지막 열 : 집(빈칸)

    [파이프]
    - 가스관 - 집
    - 건물 없는 곳, 파이프 없는 곳
    - Horizontal, diagonal
 * @input
    - R(1<=R<=10_000), C(5<=C<=500)
    - . : 빈칸, x : 건물
 * @output
 * @time_complex
 * @perf
 */
public class 빵집 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final char BUILDING = 'x';
    private static final int VISITED_SIGNAL = 'V';
    private static StringTokenizer st;
    private static char[][] board;
    private static int[][] DIRS = {{-1, 1}, {0, 1}, {1, 1}};
    private static int GAS_COL;
    private static int R, C, ans;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];
        GAS_COL = C-1;

        for (int r = 0; r < R; r++) {
            board[r] = br.readLine().toCharArray();
        }

        for (int r = 0; r < R; r++) {
            if (dfs(r, 0)) ans++;
        }

        System.out.println(ans);
    }

    private static boolean dfs(int r, int c) {
        if (c == GAS_COL) return true;

        for (int[] dir : DIRS) {
            int nr = r + dir[0], nc = c + dir[1];
            if (nr < 0 || nr >= R) continue;
            if (board[nr][nc] == BUILDING) continue;
            if (board[nr][nc] == VISITED_SIGNAL) continue;

            board[nr][nc] = VISITED_SIGNAL;
            if (dfs(nr, nc)) return true;
        }

        return false;
    }
}
