package org.example.array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author noah kim
 * @date 2024-02-06
 * @link https://www.acmicpc.net/problem/16926
 * @keyword_solution
    Requirements: 주어진 배열을 반시계방향으로 R번 회전했을 때, 결과값을 출력하라.
 * @input
    - N(높이),M(너비),R(회전수) 2<=N,M<=300|1<=R<=1000
    - 배열 정보
 * @output
    - 회전된 배열
 * @time_complex
 * @perf
 */
public class 배열_돌리기_1_실패 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuffer sb = new StringBuffer();
    private static final int[][] DIRS = {{1,0},{0,1},{-1,0},{0,-1}};
    private static final int DOWN_IDX = 0;
    private static final int RIGHT_IDX = 1;
    private static final int UP_IDX = 2;
    private static final int LEFT_IDX = 3;
    private static StringTokenizer st;
    private static int[][] arr, rotatedArr;
    private static int N, M, R, rightAngleCnt, pushCnt;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        rotatedArr = new int[N][M];

        for (int r = 0; r < N; r++) {
            arr[r] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            rotatedArr[r] = arr[r].clone();
        }

        R = R%((N+M-2)*2);

        if (N == M) {
            rightAngleCnt = R%4;
            pushCnt = rightAngleCnt > 0 ? R%4:R;

            if (rightAngleCnt == 1) rotate90(0, 0, N, M);
            else if (rightAngleCnt == 2) rotate180(0, 0, N, M);
            else if (rightAngleCnt == 3) rotate270(0, 0, N, M);
        } else {
            pushCnt = R;
        }

        int dMax = Math.min(N/2, M/2);
        for (int p = 0; p < pushCnt; p++) {
            for (int d = 0; d < dMax; d++) {
                push(d, d, d, d, DOWN_IDX, N-d*2, M-d*2);
            }

            for (int r = 0; r < N; r++) arr[r] = rotatedArr[r].clone();
        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) sb.append(rotatedArr[r][c] + " ");
            sb.append("\n");
        }

        System.out.println(sb);

        br.close();
    }

    public static void rotate90(int curR, int curC, int hLen, int widLen) {
        if (widLen < 2) return;

        int heightR = curR + hLen;
        int widthC = curC + widLen;

        for (int r = curR; r < heightR; r++) {
            for (int c = curC; c < widthC; c++) {
                rotatedArr[widthC-1-c][r] = arr[r][c];
            }
        }

        rotate90(curR+1, curC+1, hLen-2, widLen-2);
    }

    public static void rotate180(int curR, int curC, int hLen, int widLen) {
        if (widLen < 2) return;

        int heightR = curR + hLen;
        int widthC = curC + widLen;

        for (int r = curR; r < heightR; r++) {
            for (int c = curC; c < widthC; c++) {
                rotatedArr[heightR-1-r][widthC-1-c] = arr[r][c];
            }
        }

        rotate180(curR+1, curC+1, hLen-2, widLen-2);
    }

    public static void rotate270(int curR, int curC, int hLen, int widLen) {
        if (widLen < 2) return;

        int heightR = curR + hLen;
        int widthC = curC + widLen;

        for (int r = curR; r < heightR; r++) {
            for (int c = curC; c < widthC; c++) {
                rotatedArr[c][r] = arr[r][c];
            }
        }

        rotate270(curR+1, curC+1, hLen-2, widLen-2);
    }

    private static void push(int r, int c, int closeR, int closeC, int nDir, int hLen, int widLen) {
        if (widLen < 2 || hLen < 2) return;

        int nr = r + DIRS[nDir][0], nc = c + DIRS[nDir][1];
        int hOffset = (N-hLen)/2, wOffset = (M-widLen)/2;
        if (nr < hOffset || nr >= N-hOffset || nc < wOffset || nc >= M-wOffset) {
            nDir = (nDir + 1) % DIRS.length;
            nr = r + DIRS[nDir][0];
            nc = c + DIRS[nDir][1];
        }

        if (nr == closeR && nc == closeC) {
            rotatedArr[closeR][closeC] = arr[closeR][closeC+1];
            return;
        }

        rotatedArr[nr][nc] = arr[r][c];

        push(nr, nc, closeR, closeC, nDir, hLen, widLen);
    }
}
