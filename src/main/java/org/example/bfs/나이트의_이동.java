package org.example.bfs;

import java.io.*;
import java.util.*;

public class 나이트의_이동 {

    static int N, size, dy, dx;
    static int[] input;
    static int[][] board, dir = {{2, -1}, {2, 1}, {-2, -1}, {-2, 1},
                         {-1, -2}, {-1, 2}, {1, -2}, {1, 2}};
    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        init();

        for (int i = 0; i < N; i++) {
            size = Integer.parseInt(br.readLine());
            board = new int[size][size];

            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int sy = input[0], sx = input[1];

            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            dy = input[0];
            dx = input[1];

            bfs(sy, sx);
        }
    }

    private static void bfs(int y, int x) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {y, x});

        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int cy = point[0], cx = point[1];

            if (cy == dy && cx == dx) {
                System.out.println(board[cy][cx]);
                return;
            }

            for (int i = 0; i < dir.length; i++) {
                int ny = cy + dir[i][0], nx = cx + dir[i][1];
                if (ny < 0 || ny >= size) continue;
                if (nx < 0 || nx >= size) continue;
                if (board[ny][nx] > 0) continue;

                queue.offer(new int[] {ny, nx});
                board[ny][nx] = board[cy][cx] + 1;
            }
        }
    }

    private static void init() throws IOException {
        // 입력
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
    }
}
