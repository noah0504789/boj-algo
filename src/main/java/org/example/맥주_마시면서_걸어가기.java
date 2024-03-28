package org.example;

import java.io.IOException;

/**
 * @author noah kim
 * @date 2024-03-28
 * @link https://www.acmicpc.net/problem/9205
 * @requirement 목적지에 도착할 수 있는지 여부를 출력하라.
 * @keyword
    [map]
    [이동]
    - 단위: 50m
    - 조건: 이동 전 맥주 마셔야 함

    [맥주]
    - capacity: 최대 20개

    [편의점]
    - 충전: 빈 병 갯수만큼
 * @input TC: input (<=50)
    - N: 편의점 갯수(0<=N<=100)
    - start
    - (편의점)*N
    - dest
 * @output
 * @time_complex
 * @perf
 */
public class 맥주_마시면서_걸어가기 {
    private static final StringBuilder sb = new StringBuilder();
    private static final String POSSIBLE = "happy";
    private static final String IMPOSSIBLE = "sad";
    private static Point src, dest;
    private static Point[] cvsList;
    private static int tc, n;

    static class Point {
        final int r, c;

        public Point(int x, int y) {
            this.r = 32768-y;
            this.c = 32768+x;
        }
    }

    public static void main(String[] args) throws IOException {
        tc = readInt();
        for (int t = 0; t < tc; t++) {
            n = readInt();
            cvsList = new Point[n];

            src = new Point(readInt(), readInt());

            for (int i = 0; i < n; i++) {
                cvsList[i] = new Point(readInt(), readInt());
            }

            dest = new Point(readInt(), readInt());


        }

        System.out.println(sb);
    }

    private static int readInt() throws IOException {
        int c, n = System.in.read() & (1<<4)-1;
        while ((c = System.in.read()) > (1<<5)) {
            n = (n<<1)+(n<<3)+(c&(1<<4)-1);
        }
        return n;
    }
}
