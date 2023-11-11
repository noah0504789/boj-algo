package org.example.bfs;

import java.io.*;
import java.util.*;

public class 유기농배추 {

    static int T, M, N, K, cnt;
    static int[] input;
    static int[][] dir = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    static boolean[][] board;
    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        init();

        for (int i = 0; i < T; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            M = input[0];
            N = input[1];
            board = new boolean[N][M];

            K = input[2];

            for (int j = 0; j < K; j++) {
                input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                board[input[1]][input[0]] = true;
            }

            cnt = 0;

            for (int y = 0; y < N; y++) {
                for (int x = 0; x < M; x++) {
                    if (board[y][x]) {
                        cnt++;
                        bfs(y, x);
                    }
                }
            }

            System.out.println(cnt);
        }
    }

    private static void bfs(int y, int x) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {y, x});
        board[y][x] = false;

        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int cy = point[0], cx = point[1];

            for (int i = 0; i < dir.length; i++) {
                int ny = cy + dir[i][0], nx = cx + dir[i][1];
                if (ny < 0 || ny >= N) continue;
                if (nx < 0 || nx >= M) continue;
                if (!board[ny][nx]) continue;

                queue.offer(new int[] {ny, nx});
                board[ny][nx] = false;
            }
        }
    }

    private static void init() throws IOException {
        // 입력
        br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
    }
}
