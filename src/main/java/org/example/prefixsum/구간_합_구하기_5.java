package org.example.prefixsum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @author noah kim
 * @date 2024-02-01
 * @link https://www.acmicpc.net/problem/11660
 * @keyword_solution
    Requirements : N * N의 보드에서 (x1, y1)부터 (x2, y2)까지 합을 출력.
 * @input
    - N(보드 폭), M(요청된 구간합 수)
    - 보드 정보
    - 요청한 구간합의 정보: x1, y1, x2, y2 (1행 1열부터 시작)
 * @output
    - 구간합 출력
 * @time_complex O(N^2)
 * @perf
 */

public class 구간_합_구하기_5 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuffer sb = new StringBuffer();
    private static final String DELIMITER = " ";
    private static int[][] board, sums;
    private static int[] tmp;
    private static int N, M, c1, r1, c2, r2, leftSum, upSum, diagSum;

    public static void main(String[] args) throws IOException {
        tmp = Arrays.stream(br.readLine().split(DELIMITER)).mapToInt(Integer::parseInt).toArray();
        N = tmp[0];
        M = tmp[1];
        board = new int[N+1][N+1];
        sums = new int[N+1][N+1];

        for (int r = 1; r <= N; r++) {
            tmp = Arrays.stream(br.readLine().split(DELIMITER)).mapToInt(Integer::parseInt).toArray();
            for (int c = 1; c <= N; c++) {
                board[r][c] = tmp[c-1];
            }
        }

        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                leftSum = sums[r][c-1];
                upSum = sums[r-1][c];
                diagSum = sums[r-1][c-1];

                sums[r][c] = leftSum + upSum - diagSum + board[r][c];
            }
        }

        for (int req = 0; req < M; req++) {
            tmp = Arrays.stream(br.readLine().split(DELIMITER)).mapToInt(Integer::parseInt).toArray();
            r1 = tmp[0]-1;
            c1 = tmp[1]-1;
            r2 = tmp[2];
            c2 = tmp[3];

            sb.append(partSum(r2, c2, r1, c1) + "\n");
        }

        System.out.println(sb);
    }

    private static int partSum(int r2, int c2, int r1, int c1) {
        return sums[r2][c2] - sums[r1][c2] - sums[r2][c1] + sums[r1][c1];
    }
}
