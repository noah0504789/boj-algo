package org.example.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author noah kim
 * @date 2024-02-07
 * @link https://www.acmicpc.net/problem/2563
 * @keyword_solution
    Requirements: 도화지에 붙은 색종이의 너비를 출력하라.

    [도화지]
    - 크기: 100*100

    [색종이]
    - 크기: 10*10
    - 붙이기
        1. 도화지와 평행하게 붙임
        2. 겹쳐서 붙일 수 있음
 * @input
    - 색종이 수(1<=N<=100)
    - 각 색종이의 좌표: 좌측하단 좌표
 * @output
    - 색종이 너비
 * @time_complex
 * @perf 14516kb / 128ms
 */
public class 색종이 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int BOARD_SIDE = 100;
    private static final int COLOR_PAPER_SIDE = 10;
    private static StringTokenizer st;
    private static Queue<int[]> queue = new LinkedList<>();
    private static List<ColorPaper> list = new ArrayList<>();
    private static int[][] DIRS = {{1,0},{-1,0},{0,1},{0,-1}};
    private static boolean[][] colored = new boolean[BOARD_SIDE][BOARD_SIDE];
    private static int colorPaperCnt, ans;

    public static void main(String[] args) throws IOException {
        colorPaperCnt = Integer.parseInt(br.readLine());
        for (int i = 0; i < colorPaperCnt; i++) {
            st = new StringTokenizer(br.readLine());

            int bottomLeftX = Integer.parseInt(st.nextToken())-1;
            int bottomLeftY = Integer.parseInt(st.nextToken())-1;

            list.add(new ColorPaper(bottomLeftX, bottomLeftY));

            for (int r = bottomLeftY; r < bottomLeftY + COLOR_PAPER_SIDE; r++) {
                for (int c = bottomLeftX; c < bottomLeftX + COLOR_PAPER_SIDE; c++) {
                    colored[r][c] = true;
                }
            }
        }

        Collections.sort(list);

        for (ColorPaper colorPaper : list) {
            bfs(colorPaper);
        }

        System.out.println(ans);

        br.close();
    }

    private static void bfs(ColorPaper colorPaper) {
        int ly = colorPaper.ly;
        int lx = colorPaper.lx;

        if (!colored[ly][lx]) return;

        queue.offer(new int[]{ly, lx});

        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int cr = point[0], cc = point[1];

            for (int[] dir : DIRS) {
                int nr = cr + dir[0], nc = cc + dir[1];
                if (nr < 0 || nr >= BOARD_SIDE || nc < 0 || nc >= BOARD_SIDE) continue;
                if (!colored[nr][nc]) continue;

                queue.offer(new int[]{nr, nc});
                colored[nr][nc] = false;
                ans++;
            }
        }

        queue.clear();
    }

    static class ColorPaper implements Comparable<ColorPaper> {
        int ly, lx;

        public ColorPaper(int lx, int ly) {
            this.ly = ly;
            this.lx = lx;
        }

        @Override
        public int compareTo(ColorPaper o) {
            if (this.ly == o.ly) return this.lx - o.lx;

            return o.ly - this.ly;
        }
    }
}
