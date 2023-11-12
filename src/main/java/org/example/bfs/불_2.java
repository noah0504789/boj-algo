package org.example.bfs;

import java.io.*;
import java.util.*;

public class 불_2 {
    static int SPACE = 1, WALL = 2, FIRE = 3,VISITED = 4, R, C, ans = 0;
    static int[] input;
    static int[][] board, dir = {{1,0},{-1,0},{0,1},{0,-1}};
    static String[] input2;
    static Queue<int[]> qFire, qMan;

    public static void main(String[] args) throws IOException {
        init();

        if (bfs()) System.out.println(ans+1);
        else System.out.println("IMPOSSIBLE");
    }

    private static boolean bfs() {
        while (!qMan.isEmpty()) {
            int qFireSize = qFire.size();
            for (int i = 0; i < qFireSize; i++) {
                int[] fPoint = qFire.poll();
                int fcy = fPoint[0], fcx = fPoint[1];
                for (int d = 0; d < dir.length; d++) {
                    int fny = fcy + dir[d][0], fnx = fcx + dir[d][1];
                    if (fny < 0 || fny >= R) continue;
                    if (fnx < 0 || fnx >= C) continue;
                    if (board[fny][fnx] == FIRE) continue;
                    if (board[fny][fnx] == WALL) continue;

                    qFire.offer(new int[] {fny, fnx});
                    board[fny][fnx] = FIRE;
                }
            }

            int qManSize = qMan.size();
            for (int j = 0; j < qManSize; j++) {
                int[] mPoint = qMan.poll();
                int mcy = mPoint[0], mcx = mPoint[1];

                if (mcy == 0 || mcy == R-1 || mcx == 0 || mcx == C-1) return true;

                for (int d = 0; d < dir.length; d++) {
                    int mny = mcy + dir[d][0], mnx = mcx + dir[d][1];
                    if (mny < 0 || mny >= R) continue;
                    if (mnx < 0 || mnx >= C) continue;
                    if (board[mny][mnx] == FIRE) continue;
                    if (board[mny][mnx] == WALL) continue;
                    if (board[mny][mnx] == VISITED) continue;

                    qMan.offer(new int[] {mny, mnx});
                    board[mny][mnx] = VISITED;
                }
            }

            ans++;
        }

        return false;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        R = input[0];
        C = input[1];

        board = new int[R][C];
        qFire = new LinkedList<>();
        qMan = new LinkedList<>();

        for (int i = 0; i < R; i++) {
            input2 = br.readLine().split("");
            for (int j = 0; j < C; j++) {
                String block = input2[j];
                if (block.equals("J")) qMan.offer(new int[]{i, j});
                else if (block.equals("F")) qFire.offer(new int[]{i, j});
                board[i][j] = parseInt(block);
            }
        }
    }

    private static int parseInt(String c) {
        if (c.equals("#")) return WALL;
        else if (c.equals(".") || c.equals("J")) return SPACE;
        else if (c.equals("F")) return FIRE;

        return -1;
    }
}
