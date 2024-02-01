package org.example.recursive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

/**
 * @author noah kim
 * @date 2024-02-01
 * @link https://www.acmicpc.net/problem/1914
 * @keyword_solution
    Requirements : 최소 횟수로 첫번째 장대의 모든 원판을 세번째 장대로 옮기고, 옮기는 과정을 순서대로 출력하라.

    [원판]
    - n개
    - 서로 반경 다름

    [장대]
    - 1 (N개의 원판에는 반경이 큰 순서대로 쌓여있음)
    - 2
    - 3

    [옮기는 규칙]
    - 한 번에 한 개의 원판만 다른 장대로 옮길 수 있음
    - 장대의 원판들은 항상 반경이 큰 순서대로 쌓일 수 있음
 * @input
    - N (원판 갯수)(1~100)
 * @output
    - K (옮긴 횟수)
    - from(장대 번호) to(장대 번호)
 * @time_complex O(2^N)
 * @perf
 */
public class 하노이_탑 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuffer sb = new StringBuffer();
    private static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        System.out.println(BigInteger.TWO.pow(N).subtract(BigInteger.ONE));

        if (N <= 20) {
            hanoi(N, 1, 2, 3);
            System.out.println(sb);
        }

        br.close();
    }

    private static void hanoi(final int cnt, int from, int tmp, int to) {
        if (cnt == 0) return;

        hanoi(cnt-1, from, to, tmp);
        if (N <= 20) sb.append(from + " " + to + "\n");
        hanoi(cnt-1, tmp, from, to);
    }
}
