package org.example.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * @author noah kim
 * @date 2024/02/16
 * @link https://www.acmicpc.net/problem/1987
 * @requirement 말이 지나갈 수 있는 최대 칸을 출력하라.
 * @summary
    [보드]
    - 크기: R*C
    - 칸: Upper case

    [말]
    - 좌표: (0,0)
    - 이동조건: 지금까지 지나온 알파벳과 달라야 함.
 * @input
    - R(1<=R<=20), C(1<=C<=20)
    - 보드정보
 * @output
 * @time_complex O(N^2)
 * @perf 14848kb / 1116ms
 */
public class 알파벳_T2 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int[][] DIRS = {{0,1}, {0,-1}, {-1,0}, {1,0}};
    private static boolean[] visited = new boolean['Z'-'A'+1];
    private static StringTokenizer st;
    private static char[][] board;
    private static int R, C, ans = 1;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new char[R][C];

        for (int r = 0; r < R; r++) board[r] = br.readLine().toCharArray() ;

        dfs(0, 0, 0);

        System.out.println(ans);

        br.close();
    }

    private static void dfs(int r, int c, int length) {
        int curIdx = board[r][c] - 'A';
        if (visited[curIdx]) {
            ans = Math.max(ans, length);
            return;
        }

        for (int[] dir : DIRS) {
            int nr = r + dir[0], nc = c + dir[1];
            if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;

            visited[curIdx] = true;
            dfs(nr, nc, length+1);
            visited[curIdx] = false;
        }
    }
}

