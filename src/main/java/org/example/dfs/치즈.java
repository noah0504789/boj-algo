package org.example.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author noah kim
 * @date 2024-02-20
 * @link https://www.acmicpc.net/problem/2636
 * @requirement 치즈가 모두 녹는데 걸리는 시간과 모두 녹기 직전에 남아있는 치즈조각 수를 출력하라.
 * @keyword
    [보드]
    - 정사각형
    - 가장자리(X): 치즈 없음
    - 치즈: 구멍이 있을 수 있음. 공기와 접촉 시 녹음. 구멍이 있는 치즈는 녹게되면 구멍속으로 공기가 들어감
 * @input
 * @output
 * @time_complex
 * @perf 16412kb / 156ms
 */
public class 치즈 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int[][] DIRS = {{0,1},{0,-1},{-1,0},{1,0}};
    private static StringTokenizer st;
    private static int[][] board;
    private static boolean[][] visited;
    private static int R, C, time, prevCnt, totCnt;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new int[R][C];

        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
                if (board[r][c] == 1) totCnt++;
            }
        }

        while (totCnt > 0) bfs();

        System.out.println(time);
        System.out.println(prevCnt);

        br.close();
    }

    private static void bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        visited = new boolean[R][C];
        visited[0][0] = true;
        int meltedCnt = 0;

        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int r = point[0], c = point[1];

            for (int[] dir : DIRS) {
                int nr = r + dir[0], nc = c + dir[1];
                if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
                if (visited[nr][nc]) continue;
                visited[nr][nc] = true;

                if (board[nr][nc] == 1) {
                    meltedCnt++;
                    board[nr][nc] = 0;
                } else {
                    queue.offer(new int[]{nr, nc});
                }
            }
        }

        prevCnt = meltedCnt;
        totCnt -= prevCnt;
        time++;
    }
}
