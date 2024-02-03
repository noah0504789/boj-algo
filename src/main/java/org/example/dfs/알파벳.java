package org.example.dfs;

import java.io.*;
import java.util.*;

/**
 * @author noah kim
 * @date 2024/02/04
 * @link https://www.acmicpc.net/problem/1987
 * @keyword_solution
    Requirements: 말이 지나갈 수 있는 최대의 칸수를 출력하라.

    [보드]
    - 크기: R * C
    - 칸: 대문자 알파벳

    [말]
    - 좌표: 좌측상단 (0,0)
    - 이동: 지금까지 지나온 알파벳과 달라야 함
 * @input
    - R, C (1<=R,C<=20)
    - 보드 칸 정보
 * @output
    - 말이 지날 수 있는 최대의 칸 수
 * @time_complex O(N^2)
 * @perf
 */
public class 알파벳 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static int[][] DIRS = {{0,1},{0,-1},{1,0},{-1,0}};
    private static char[][] board;
    private static boolean[] visited = new boolean['Z'-'A'+1];
    private static int R, C, ans=1;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];

        for (int r = 0; r < R; r++) board[r] = br.readLine().toCharArray();

        dfs(0,0, 0);

        System.out.println(ans);
    }

    private static void dfs(int r, int c, int length) {
        int curPointIdx = board[r][c] - 'A';
        if (visited[curPointIdx]) {
            ans = Math.max(ans, length);
            return;
        }

        for (int[] dir : DIRS) {
            int nr = r + dir[0], nc = c + dir[1];

            if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;

            visited[curPointIdx] = true;
            dfs(nr, nc, length+1);
            visited[curPointIdx] = false;
        }
    }
}
