package org.example.backtracking;

import java.io.*;
import java.util.*;

/**
 * @author noah kim
 * @date 2024-03-27
 * @link https://www.acmicpc.net/problem/2239
 * @requirement 하다 만 스도쿠 보드가 주어질 때, 완성된 스도쿠 보드를 출력하라.
 * @keyword
[스도쿠]
- size: 9*9 ((3*3)*9)
- 칸: 1~9
- 규칙:
1. 3*3 보드에 내부에 숫자중복 X
2. 9*9 보드의 행,열에 숫자중복 X
 * @input 하다 만 스도쿠 보드(0:빈칸)
 * @output 사전순으로 가장 앞서는 경우 출력
 * @time_complex
 * @perf
 */
public class 스도쿠 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuilder sb = new StringBuilder();
    private static final int BOARD_SIDE = 9;
    private static int[][] board = new int[BOARD_SIDE][BOARD_SIDE];
    private static List<int[]> blanks = new ArrayList<>();
    private static int[] rowMark = new int[BOARD_SIDE];
    private static int[] colMark = new int[BOARD_SIDE];
    private static int[] miniBoardMark = new int[BOARD_SIDE];
    private static int[] row;
    private static boolean flag = false;

    public static void main(String[] args) throws IOException {
        for (int r = 0; r < BOARD_SIDE; r++) {
            row = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
            for (int c = 0; c < BOARD_SIDE; c++) {
                int val = row[c];
                if (val == 0) {
                    blanks.add(new int[] {r, c});
                    continue;
                }

                int id = getId(r, c);
                rowMark[r] |= 1<<val;
                colMark[c] |= 1<<val;
                miniBoardMark[id] |= 1<<val;

                board[r][c] = val;
            }
        }

        dfs(0);

        for (int cr = 0; cr < BOARD_SIDE; cr++) {
            for (int cc = 0; cc < BOARD_SIDE; cc++) {
                sb.append(board[cr][cc]);
            }
            sb.append("\n");
        }

        System.out.println(sb);

        br.close();
    }

    private static void dfs(int cnt) {
        if (cnt == blanks.size()) {
            flag = true;
            return;
        }

        int[] blankPoint = blanks.get(cnt);
        int r = blankPoint[0], c = blankPoint[1];
        int id = getId(r,c);

        for (int val = 1; val < 10; val++) {
            if ((rowMark[r]&(1<<val)) > 0) continue;
            if ((colMark[c]&(1<<val)) > 0) continue;
            if ((miniBoardMark[id]&(1<<val)) > 0) continue;

            rowMark[r] |= 1<<val;
            colMark[c] |= 1<<val;
            miniBoardMark[id] |= 1<<val;

            board[r][c] = val;

            dfs(cnt+1);

            if (flag) return;

            rowMark[r] &= ~(1<<val);
            colMark[c] &= ~(1<<val);
            miniBoardMark[id] &= ~(1<<val);
        }
    }

    private static int getId(int r, int c) {
        return (r/3)*3+c/3;
    }
}
