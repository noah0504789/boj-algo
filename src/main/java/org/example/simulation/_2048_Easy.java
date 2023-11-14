package org.example.simulation;

// 최대 5번 이동시켜서 얻을 수 있는 최대 숫자를 리턴하라

// 2048
// - 한 번의 이동 : 상하좌우 네 방향 중 하나로 끝까지 이동
// - 아래에서 위로 이동함
// - 충돌 : 두 블록은 하나로 합쳐짐
//   - 한번 합쳐진 블록은 다른 블록과 다시 합쳐질 수 없음

import java.io.*;
import java.util.*;

// 출처 : https://hidelookit.tistory.com/183
public class _2048_Easy {
    static int n, max = 0;
    static int[] direct, dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    static int[][] map, temp;
    static boolean[][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(reader.readLine());

        map = new int[n+1][n+1];
        direct = new int[5];

        StringTokenizer st;
        for (int i=1; i<=n; i++) {
            st = new StringTokenizer(reader.readLine());
            for (int j=1; j<=n; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }

        dfs(5,0);//5번

        System.out.println(max);
    }

    private static void dfs(int end, int index) {
        if (index == end) {
            confirm();
            return;
        }

        for (int i = 0; i < 4; i++) {
            direct[index] = i;
            dfs(end, index+1);
        }
    }

    private static void confirm() {
        temp = new int[n+1][n+1];

        for (int i = 1; i <= n; i++) temp[i] = map[i].clone();

        for (int d = 0; d < direct.length; d++) {
            visit = new boolean[n+1][n+1];

            if (direct[d] == 0) { // 상
                for (int i=1; i<=n; i++) {
                    for (int j=1; j<=n; j++) {
                        move(i,j,direct[d]);
                    }
                }
            } else if (direct[d] == 2) { // 하
                for (int i=n; i>=1; i--) {
                    for (int j=1; j<=n; j++) {
                        move(i,j,direct[d]);
                    }
                }
            } else if (direct[d] == 1) { // 우
                for (int i=1; i<=n; i++) {
                    for (int j=n; j>=1; j--) {
                        move(i,j,direct[d]);
                    }
                }
            } else {
                for (int i=1; i<=n; i++) { // 좌
                    for (int j=1; j<=n; j++) {
                        move(i,j,direct[d]);
                    }
                }
            }
        }

        for (int i=1; i<=n; i++) {
            for (int j = 1; j <= n; j++) {
                if (temp[i][j] > max) max = temp[i][j];
            }
        }
    }

    private static void move(int x, int y, int dir) {
        if (temp[x][y] == 0) return;

        while (true) {
            int nx = x + dx[dir], ny = y + dy[dir];

            if (nx < 1 || ny < 1 || nx > n || ny > n) return;
            if (visit[nx][ny]) return;
            if (temp[nx][ny] == temp[x][y]) {
                visit[nx][ny] = true;
                temp[nx][ny] *= 2;
                temp[x][y] = 0;
                return;
            } else if (temp[nx][ny] != 0) {
                return;
            }

            temp[nx][ny] = temp[x][y];
            temp[x][y] = 0;
            x = nx;
            y = ny;
        }
    }
}

/**
 static int N, ans = 0, MAX_MOVE = 5, UP = 0, DOWN = 1, RIGHT = 2, LEFT = 3, DIR_LENGTH = 4;
 static int[] input;
 static int[][] blocks, beforeBlocks;

 public static void main(String[] args) throws IOException {
 init();

 dfs(0);

 System.out.println(ans);
 }

 private static void dfs(int depth) {
 if (depth == MAX_MOVE) {
 for (int n = 0; n < N; n++) {
 ans = Math.max(ans, Arrays.stream(blocks[n]).max().getAsInt());
 }

 return;
 }

 for (int i = 0; i < DIR_LENGTH; i++) {
 for (int j = 0; j < N; j++) System.arraycopy(blocks[j], 0, beforeBlocks[j], 0, N);

 move(i);
 dfs(depth+1);

 for (int k = 0; k < N; k++) System.arraycopy(beforeBlocks[k], 0, blocks[k], 0, N);
 }
 }

 private static void move(int DIR) {
 boolean[][] isCollison = new boolean[N][N];

 if (DIR == UP) {
 for (int j = 0; j < N; j++) {
 int r_idx = N-1;
 while (r_idx > 0) {
 if (blocks[r_idx][j] == 0 || isCollison[r_idx][j]) {
 r_idx--;
 continue;
 }

 if (blocks[r_idx-1][j] == 0) {
 blocks[r_idx-1][j] = blocks[r_idx][j];
 blocks[r_idx][j] = 0;
 r_idx--;
 continue;
 }

 if (blocks[r_idx-1][j] == blocks[r_idx][j]) {
 blocks[r_idx-1][j] += blocks[r_idx][j];
 blocks[r_idx][j] = 0;
 isCollison[r_idx-1][j] = true;
 }

 r_idx--;
 }
 }
 } else if (DIR == DOWN) {
 for (int j = 0; j < N; j++) {
 int r_idx = 0;
 while (r_idx < N-1) {
 if (blocks[r_idx][j] == 0 || isCollison[r_idx][j]) {
 r_idx++;
 continue;
 }

 if (blocks[r_idx+1][j] == 0) {
 blocks[r_idx+1][j] = blocks[r_idx][j];
 blocks[r_idx][j] = 0;
 r_idx++;
 continue;
 }

 if (blocks[r_idx][j] == blocks[r_idx+1][j]) {
 blocks[r_idx+1][j] += blocks[r_idx][j];
 blocks[r_idx][j] = 0;
 isCollison[r_idx+1][j] = true;
 }

 r_idx++;
 }
 }
 } else if (DIR == RIGHT) {
 for (int i = 0; i < N; i++) {
 int c_idx = 0;
 while (c_idx < N-1) {
 if (blocks[i][c_idx] == 0 || isCollison[i][c_idx]) {
 c_idx++;
 continue;
 }

 if (blocks[i][c_idx+1] == 0) {
 blocks[i][c_idx+1] = blocks[i][c_idx];
 blocks[i][c_idx] = 0;
 c_idx++;
 continue;
 }

 if (blocks[i][c_idx] == blocks[i][c_idx+1]) {
 blocks[i][c_idx+1] += blocks[i][c_idx];
 blocks[i][c_idx] = 0;
 isCollison[i][c_idx+1] = true;
 }

 c_idx++;
 }
 }
 } else if (DIR == LEFT) {
 for (int i = 0; i < N; i++) {
 int c_idx = N-1;
 while (c_idx > 0) {
 if (blocks[i][c_idx] == 0 || isCollison[i][c_idx]) {
 c_idx--;
 continue;
 }

 if (blocks[i][c_idx-1] == 0) {
 blocks[i][c_idx-1] = blocks[i][c_idx];
 blocks[i][c_idx] = 0;
 c_idx--;
 continue;
 }

 if (blocks[i][c_idx] == blocks[i][c_idx-1]) {
 blocks[i][c_idx-1] += blocks[i][c_idx];
 blocks[i][c_idx] = 0;
 isCollison[i][c_idx-1] = true;
 }

 c_idx--;
 }
 }
 }
 }

 private static void init() throws IOException {
 // 입력
 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

 input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
 N = input[0];

 blocks = new int[N][N];
 beforeBlocks = new int[N][N];

 for (int i = 0; i < N; i++) {
 blocks[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
 }
 }
 */
