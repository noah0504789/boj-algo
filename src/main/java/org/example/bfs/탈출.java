package org.example.bfs;

import java.io.*;
import java.util.*;

/**
 * @author noah kim
 * @date 2024-03-28
 * @link https://www.acmicpc.net/problem/3055
 * @requirement 굴로 이동하는 데 걸리는 최소시간을 출력하라. (이동 불가 시, "KAKTUS" 출력)
 * @keyword
    [지도]
    - 크기: R*C
    - 칸: 빈칸(.), 물(*), 돌(X), 굴(D), 내위치(S)

    [물]
    - 확장(사방면)
    - 확장 X: 굴

    [이동]
    - 이동 X: 돌,물 X
    - 이동 O: 사방면 + 자신
 * @input 2 <= R, C <= 50
 * @output
 * @time_complex 16536kb / 188ms
 * @perf
 */
public class 탈출 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final Queue<Point> humanQ = new ArrayDeque<>();
    private static final Queue<Point> waterQ = new ArrayDeque<>();
    private static final int[][] DIRS = {{0,1},{0,-1},{-1,0},{1,0}};
    private static final String IMPOSSIBLE = "KAKTUS";
    private static final char EMPTY = '.';
    private static final char WATER = '*';
    private static final char ROCK = 'X';
    private static final char DEST = 'D';
    private static final char MY = 'S';
    private static char[][] board;
    private static boolean[][] visited;
    private static int R, C;
    static class Point {
        final int r, c, turn;

        public Point(int r, int c, int turn) {
            this.r = r;
            this.c = c;
            this.turn = turn;
        }
    }

    public static void main(String[] args) throws IOException {
        int[] size = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        R = size[0];
        C = size[1];

        board = new char[R][C];
        visited = new boolean[R][C];
        for (int r = 0; r < R; r++) {
            String str = br.readLine();
            for (int c = 0; c < C; c++) {
                board[r][c] = str.charAt(c);

                if (board[r][c] == MY) {
                    visited[r][c] = true;
                    humanQ.offer(new Point(r, c, 0));
                } else if (board[r][c] == WATER) {
                    waterQ.offer(new Point(r, c, 0));
                }
            }
        }

        while (!humanQ.isEmpty()) {
            move();
        }

        System.out.println(IMPOSSIBLE);

        br.close();
    }

    private static void move() {
        int curT = humanQ.peek().turn;

        while (!humanQ.isEmpty() && humanQ.peek().turn == curT) {
            Point curH = humanQ.poll();
            int cc = curH.c, cr = curH.r, ct = curH.turn;
            if (board[cr][cc] == DEST) {
                System.out.println(ct);

                System.exit(0);
            }

            while (!waterQ.isEmpty() && waterQ.peek().turn == curT) {
                Point curW = waterQ.poll();
                for (int[] dir : DIRS) {
                    int nr = curW.r + dir[0], nc = curW.c + dir[1];
                    if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
                    if (board[nr][nc] == ROCK) continue;
                    if (board[nr][nc] == DEST) continue;
                    if (board[nr][nc] == WATER) continue;

                    board[nr][nc] = WATER;
                    waterQ.offer(new Point(nr, nc, curT+1));
                }
            }

            for (int[] dir : DIRS) {
                int nc = cc+dir[0], nr = cr+dir[1];
                if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
                if (board[nr][nc] == ROCK) continue;
                if (board[nr][nc] == WATER) continue;
                if (visited[nr][nc]) continue;

                visited[nr][nc] = true;
                humanQ.offer(new Point(nr, nc, curT+1));
            }
        }
    }
}
