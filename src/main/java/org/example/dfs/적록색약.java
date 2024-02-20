package org.example.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Predicate;

/**
 * @author noah kim
 * @date 2024/02/21
 * @link
 * @requirement
 * @summary
 * @input
 * @output
 * @time_complex
 * @perf 16540kb / 156ms
 */
public class 적록색약 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int[][] DIRS = {{0,1},{0,-1},{1,0},{-1,0}};
    private static Queue<int[]> queue = new LinkedList<>();
    private static boolean[][] visited;
    private static char[][] board;
    private static char RED = 'R';
    private static char BLUE = 'B';
    private static char GREEN = 'G';
    private static int N, redCnt, greenCnt, blueCnt, redGreenCnt;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        board = new char[N][N];
        for (int i = 0; i < N; i++) board[i] = br.readLine().toCharArray();

        visited = new boolean[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (visited[r][c]) continue;
                char curCh = board[r][c];

                bfs(r, c, ch -> ch == curCh);

                if (curCh == BLUE) blueCnt++;
                else if (curCh == RED) redCnt++;
                else if (curCh == GREEN) greenCnt++;
            }
        }

        visited = new boolean[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (visited[r][c]) continue;
                char curCh = board[r][c];
                if (curCh == BLUE) continue;

                bfs(r, c, 적록색약::isRedOrGreen);

                redGreenCnt++;
            }
        }

        System.out.println((redCnt+greenCnt+blueCnt) + " " + (redGreenCnt+blueCnt));
    }

    private static void bfs(int r, int c, Predicate<Character> shouldVisit) {
        queue.offer(new int[] {r, c});
        visited[r][c] = true;

        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int cr = point[0], cc = point[1];

            for (int[] dir : DIRS) {
                int nr = cr + dir[0], nc = cc + dir[1];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                if (visited[nr][nc]) continue;
                if (!shouldVisit.test(board[nr][nc])) continue;

                visited[nr][nc] = true;
                queue.offer(new int[] {nr, nc});
            }
        }
    }

    private static boolean isRedOrGreen(char input) {
        return input == RED || input == GREEN;
    }
}
