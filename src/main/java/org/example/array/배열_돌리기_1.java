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
    Requirements: 주어진 배열을 반시계방향으로 R번 회전했을 때, 결과값을 출력하라.
 * @input
    - N(높이),M(너비),R(회전수) 2<=N,M<=300|1<=R<=1000
    - 배열 정보
 * @output
    - 회전된 배열
 * @time_complex
 * @perf
 */
public class 배열_돌리기_1 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuffer sb = new StringBuffer();
    private static StringTokenizer st;
    private static int N, M, R;
    private static int[][] arr;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        arr = new int[N][];

        for (int r = 0; r < N; r++) {
            arr[r] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        rotate();

        for (int r = 0 ; r < N; r++) {
            for (int c = 0 ; c < M; c++) {
                sb.append(arr[r][c] + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb);

        br.close();
    }

    private static void rotate() {
        int layers = Math.min(N, M) / 2;
        for (int layer = 0; layer < layers; layer++) {
            int height = N-2*layer, width = M-2*layer;
            int perimeter = 2 * (height+width-2);
            for (int r = 0; r < R % perimeter; r++) {
                rotateArray(layer);
            }
        }
    }

    private static void rotateArray(int layer) {
        int sr = layer, sc = layer;
        int hEnd = N-1-layer, wEnd = M-1-layer;
        int tmp = arr[sr][sc];

        // up
        for (int c = sc; c < wEnd; c++) {
            arr[sr][c] = arr[sr][c+1];
        }

        // right
        for (int r = sr; r < hEnd; r++) {
            arr[r][wEnd] = arr[r+1][wEnd];
        }

        // down
        for (int c = wEnd; c > sc; c--) {
            arr[hEnd][c] = arr[hEnd][c-1];
        }

        // left
        for (int r = hEnd; r > sr; r--) {
            arr[r][sc] = arr[r-1][sc];
        }

        arr[sr+1][sc] = tmp;
    }
}
