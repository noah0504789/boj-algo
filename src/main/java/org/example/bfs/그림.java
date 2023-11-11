package org.example.bfs;

import java.io.*;
import java.util.*;

// 도화지에 있는 그림의 개수와 가장 큰 그림의 넓이를 출력하라

public class 그림 {

    static String[] input = null;
    static int N, M, cnt = 0, maxArea = 0;
    static int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static boolean[][] board;
    static Queue<int[]> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        init();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j]) {
                    bfs(i, j);
                }
            }
        }

        System.out.println(cnt);
        System.out.println(maxArea);
    }

    private static void bfs(int i, int j) {
        int area = 0;

        queue.offer(new int[]{i, j});
        board[i][j] = false;
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            area++;

            for (int k = 0; k < 4; k++) {
                int nY = point[0] + dir[k][0], nX = point[1] + dir[k][1];

                if (nY < 0 || nY >= N) continue;
                if (nX < 0 || nX >= M) continue;
                if (!board[nY][nX]) continue;

                queue.offer(new int[] {nY, nX});
                board[nY][nX] = false;
            }
        }

        maxArea = Math.max(maxArea, area);

        cnt++;
    }

    private static void init() throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        board = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                if (Integer.parseInt(input[j]) == 1) board[i][j] = true;
            }
        }
    }
}
