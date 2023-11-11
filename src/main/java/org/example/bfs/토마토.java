package org.example.bfs;

// 모든 토마토들이 익는데 걸리는 날짜수를 리턴하라.
// - 익은 토마토들의 인접한 곳에 있는 익지 않은 토마토는 영향을 받아 익게된다.

import java.io.*;
import java.util.*;

public class 토마토 {

    static int M, N, ans = 0, RAW = 0, NON_EXIST = -1;
    static boolean allRipen;
    static int[] input;
    static int[][] board, dir = {{0,1},{0,-1},{1,0},{-1,0}};
    static Queue<int[]> queue;

    public static void main(String[] args) throws IOException {
        init();

        bfs();

        for (int i = 0; i < N; i++) {
            ans = Math.max(ans, Arrays.stream(board[i]).max().getAsInt());
            allRipen = Arrays.stream(board[i]).filter(n -> n == 0).findAny().isPresent();
            if (allRipen) {
                System.out.println(-1);
                return;
            }
        }

        System.out.println(ans-1);
    }

    private static void bfs() {
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int cy = point[0], cx = point[1];

            for (int i = 0; i < dir.length; i++) {
                int ny = cy + dir[i][0], nx = cx + dir[i][1];
                if (ny < 0 || ny >= N) continue;
                if (nx < 0 || nx >= M) continue;
                if (board[ny][nx] == NON_EXIST) continue;
                if (board[ny][nx] != RAW) continue;

                queue.offer(new int[]{ny, nx});
                board[ny][nx] = board[cy][cx] + 1;
            }
        }
    }

    private static void init() throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = input[1];
        M = input[0];
        board = new int[N][M];
        queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < M; j++) {
                board[i][j] = input[j];
                if (input[j] == 1) queue.offer(new int[]{i, j});
            }
        }
    }
}
