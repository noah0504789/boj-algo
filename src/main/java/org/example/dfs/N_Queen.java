package org.example.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author noah kim
 * @date 2024/02/04
 * @link https://www.acmicpc.net/problem/9663
 * @keyword_solution
    Requirements: 퀸 N개를 서로 공격할 수 없도록 퀸을 놓는 방법의 수를 출력하라.

    [체스판]
    - 크기: N * N

    [퀸]
    - 이동방향: 상,하,좌,우,대각
    - 이동거리: 제한없이 이동가능
 * @input
    - N (1<=N<=15)
 * @output
    - 퀸을 놓는 방법의 수
 * @time_complex O(N^2)
 * @perf
 */
public class N_Queen {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static boolean[][] board;
    private static int[][] DIRS = {{1,0},{-1,0},{-1,1},{-1,-1},{1,-1},{1,1}};
    private static int N, cnt;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        board = new boolean[N][N];

        dfs(0);

        System.out.println(cnt);
    }

    private static void dfs(final int depth) {
        if (depth == N) {
            cnt++;
            return;
        }

        for (int c = 0; c < N; c++) {
            if (!isValid(depth, c)) continue;

            board[depth][c] = true;
            dfs(depth+1);
            board[depth][c] = false;
        }
    }

    private static boolean isValid(int depth, int c) {
        for (int[] dir : DIRS) {
            int nr=0, nc=0, delta=1;
            while (true) {
                nr = depth+dir[0]*delta;
                nc = c+dir[1]*delta;

                if (nr < 0 || nr >= N || nc < 0 || nc >= N) break;
                if (board[nr][nc]) return false;

                delta++;
            }
        }

        return true;
    }
}
