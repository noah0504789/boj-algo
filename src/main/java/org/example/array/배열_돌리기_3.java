package org.example.array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author noah kim
 * @date 2024-02-07
 * @keyword_solution
    Requirements: 주어진 배열에 특정 연산을 수행했을 때, 결과값을 출력하라.

    [연산]
    1. 상하 반전
    2. 좌우 반전
    3. 90도 회전 (clockwise)
    4. 90도 회전 (counter-clockwise)
    5. 4개의 그룹을 시계방향으로 이동
    6. 4개의 그룹을 반시계방향으로 이동

    [그룹]
    - 크기: N/2*M/2
    - 개수: 4개
    - 1 2
      4 3
 * @input
    - N(높이),M(너비),R(연산수) 2<=N,M<=100|1<=R<=1_000
    - 배열 정보 (1<=칸<=100_000_000)
 * @output
    - 회전된 배열
 * @time_complex
 * @perf
 */
public class 배열_돌리기_3 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuffer sb = new StringBuffer();
    private static StringTokenizer st;
    private static int[][] arr;
    private static int[] operations;
    private static int N, M, R;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        arr = new int[N][];

        for (int r = 0; r < N; r++) {
            arr[r] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        operations = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int operation : operations) {
            switch (operation) {
                case 1: verticalFilp(); break;
                case 2: horizontalFilp(); break;
                case 3: rotate90CW(); break;
                case 4: rotate90CCW(); break;
                case 5: shiftGroupsCW(); break;
                case 6: shiftGroupsCCW(); break;
            }
        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                sb.append(arr[r][c] + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb);

        br.close();
    }

    private static void verticalFilp() {
        int midR = N / 2;
        int r = midR, l = midR-1;

        while (l >= 0) {
            for (int col = 0; col < M; col++) {
                int tmp = arr[r][col];
                arr[r][col] = arr[l][col];
                arr[l][col] = tmp;
            }

            r++;
            l--;
        }
    }

    private static void horizontalFilp() {
        int midC = M / 2;
        int r = midC, l = midC-1;

        while (l >= 0) {
            for (int row = 0; row < N; row++) {
                int tmp = arr[row][l];
                arr[row][l] = arr[row][r];
                arr[row][r] = tmp;
            }

            r++;
            l--;
        }
    }

    private static void rotate90CW() {
        int[][] rotatedArr = new int[M][N];

        for (int r = 0; r < M; r++) {
            for (int c = 0; c < N; c++) {
                rotatedArr[r][c] = arr[N-1-c][r];
            }
        }

        arr = rotatedArr;
        int tmp = N;
        N = M;
        M = tmp;
    }

    public static void rotate90CCW() {
        int[][] rotatedArr = new int[M][N];

        for (int r = 0; r < M; r++) {
            for (int c = 0; c < N; c++) {
                rotatedArr[r][c] = arr[c][M-1-r];
            }
        }

        arr = rotatedArr;
        int tmp = N;
        N = M;
        M = tmp;
    }

    private static void shiftGroupsCW() {
        int[][] rotatedArr = new int[N][M];

        int dy = N/2, dx = M/2;

        for (int r = 0; r < N/2; r++) {
            for (int c = 0; c < M/2; c++) {
                rotatedArr[r][c] = arr[r+dy][c];
            }
        }

        for (int r = 0; r < N/2; r++) {
            for (int c = M/2; c < M; c++) {
                rotatedArr[r][c] = arr[r][c-dx];
            }
        }

        for (int r = N/2; r < N; r++) {
            for (int c = M/2; c < M; c++) {
                rotatedArr[r][c] = arr[r-dy][c];
            }
        }

        for (int r = N/2; r < N; r++) {
            for (int c = 0; c < M/2; c++) {
                rotatedArr[r][c] = arr[r][c+dx];
            }
        }

        arr = rotatedArr;
    }

    private static void shiftGroupsCCW() {
        int[][] rotatedArr = new int[N][M];

        int dy = N/2, dx = M/2;

        for (int r = 0; r < N/2; r++) {
            for (int c = 0; c < M/2; c++) {
                rotatedArr[r][c] = arr[r][c+dx];
            }
        }

        for (int r = 0; r < N/2; r++) {
            for (int c = M/2; c < M; c++) {
                rotatedArr[r][c] = arr[r+dy][c];
            }
        }

        for (int r = N/2; r < N; r++) {
            for (int c = M/2; c < M; c++) {
                rotatedArr[r][c] = arr[r][c-dx];
            }
        }

        for (int r = N/2; r < N; r++) {
            for (int c = 0; c < M/2; c++) {
                rotatedArr[r][c] = arr[r-dy][c];
            }
        }

        arr = rotatedArr;
    }
}
