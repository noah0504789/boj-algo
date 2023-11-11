package org.example.bfs;

// 직사각형을 제외한 넓이를 구하라

import java.io.*;
import java.util.*;

public class 영역_구하기 {

    static int M, N, K, cnt = 0;
    static int[] input;
    static int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static List<Integer> ans;
    static boolean[][] board;
    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        init();

        for (int i = 0; i < K; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            for (int j = input[1]; j < input[3]; j++) {
                for (int k = input[0]; k < input[2]; k++) {
                    if (!board[j][k]) {
                        board[j][k] = true;
                    }
                }
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (!board[i][j]) {
                    cnt++;
                    bfs(i, j);
                }
            }
        }

        System.out.println(cnt);

        StringBuffer sb = new StringBuffer();
        ans.stream().sorted().forEach(a -> sb.append(a + " "));
        System.out.println(sb.toString().trim());
    }

    private static void bfs(int y, int x) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{y, x});
        board[y][x] = true;

        int blankCnt = 1;

        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int cy = point[0], cx = point[1];

            for (int i = 0; i < 4; i++) {
                int ny = cy + dir[i][0], nx = cx + dir[i][1];
                if (ny < 0 || ny >= M) continue;
                if (nx < 0 || nx >= N) continue;
                if (board[ny][nx]) continue;

                queue.offer(new int[] {ny, nx});
                board[ny][nx] = true;
                blankCnt++;
            }
        }

        ans.add(blankCnt);
    }

    private static void init() throws IOException {
        // 입력
        br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        M = Integer.parseInt(input[0]);
        N = Integer.parseInt(input[1]);
        K = Integer.parseInt(input[2]);
        board = new boolean[M][N];
        ans = new ArrayList<>();
    }
}
