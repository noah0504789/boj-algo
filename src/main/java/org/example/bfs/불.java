package org.example.bfs;

import java.io.*;
import java.util.*;

public class 불 {

    static int T, H, W, ans, VISITED = -1, EMPTY = 0, WALL = 1, FIRE = 2;
    static int[] input;
    static int[][] board, dir = {{0, 1},{0, -1},{1, 0},{-1, 0}};
    static Queue<int[]> queueMan, queueFire;
    static String[] inputChar;
    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        init();

        for (int t = 0; t < T; t++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            W = input[0];
            H = input[1];
            board = new int[H][W];
            queueMan = new LinkedList<>();
            queueFire = new LinkedList<>();

            for (int i = 0; i < H; i++) {
                inputChar = br.readLine().split("");
                for (int j = 0; j < W; j++) {
                    if (inputChar[j].equals("@")) queueMan.offer(new int[]{i, j});
                    else if (inputChar[j].equals("*")) queueFire.offer(new int[]{i, j});

                    board[i][j] = mapToIntValue(inputChar[j]);
                }
            }

            ans = 0;

            if (bfs()) System.out.println(ans+1);
            else System.out.println("IMPOSSIBLE");
        }
    }

    private static boolean bfs() {
        while (!queueMan.isEmpty()) {
            int qFiresize = queueFire.size();
            for (int a = 0; a < qFiresize; a++) {
                int[] firePoint = queueFire.poll();
                int fcy = firePoint[0], fcx = firePoint[1];

                for (int f = 0; f < dir.length; f++) {
                    int fny = fcy + dir[f][0], fnx = fcx + dir[f][1];
                    if (fny < 0 || fny >= H) continue;
                    if (fnx < 0 || fnx >= W) continue;
                    if (board[fny][fnx] == WALL) continue;
                    if (board[fny][fnx] == FIRE) continue;

                    queueFire.offer(new int[] {fny, fnx});
                    board[fny][fnx] = FIRE;
                }
            }

            int qMansize = queueMan.size();
            for (int b = 0; b < qMansize; b++) {
                int[] manPoint = queueMan.poll();
                int cy = manPoint[0], cx = manPoint[1];
                if (cy == 0 || cy == H-1 || cx == 0 || cx == W-1) return true;

                for (int i = 0; i < dir.length; i++) {
                    int ny = cy + dir[i][0], nx = cx + dir[i][1];
                    if (ny < 0 || ny >= H) continue;
                    if (nx < 0 || nx >= W) continue;
                    if (board[ny][nx] == WALL) continue;
                    if (board[ny][nx] == FIRE) continue;
                    if (board[ny][nx] == VISITED) continue;

                    queueMan.offer(new int[] {ny, nx});
                    board[ny][nx] = VISITED;
                }
            }

            ans++;
        }

        return false;
    }

    private static int mapToIntValue(String s) {
        if (s.equals(".")) return EMPTY;
        else if (s.equals("@")) return VISITED;
        else if (s.equals("#")) return WALL;
        else if (s.equals("*")) return FIRE;

        return -100;
    }

    private static void init() throws IOException {
        // 입력
        br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
    }
}
